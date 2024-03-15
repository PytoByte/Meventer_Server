@file:UseSerializers(InstantSerializer::class)
package org.penakelex.database.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.penakelex.database.extenstions.InstantSerializer
import java.time.Instant

@Serializable
data class MessageSend(
    val chatID: Long,
    val body: String,
    val timestamp: Instant,
    val attachment: String?
)

@Serializable
data class Message(
    val id: Long,
    val chatID: Long,
    val senderID: Int,
    val body: String,
    val timestamp: Instant,
    val attachment: String?
)

@Serializable
data class MessageUpdate(
    val chatID: Long,
    val body: String
)