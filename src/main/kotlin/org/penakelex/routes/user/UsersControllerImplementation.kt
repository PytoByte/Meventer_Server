package org.penakelex.routes.user

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.util.reflect.*
import jakarta.mail.*
import jakarta.mail.Message
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeMessage
import kotlinx.serialization.json.Json
import org.penakelex.database.models.*
import org.penakelex.database.services.Service
import org.penakelex.ecnryption.decipher
import org.penakelex.fileSystem.FileManager
import org.penakelex.response.Result
import org.penakelex.response.toHttpStatusCode
import org.penakelex.routes.extensions.getIntJWTPrincipalClaim
import org.penakelex.session.*
import java.util.*


class UsersControllerImplementation(
    private val service: Service,
    private val valuesJWT: JWTValues,
    private val properties: Properties,
    private val userEmailValues: UserEmailValues,
    private val authenticator: Authenticator,
    private val fileManager: FileManager,
    private val controllerJWT: JWTController
) : UsersController {
    override suspend fun sendEmailCode(call: ApplicationCall) {
        val userEmail = call.receive<UserEmail>()
        call.respond(Result.SENDING_VERIFICATION_CODE_STARTED.toHttpStatusCode(), "")
        val code = buildString {
            val range = 0..9
            repeat(6) { append(range.random()) }
        }
        try {
            Transport.send(
                MimeMessage(Session.getInstance(properties, authenticator)).apply {
                    setFrom(InternetAddress(userEmailValues.email, userEmailValues.personal))
                    addRecipient(Message.RecipientType.TO, InternetAddress(userEmail))
                    subject = userEmailValues.subject
                    setText(userEmailValues.getMessageBody(code))
                }
            )
            service.usersEmailCodesService.insertCode(email = userEmail, code = code)
        } catch (_: MessagingException) {
        }
    }

    override suspend fun verifyEmailCode(call: ApplicationCall) = call.response.status(
        service.usersEmailCodesService.verifyCode(
            emailCode = call.receive<UserEmailCode>()
        ).toHttpStatusCode()
    )

    override suspend fun verifyToken(call: ApplicationCall) = call.response.status(
        HttpStatusCode.OK
    )

    override suspend fun registerUser(call: ApplicationCall) {
        val multiPartData = call.receiveMultipart().readAllParts()
        val user: UserRegister = multiPartData.filterIsInstance<PartData.FormItem>().singleOrNull()?.let {
            Json.decodeFromString(it.value)
        } ?: return call.response.status(
            Result.EMPTY_FORM_ITEM_OF_MULTI_PART_DATA.toHttpStatusCode()
        )
        val codeVerificationResult = service.usersEmailCodesService.verifyAndDeleteCode(
            email = user.email, code = user.code
        )
        if (codeVerificationResult != Result.OK) return call.response.status(
            codeVerificationResult.toHttpStatusCode()
        )
        val images = fileManager.uploadFiles(
            fileItems = multiPartData.filterIsInstance<PartData.FileItem>()
        )
        if (images.size > 1) return call.response.status(
            Result.USER_CAN_NOT_HAVE_MORE_THAN_ONE_AVATAR.toHttpStatusCode()
        )
        val (insertionResult, userID) = service.usersService.insertNewUser(
            user = user, avatar = images.singleOrNull()
        )
        if (userID == null) return call.response.status(insertionResult.toHttpStatusCode())
        val expirationTime = controllerJWT.getExpirationTime()
        val (_, sessionID) = service.sessionsService.createSession(
            userID = userID, endOfValidity = expirationTime
        )
        call.respond(
            insertionResult.toHttpStatusCode(),
            controllerJWT.generateToken(valuesJWT, userID, sessionID, expirationTime),
            typeInfo<String>()
        )
    }


    override suspend fun loginUser(call: ApplicationCall) {
        val user = call.receive<UserLogin>()
        val (checkResult, userID) = service.usersService.isEmailAndPasswordCorrect(user)
        if (userID == null) return call.response.status(checkResult.toHttpStatusCode())
        val expirationTime = controllerJWT.getExpirationTime()
        val (_, sessionID) = service.sessionsService.createSession(
            userID = userID, endOfValidity = expirationTime
        )
        call.respond(
            checkResult.toHttpStatusCode(),
            controllerJWT.generateToken(valuesJWT, userID, sessionID, expirationTime),
            typeInfo<String>()
        )
    }

    override suspend fun getUserData(call: ApplicationCall) {
        val (result, data) = service.usersService.getUserData(
            userID = call.receiveNullable<Int?>()
                ?: call.getIntJWTPrincipalClaim(USER_ID)
        )
        call.respond(
            result.toHttpStatusCode(),
            data,
            typeInfo<User?>()
        )
    }

    override suspend fun getUsersByNickname(call: ApplicationCall) {
        val (result, users) = service.usersService.getUsersByNickname(
            nickname = call.receive<String>()
        )
        call.respond(
            result.toHttpStatusCode(),
            users,
            typeInfo<List<UserShort>>()
        )
    }

    override suspend fun updateUserData(call: ApplicationCall) {
        val multiPartData = call.receiveMultipart().readAllParts()
        val userData: UserUpdate = Json.decodeFromString(
            string = multiPartData.filterIsInstance<PartData.FormItem>().singleOrNull()?.value
                ?: return call.response.status(Result.EMPTY_FORM_ITEM_OF_MULTI_PART_DATA.toHttpStatusCode())
        )
        val newAvatar = fileManager.uploadFiles(
            fileItems = multiPartData.filterIsInstance<PartData.FileItem>()
        ).singleOrNull()
        val userID = call.getIntJWTPrincipalClaim(USER_ID)
        if (newAvatar != null) {
            val (gettingAvatarResult, lastAvatar) = service.usersService.getUserAvatar(userID = userID)
            if (gettingAvatarResult == Result.NO_USER_WITH_SUCH_ID) {
                return call.response.status(gettingAvatarResult.toHttpStatusCode())
            }
            if (gettingAvatarResult == Result.OK) fileManager.deleteFiles(listOf(lastAvatar!!))
        }
        if (newAvatar == null && userData.nickname == null && userData.name == null) {
            return call.response.status(Result.NOTHING_TO_CHANGE.toHttpStatusCode())
        }
        call.response.status(
            service.usersService.updateUserData(
                userID = userID,
                userData = userData,
                avatar = newAvatar
            ).toHttpStatusCode()
        )
    }

    override suspend fun updateUserEmail(call: ApplicationCall) {
        val (emailCode, email) = call.receive<UserUpdateEmail>()
        val verificationResult = service.usersEmailCodesService.verifyAndDeleteCode(
            email = email,
            code = emailCode
        )
        if (verificationResult != Result.OK) {
            return call.respond(verificationResult.toHttpStatusCode())
        }
        call.respond(
            service.usersService.updateEmail(
                userID = call.getIntJWTPrincipalClaim(USER_ID),
                email = email
            ).toHttpStatusCode()
        )
    }

    override suspend fun updateUserPassword(call: ApplicationCall) {
        val (emailCode, oldPassword, newPassword) = call.receive<UserUpdatePassword>()
        val userID = call.getIntJWTPrincipalClaim(USER_ID)
        val (gettingUserEmailResult, email) = service.usersService.getUserEmail(userID = userID)
        if (gettingUserEmailResult != Result.OK) {
            return call.response.status(gettingUserEmailResult.toHttpStatusCode())
        }
        val verificationResult = service.usersEmailCodesService.verifyAndDeleteCode(
            email = email!!,
            code = emailCode
        )
        if (verificationResult != Result.OK) {
            return call.response.status(verificationResult.toHttpStatusCode())
        }
        call.response.status(
            service.usersService.updatePassword(
                userID = userID,
                oldPassword = oldPassword,
                newPassword = newPassword
            ).toHttpStatusCode()
        )
    }

    override suspend fun createFeedback(call: ApplicationCall) = call.response.status(
        service.usersFeedbackService.insertFeedback(
            fromUserID = call.getIntJWTPrincipalClaim(USER_ID),
            feedback = call.receive<UserFeedbackCreate>()
        ).toHttpStatusCode()
    )

    override suspend fun getFeedbackToUser(call: ApplicationCall) {
        val (result, feedback) = service.usersFeedbackService.getAllFeedbackToUser(
            userID = call.receiveNullable<Int?>()
                ?: call.getIntJWTPrincipalClaim(USER_ID)
        )
        call.respond(
            result.toHttpStatusCode(),
            feedback,
            typeInfo<List<UserFeedback>?>()
        )
    }

    override suspend fun updateFeedback(call: ApplicationCall) = call.response.status(
        service.usersFeedbackService.updateFeedback(
            userID = call.getIntJWTPrincipalClaim(USER_ID),
            feedback = call.receive<UserFeedbackUpdate>()
        ).toHttpStatusCode()
    )

    override suspend fun deleteFeedback(call: ApplicationCall) = call.response.status(
        service.usersFeedbackService.deleteFeedback(
            userID = call.getIntJWTPrincipalClaim(USER_ID),
            feedbackID = call.receive<Long>()
        ).toHttpStatusCode()
    )

    override suspend fun logOut(call: ApplicationCall) {
        val payload = call.principal<JWTPrincipal>()!!.payload
        call.response.status(
            service.sessionsService.deleteSession(
                userID = payload.getClaim(USER_ID).asInt(),
                sessionID = payload.getClaim(SESSION_ID).asString().decipher().toInt()
            ).toHttpStatusCode()
        )
    }
}
