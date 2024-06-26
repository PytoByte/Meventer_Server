package org.penakelex.database.services.usersFeedback

import org.penakelex.database.models.UserFeedback
import org.penakelex.database.models.UserFeedbackCreate
import org.penakelex.database.models.UserFeedbackUpdate
import org.penakelex.response.Result

interface UsersFeedbackService {
    /**
     * Inserts new feedback to the database
     * @param fromUserID user ID from which it comes
     * @param feedback user feedback
     * @return [Result.YOU_CAN_NOT_FEEDBACK_YOURSELF] if [fromUserID] == [feedback.toUserID]
     * else if [Result.NO_USER_WITH_SUCH_ID] if any user ID not found
     * else if [Result.FEEDBACK_FROM_SAME_USER_AND_TO_SAME_USER] if feedback from user to user already sent
     * else [Result.OK]
     * */
    suspend fun insertFeedback(fromUserID: Int, feedback: UserFeedbackCreate): Result
    /**
     * Gets all feedback to user from the database
     * @param userID user ID to which feedback is getting
     * @return [Result.FEEDBACKS_FOR_USER_WITH_SUCH_ID_NOT_FOUND] if feedbacks is empty
     * else [Result.OK]
     * */
    suspend fun getAllFeedbackToUser(userID: Int): Pair<Result, List<UserFeedback>?>
    suspend fun updateFeedback(userID: Int, feedback: UserFeedbackUpdate): Result
    suspend fun deleteFeedback(userID: Int, feedbackID: Long): Result
}