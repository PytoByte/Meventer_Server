package org.penakelex.routes

import org.penakelex.routes.chat.ChatsController
import org.penakelex.routes.event.EventsController
import org.penakelex.routes.file.FilesController
import org.penakelex.routes.user.UsersController

/**
 * Container class for requests controllers
 * */
data class Controller(
    val usersController: UsersController,
    val eventsController: EventsController,
    val filesController: FilesController,
    val chatsController: ChatsController,
)