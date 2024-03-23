package org.penakelex.plugins

import com.auth0.jwt.JWT
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import org.koin.ktor.ext.inject
import org.penakelex.database.services.Service
import org.penakelex.response.Result
import org.penakelex.response.toHttpStatusCode
import org.penakelex.session.JWTValues
import org.penakelex.session.PASSWORD
import org.penakelex.session.USER_ID
import org.penakelex.session.getAlgorithm

fun Application.configureSecurity() {
    val service by inject<Service>()
    val values by inject<JWTValues>()
    authentication {
        jwt {
            realm = values.realm
            verifier(
                JWT
                    .require(getAlgorithm(values.secret))
                    .withAudience(values.audience)
                    .withIssuer(values.issuer)
                    .build()
            )
            validate { jwtCredential ->
                val isTokenExpired = jwtCredential.payload.expiresAt.time <= System.currentTimeMillis()
                if (isTokenExpired) {
                    return@validate null
                }
                val isTokenNotValid = service.usersService.isTokenValid(
                    userID = jwtCredential.payload.getClaim(USER_ID).asInt()
                        ?: return@validate null,
                    password = jwtCredential.payload.getClaim(PASSWORD).asString()
                        ?: return@validate null
                ) != Result.OK
                if (isTokenNotValid) return@validate null
                return@validate JWTPrincipal(jwtCredential.payload)
            }
            challenge { _, _ ->
                call.response.status(Result.TOKEN_IS_NOT_VALID_OR_EXPIRED.toHttpStatusCode())
            }
        }
    }
}
