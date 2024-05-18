# Meventer_server

> Приложение для тех, кто ценит своё время
> На всякий случай скопировал это себе

## Содержание

1. [Запросы:](#requests)
    + [Пользователь:](#requests_user)
        + [https://127.0.0.1:8080/user/register](#requests_user_register)
        + [https://127.0.0.1:8080/user/login](#requests_user_login)
        + [https://127.0.0.1:8080/user/sendEmailCode](#requests_user_sendEmailCode)
        + [https://127.0.0.1:8080/user/verifyEmailCode](#requests_user_verifyEmailCode)
        + [https://127.0.0.1:8080/user/data](#requests_user_data)
        + [https://127.0.0.1:8080/user/verifyToken](#requests_user_verifyToken)
        + [https://127.0.0.1:8080/user/byNickname](#requests_user_by_nickname)
        + [https://127.0.0.1:8080/user/update/data](#requests_user_update_data)
        + [https://127.0.0.1:8080/user/update/email](#requests_user_update_email)
        + [https://127.0.0.1:8080/user/update/password](#requests_user_update_password)
        + [https://127.0.0.1:8080/user/feedback/create](#requests_user_feedback_create)
        + [https://127.0.0.1:8080/user/feedback/get](#requests_user_feedback_get)
        + [https://127.0.0.1:8080/user/feedback/update](#requests_user_feedback_update)
        + [https://127.0.0.1:8080/user/feedback/delete](#requests_user_feedback_delete)
        + [https://127.0.0.1:8080/user/logout](#requests_user_feedback_logout)
    + [Мероприятие:](#requests_event)
        + [https://127.0.0.1:8080/event/create](#requests_event_create)
        + [https://127.0.0.1:8080/event/user](#requests_event_user)
        + [https://127.0.0.1:8080/event/update](#requests_event_update)
        + [https://127.0.0.1:8080/event/delete](#requests_event_delete)
        + [https://127.0.0.1:8080/event/changeUsers/participant](#requests_event_participant)
        + [https://127.0.0.1:8080/event/changeUsers/organizer](#requests_event_organizer)
        + [https://127.0.0.1:8080/event/changeUsers/inFavourites](#requests_event_in_favourites)
        + [https://127.0.0.1:8080/event/global](#requests_event_global)
        + [https://127.0.0.1:8080/event/{eventID}](#requests_event_id)
    + [Файл:](#requests_file)
        + [https://127.0.0.1:8080/file/{fileName}](#requests_file_name)
        + [https://127.0.0.1:8080/file/upload](#requests_file_upload)
    + [Чат:](#request_chat)
        + [wss://127.0.0.1:8085/chat/socket](#requests_chat_socket)
        + [https://127.0.0.1:8080/chat/create/closed](#requests_chat_create_closed)
        + [https://127.0.0.1:8080/chat/create/dialog](#requests_chat_create_dialog)
        + [https://127.0.0.1:8080/chat/participants](#requests_chat_participant)
        + [https://127.0.0.1:8080/chat/getAll/chats](#requests_chat_get_all_chats)
        + [https://127.0.0.1:8080/chat/getAll/messages](#requests_chat_get_all_messages)
        + [https://127.0.0.1:8080/chat/change/participant](#requests_chat_change_participant)
        + [https://127.0.0.1:8080/chat/change/administrator](#requests_chat_change_administrator)
        + [https://127.0.0.1:8080/chat/change/name](#requests_chat_change_name)
        + [https://127.0.0.1:8080/chat/delete](#requests_chat_delete)
2. [Аргументы запросов:](#arguments)
    + [Пользователь:](#arguments_user)
        + [UserRegister](#arguments_user_register)
        + [UserLogin](#arguments_user_login)
        + [UserEmailCode](#arguments_user_emailCode)
        + [UserFeedbackCreate](#arguments_user_feedback_create)
        + [UserFeedbackUpdate](#arguments_user_feedback_update)
        + [UserUpdate](#arguments_user_update)
        + [UserUpdateEmail](#arguments_user_update_email)
        + [UserUpdatePassword](#arguments_user_update_password)
    + [Мероприятие:](#arguments_event)
        + [EventCreate](#arguments_event_create)
        + [EventSelection](#arguments_event_selection)
        + [EventOrganizer](#arguments_event_organizer)
        + [EventsGet](#arguments_event_get)
        + [EventUpdate](#arguments_event_update)
        + [EventParticipant](#arguments_event_participant)
    + [Чат:](#arguments_chat)
        + [ChatCreate](#arguments_chat_create)
        + [ChatNameUpdate](#arguments_chat_name_update)
        + [ChatAdministratorUpdate](#arguments_chat_administrator_update)
        + [ChatParticipantUpdate](#arguments_chat_participant_update)
        + [MessageSend](#arguments_chat_message_send)
        + [MessageUpdate](#arguments_chat_message_update)
        + [MessageDelete](#arguments_chat_message_delete)
3. [Результаты выполнения запросов:](#results)
    + [Пользователь:](#results_user)
        + [User](#results_user_user)
        + [UserShort](#results_user_short)
        + [UserFeedback](#results_user_feedback)
    + [Мероприятие:](#results_event)
        + [Event](#results_event_event)
    + [Чат:](#results_chat)
        + [Chat](#results_chat_chat)
        + [Message](#results_chat_message)
        + [MessageUpdated](#results_chat_updated)
4. [Структура базы данных:](#database)
    + [Chats](#database_chats)
    + [ChatsAdministrators](#database_chats_administrators)
    + [ChatsParticipants](#database_chats_participants)
    + [Dialogs](#database_dialogs)
    + [Events](#database_events)
    + [EventsImages](#database_events_images)
    + [EventsInFavourites](#database_events_in_favourites)
    + [EventsOrganizers](#database_events_organizers)
    + [EventsParticipants](#database_events_participants)
    + [EventsTags](#database_events_tags)
    + [Messages](#database_messages)
    + [MessagesAttachments](#database_messages_attachments)
    + [Sessions](#database_sessions)
    + [Users](#database_users)
    + [UsersEmailCodes](#database_users_email_codes)
    + [UsersFeedback](#database_users_feedback)

## Запросы <a name="requests"></a>

### Пользователь: <a name="requests_user"></a>

+ Регистрация (https://127.0.0.1:8080/user/register):
  <a name="requests_user_register"></a> \
  Метод: `POST` \
  Аргументы: `MultiPartData` - `UserRegister`, `image` \
  Заголовки запроса: Content-Type - `multipart/mixed` \
  Результат выполнения: `String` \
  Описание: Регистрация нового пользователя, возвращает токен аутентификации. \
  `code = 200`: Регистрация успешна, передан `Bearer токен`. \
  `code ≠ 200`: Регистрация неуспешна, передан `null`.
+ Вход (https://127.0.0.1:8080/user/login):
  <a name="requests_user_login"></a> \
  Метод: `POST` \
  Аргументы: `UserLogin` \
  Заголовки запроса: Content-Type - `application/json` \
  Результат выполнения: `String` \
  Описание: Вход зарегистрированного пользователя, возвращает токен аутентификации. \
  `code = 200`: Вход успешен, передан `Bearer токен`. \
  `code ≠ 200`: Вход неуспешен, передан `null`.
+ Отправка кода подтверждения на почту(https://127.0.0.1:8080/user/sendEmailCode):
  <a name="requests_user_sendEmailCode"></a> \
  Метод: `POST` \
  Аргументы: `String`
  Заголовки запроса: Content-Type - `application/json` \
  Результат выполнения: - \
  Описание: Отправляет на указанную почту код подтверждения, если в процессе выполнения
  возникает проблема с почтой, то код верификации не приходит на почту. \
  `code = 200`: Отправка кода начата. \
  `code ≠ 200`: Вероятна ошибка в запросе.
+ Верификация кода подтверждения (https://127.0.0.1:8080/user/verifyEmailCode):
  <a name="requests_user_verifyEmailCode"></a> \
  Метод: `POST` \
  Аргументы: `UserEmailCode` \
  Заголовки запроса: Content-Type - `application/json` \
  Результат выполнения: - \
  Описание: Проверяет правильность кода подтверждения с почты. \
  `code = 200`: Код верный. \
  `code ≠ 200`: Код неверный.
+ Получение данных пользователя (https://127.0.0.1:8080/user/data):
  <a name="requests_user_data"></a> \
  Метод: `POST` \
  Аргументы: `Int?` \
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результат выполнения: `User?` \
  Описание: Если ID пользователя указан в аргументе, то возвращает информацию о нём,
  иначе возвращает информацию о владельце токена аутентификации. \
  `code = 200`: Пользователь найден, передана информация о нём. \
  `code ≠ 200`: Пользователь не найден или токен аутентификации недействителен
  (в таком случае результатом является -), передан `null`.
+ Верификация токена аутентикации (https://127.0.0.1:8080/user/verifyToken):
  <a name="requests_user_verifyToken"></a> \
  Метод: `GET` \
  Аргументы: -
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результат выполнения: - \
  Описание: Проверяет валидность токена аутентификации. \
  `code = 200`: Токен действительный. \
  `code ≠ 200`: Токен недействительный.
+ Получение кратких данных пользователей(https://127.0.0.1:8080/user/byNickname):
  <a name="requests_user_by_nickname"></a> \
  Метод: `POST` \
  Аргументы: `String`
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результат выполнения: `List<UserShort>` \
  Описание: Получение кратких данных пользователей, у которых ник подобен данному.
  `code = 200`: Токен действительный. \
  `code ≠ 200`: Токен недействительный.
+ Обновление данных пользователя (https://127.0.0.1:8080/user/update/data):
  <a name="requests_user_update_data"></a> \
  Метод: `POST` \
  Аргументы: `MultiPartData` - `UserUpdate`, `image` \
  Заголовки запроса: Content-Type - `multipart/mixed`; Authentication - `Bearer {token}` \
  Результат выполнения: - \
  Описание: Обновление ника, имени, аватарки пользователя. \
  `code = 200`: Пользователь найден и успешно обновлён. \
  `code ≠ 200`: Пользователь с переданным ником уже существует, токен недействительный.
+ Обновление почты пользователя (https://127.0.0.1:8080/user/update/email):
  <a name="requests_user_update_email"></a> \
  Метод: `POST` \
  Аргументы: `UserUpdateEmail` \
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результат выполнения: - \
  Описание: Обновление почты пользователя. \
  `code = 200`: Код верификации с почты правильный, пользователь найден, почта обновлена. \
  `code ≠ 200`: Код верификации неправильный, токен недействительный.
+ Обновление пароля пользователя (https://127.0.0.1:8080/user/update/password):
  <a name="requests_user_update_password"></a> \
  Метод: `POST` \
  Аргументы: `UserUpdatePassword` \
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результат выполнения: - \
  Описание: Обновление пароля пользователя. \
  `code = 200`: Код верификации с почты правильный, пользователь найден, пароль обновлён. \
  `code ≠ 200`: Код верификации неправильный, токен недействительный. \
+ Создание отзыва на пользователя (https://127.0.0.1:8080/user/feedback/create):
  <a name="requests_user_create"></a> \
  Метод: `POST` \
  Аргументы: `UserFeedbackCreate`
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результат выполнения: - \
  Описание: Создаёт отзыв на пользователя. \
  `code = 200`: Пользователи, от кого и кому, найдены, создан отзыв. \
  `code ≠ 200`: Как минимум, один из пользователей не найден, либо пользователь пытался
  отправить отзыв на себя, либо отзыв от пользователя на этого пользователя уже был создан
  до этого, либо токен аутентификации недействителен.
+ Получение отзывов на пользователя (https://127.0.0.1:8080/user/feedback/get):
  <a name="requests_user_get"></a> \
  Метод: `POST` \
  Аргументы: `Int?` \
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результат выполнения: `List<UserFeedback>?` \
  Описание: Получение всех отзывов на указанного пользователя, либо если отправлен `null`,
  то получение отзывов на владельца токена. \
  `code = 200`: Отзывы на пользователя найдены, переданы все отзывы. \
  `code ≠ 200`: Отзывы на пользователя не найдены, либо токен аутентификации недействителен.
+ Обновление отзыва на пользователя (https://127.0.0.1:8080/user/feedback/update):
  <a name="requests_user_feedback_update"></a> \
  Метод: `POST` \
  Аргументы: `UserFeedbackUpdate` \
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результаты выполнения: - \
  Описание: Обновление отзыва на пользователя. \
  `code = 200`: Отзыв найден, отзыв обновлён. \
  `code ≠ 200`: Отзыв не найден, токен не действителен.
+ Удаление отзыва на пользователя (https://127.0.0.1:8080/user/feedback/delete):
  <a name="requests_user_feedback_delete"></a> \
  Метод: `POST` \
  Аргументы: `Long` \
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результаты выполнения: - \
  Описание: Удаление отзыва на пользователя. \
  `code = 200`: Отзыв найден и удалён. \
  `code ≠ 200`: Отзыв не найден, токен не действителен.
+ Выход из аккаунта пользователя (https://127.0.0.1:8080/user/logout):
  <a name="requests_user_feedback_logout"></a> \
  Метод: `POST` \
  Аргументы: - \
  Заголовки запроса: Authentication - `Bearer {token}` \
  Результаты выполнения: - \
  Описание: Удаление сессии пользователя. \
  `code = 200`: Сессия успешно удалена, токен становится недействительным. \
  `code ≠ 200`: Сессия не найдена, либо токен уже был недействительный.

### Мероприятие: <a name="requests_event"></a>

+ Создание мероприятия (https://127.0.0.1:8080/event/create):
  <a name="requests_event_create"></a> \
  Метод: `POST` \
  Аргументы: `MultiPartData` - `EventCreate`, `image` \
  Заголовки запроса: Content-Type - `multipart/mixed`; Authentication - `Bearer {token}` \
  Результат выполнения: - \
  Описание: Создаёт мероприятие. \
  `code = 200`: Мероприятие создано. \
  `code ≠ 200`: Мероприятие не создано: не отправлена информация о мероприятии, либо
  токен аутентификации недействителен.
+ Получение мероприятий пользователя (https://127.0.0.1:8080/event/user):
  <a name="requests_event_user"></a> \
  Метод: `POST` \
  Аргументы: `EventsGet`
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результат выполнения: `List<Event>?` \
  Описание: Получение всех мероприятий пользователя; в `type` указывается тип получения:
    + `null` или `пустая строка`: получение **абсолютно** всех мероприятий пользователя;
    + `participant`: получение всех мероприятий пользователя, где он является **участником**;
    + `organizer`: получение всех мероприятий пользователя, где он является **организатором**;
    + `in favourite`: получение всех мероприятий пользователя, которые находятся в **избранном**;
    + `originator`: получение всех мероприятий пользователя, где является **создателем**.

  `code = 200`: Найдены и переданы мероприятия пользователя. \
  `code ≠ 200`: Токен аутентификации недействителен.
+ Изменение мероприятия (https://127.0.0.1:8080/event/update): <a name="requests_event_update"></a> \
  Метод: `POST` \
  Аргументы: `EventUpdate`
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результат выполнения: - \
  Описание: Изменение мероприятия. \
  `code = 200`: Найдено и изменено мероприятие. \
  `code ≠ 200`: Не найдено мероприятие,
+ Удаление мероприятия (https://127.0.0.1:8080/event/delete): <a name="requests_event_delete"></a> \
  Метод: `POST` \
  Аргументы: `Int` \
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результат выполнения: - \
  Описание: Удаление мероприятия, в аргументы передаётся `ID мероприятия`.
  `code = 200`: Найдено и удалено мероприятие.
  `code ≠ 200`: Мероприятие не найдено, либо токен аутентификации недействителен.
+ Изменение статуса пользователя как участника (https://127.0.0.1:8080/event/changeUsers/participant):
  <a name="requests_event_participant"></a> \
  Метод: `POST` \
  Аргументы: `EventParticipant` \
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результат выполнения: - \
  Описание: Меняет пользователя на участника, либо убирает пользователя из списка участников.
  `code = 200`: Пользователь и мероприятие найдены, его статус изменён.
  `code ≠ 200`: Пользователь не найден, либо мероприятие не найдено, либо пользователь
  является создателем мероприятия, либо токен аутентификации недействителен.
+ Изменение статуса пользователя как организатора (https://127.0.0.1:8080/event/changeUsers/organizer):
  <a name="requests_event_organizer"></a> \
  Метод: `POST` \
  Аргументы: `EventOrganizer` \
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результат выполнения: - \
  Описание: Меняет пользователя на организатора, либо убирает пользователя из списка участников. \
  `code = 200`: Пользователь и мероприятие найдены, его статус изменён. \
  `code ≠ 200`: Пользователь не найден, либо мероприятие не найдено, либо пользователь
  является создателем мероприятия, либо токен аутентификации недействителен.
+ Добавление/удаление мероприятия из избранного для
  пользователя (https://127.0.0.1:8080/event/changeUsers/inFavourites):
  <a name="requests_event_in_favourites"></a> \
  Метод: `POST` \
  Аргументы: `Int` \
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результат выполнения: - \
  Описание: Добавляет/удаляет мероприятие из избранного для пользователя;
  в аргументах запроса указывается `ID мероприятия`. \
  `code = 200`: Мероприятие найдено, и изменён его статус для пользователя. \
  `code ≠ 200`: Мероприятие или пользователь не найдены, либо токен аутентификации не найден.
+ Получение глобальных мероприятий с возможностью выборки (https://127.0.0.1:8080/event/global):
  <a name="requests_event_global"></a> \
  Метод: `POST` \
  Аргументы: `EventSelection` \
  Заголовки запроса: Content-Type - `application/json` \
  Результат выполнения: `List<Event>?` \
  Описание: Получение глобальных мероприятий, выбранных по предоставленным ключевым данным;
  в `sortBy` указан порядок, по которому отсортированы мероприятия:
    + `Nearest ones first`: ближайшие мероприятия сначала;
    + `Further ones first`: дальнейшие мероприятия сначала. \

  `code = 200`: Мероприятия найдены, и передана о них информация. \
  `code ≠ 200`: Ошибка в запросе.
+ Получение данных об одном мероприятии (https://127.0.0.1:8080/event/{eventID}):
  <a name="requests_event_id"></a> \
  Метод: `GET` \
  Аргументы: - \
  Заголовки запроса: - \
  Результат выполнения: `Event` \
  Описание: Получение мероприятия по его `ID`, который указывается в параметре запроса. \
  `code = 200`: Мероприятие найдено, и передана информация о нём. \
  `code ≠ 200`: Мероприятие не найдено, передан `null`.

### Файл: <a name="requests_file"></a>

+ Получение файла по названию (https://127.0.0.1:8080/file/{fileName}):
  <a name="requests_file_name"></a> \
  Метод: `GET` \
  Аргументы: - \
  Заголовки запроса: - \
  Результат выполнения: `File` \
  Описание: Получение файла по `названию`, которое указано в параметре. \
  `code = 200`: Файл найден и передан в ответе. \
  `code ≠ 200`: Файл не найден, токен недействительный.
+ Загрузка файла на сервер (https://127.0.0.1:8080/file/upload):
  <a name="requests_file_upload"></a> \
  Метод: `POST` \
  Аргументы: `ByteArray` \
  Заголовки запроса: Content-Type - `application/{fileExtension}`; Authentication - `Bearer {token}` \
  Результат выполнения: `String` \
  Описание: Загрузка файла на сервер. \
  `code = 200`: Файл успешно создан и передано его имя. \
  `code ≠ 200`: Не получилось создать файл из полученного массива байтов, токен недействительный.

### Чат: <a name="request_chat"></a>

+ Создание сессии веб сокета (wss://127.0.0.1:8080/chat/socket):
  <a name="requests_chat_socket"></a> \
  Метод: `GET` \
  Заголовки запроса: Authentication - `Bearer {token}` \
  Отправление: `MessageSend`, `MessageUpdate`, `MessageDelete` \
  Получение: `Message`, `MessageUpdated`, `Long` \
  Описание: Создаёт сессию веб сокета (на получение `Long` - `ID` удалённого сообщения).
+ Создание закрытого чата (https://127.0.0.1:8080/chat/create/closed):
  <a name="requests_chat_create_closed"></a> \
  Метод: `POST` \
  Аргументы: `ChatCreate` \
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результат выполнения: - \
  Описание: Создание закрытого чата. \
  `code = 200`: Чат успешно создан. \
  `code ≠ 200`: Пользователь не найден, токен недействителен.
+ Создание диалога (https://127.0.0.1:8080/chat/create/dialog):
  <a name="requests_chat_create_dialog"></a> \
  Метод: `POST` \
  Аргументы: `Int` \
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результат выполнения: - \
  Описание: Создание диалога, в аргументе указывается `ID` пользователя, с которым
  создаётся диалог. \
  `code = 200`: Диалог успешно создан. \
  `code ≠ 200`: Пользователь не найден, токен недействителен.
+ Получение участников чата (https://127.0.0.1:8080/chat/participants):
  <a name="requests_chat_participant"></a> \
  Метод: `POST` \
  Аргументы: - \
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результат выполнения: `List<Int>?` \
  Описание: Получение участников чата, в котором состоит пользователь. \
  `code = 200`: Чат найден, передан список участников. \
  `code ≠ 200`: Пользователь не найден, токен недействителен.
+ Получение всех чатов пользователя (https://127.0.0.1:8080/chat/getAll/chats):
  <a name="requests_chat_get_all_chats"></a> \
  Метод: `POST` \
  Аргмуенты: - \
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результат выполнения: `List<Chat>?` \
  Описание: Получение всех чатов пользователя, в которых состоит пользователь. \
  `code = 200`: Пользователь найден, передан список чатов. \
  `code ≠ 200`: Пользователь не найден, токен не действителен.
+ Получение всех сообщений из чата (https://127.0.0.1:8080/chat/getAll/messages):
  <a name="requests_chat_get_all_messages"></a> \
  Метод: `POST` \
  Аргмуенты: `Long` \
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результат выполнения: `List<Message>?` \
  Описание: Получение всех сообщений из чата, `ID` которого указан в аргументе. \
  `code = 200`: Чат найден, передан список сообщений. \
  `code ≠ 200`: Пользователь не найден, чат не найден, токен недействителен.
+ Изменение пользователя как участника чата (https://127.0.0.1:8080/chat/change/participant):
  <a name="requests_chat_change_participant"></a> \
  Метод: `POST` \
  Аргументы: `ChatParticipantUpdate` \
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результат выполнения: - \
  Описание: Изменяет пользователя как участника чата, то есть добавляет или убирает его
  из чата. \
  `code = 200`: Пользователь найден и успешно изменён. \
  `code ≠ 200`: Пользователь не найден, токен недействителен.
+ Изменение пользователя как администратора чата (https://127.0.0.1:8080/chat/change/administrator):
  <a name="requests_chat_change_administrator"></a> \
  Метод: `POST` \
  Аргументы: `ChatAdministratorUpdate` \
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результат выполнения: - \
  Описание: Изменяет пользователя как администратора чата, то есть делает его
  администратором или убирает эту роль с пользователя.\
  `code = 200`: Пользователь найден и успешно изменён. \
  `code ≠ 200`: Пользователь не найден, токен недействителен.
+ Изменение названия чата (https://127.0.0.1:8080/chat/change/name):
  <a name="requests_chat_change_name"></a> \
  Метод: `POST` \
  Аргументы: `ChatNameUpdate` \
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результат выполнения: - \
  Описание: Меняет название чата на новое. \
  `code = 200`: Чат найден, где пользователь является его создателем, и изменено его имя. \
  `code ≠ 200`: Не найден чат, токен недействительный.
+ Удаление чата (https://127.0.0.1:8080/chat/delete): <a name="requests_chat_delete"></a> \
  Метод: `POST` \
  Аргументы: `Long` \
  Заголовки запроса: Content-Type - `application/json`; Authentication - `Bearer {token}` \
  Результат выполнения: - \
  Описание: Удаляет чат с полученным `id`. \
  `code = 200`: Чат найден, где пользователь является его создателем, и удалён. \
  `code ≠ 200`: Чат не найден, токен не действителен.

## Аргументы запросов <a name="arguments"></a>

### Пользователь: <a name="arguments_user"></a>

+ UserRegister <a name="arguments_user_register"></a>

```kotlin
class UserRegister(
    val code: String,
    val email: String,
    val password: String,
    val nickname: String?,
    val name: String,
    val dateOfBirth: LocalDate
)
```

```json
{
  "code": "000000",
  "email": "email@email.com",
  "password": "password",
  "nickname": null,
  "name": "name",
  "dateOfBirth": "2000-01-01"
}
```

+ UserLogin <a name="arguments_user_login"></a>

```kotlin
class UserLogin(
    val email: String,
    val password: String
)
```

```json
{
  "email": "email@email.com",
  "password": "password"
}
```

+ UserEmailCode <a name="arguments_user_emailCode"></a>

```kotlin
class UserEmailCode(
    val email: String,
    val code: String
)
```

```json
{
  "email": "email@email.com",
  "code": "000000"
}
```

+ UserFeedbackCreate <a name="arguments_user_feedback_create"></a>

```kotlin
class UserFeedbackCreate(
    val toUserID: Int,
    val rating: Float,
    val comment: String
)
```

```json
{
  "toUserID": 1,
  "rating": 5.0,
  "comment": "Comment!"
}
```

+ UserFeedbackUpdate <a name="arguments_user_feedback_update"></a>

```kotlin
class UserFeedbackUpdate(
    val feedbackID: Long,
    val rating: Float,
    val comment: String
)
```

```json
{
  "feedbackID": 1231,
  "rating": 5.0,
  "comment": "GOOD!"
}
```

+ UserUpdate <a name="arguments_user_update"></a>

```kotlin
class UserUpdate(
    val nickname: String?,
    val name: String?,
)
```

```json
{
  "nickname": "Anakin",
  "name": null
}
```

+ UserUpdateEmail <a name="arguments_user_update_email"></a>

```kotlin
class UserUpdateEmail(
    val emailCode: String,
    val email: String,
)
```

```json
{
  "emailCode": "000000",
  "email": "newEmail@email.com"
}
```

+ UserUpdatePassword <a name="arguments_user_update_password"></a>

```kotlin
class UserUpdatePassword(
    val emailCode: String,
    val oldPassword: String,
    val newPassword: String
)
```

```json
{
  "emailCode": "000000",
  "oldPassword": "87654321",
  "newPassword": "12345678"
}
```

### Мероприятие: <a name="arguments_event"></a>

+ EventCreate <a name="arguments_event_create"></a>

```kotlin
class EventCreate(
    val name: String,
    val description: String,
    val startTime: Instant,
    val minimalAge: Short?,
    val maximalAge: Short?,
    val price: Int?,
    val tags: List<String>?
)
```

```json
{
  "name": "Event",
  "description": "Event description",
  "startTime": "2024-03-11T10:00:48.315738500Z",
  "minimalAge": 0,
  "maximalAge": null,
  "price": 0,
  "tags": [
    "tag1"
  ]
}
```

+ EventSelection <a name="arguments_event_selection"></a>

```kotlin
class EventSelection(
    val tags: List<String>?,
    val age: UShort?,
    val minimalPrice: Int?,
    val maximalPrice: Int?,
    val sortBy: String?
)
```

```json
{
  "tags": [
    "tag1",
    "tag2"
  ],
  "age": 12,
  "minimalPrice": 20,
  "maximalPrice": null,
  "sortBy": null
}
```

+ EventOrganizer <a name="arguments_event_organizer"></a>

```kotlin
class EventOrganizer(
    val eventID: Int,
    val changingID: Int
)
```

```json
{
  "eventID": 10,
  "changingID": 20
}
```

+ EventsGet <a name="arguments_event_get"></a>

```kotlin
class EventsGet(
    val userID: Int?,
    val actual: Boolean?,
    val aforetime: Boolean?,
    val type: String?
)
```

```json
{
  "userID": 21,
  "actual": true,
  "aforetime": null,
  "type": null
}
```

+ EventUpdate <a name="arguments_event_update"></a>

```kotlin
class EventUpdate(
    val eventID: Int,
    val name: String?,
    val description: String?,
    val startTime: Instant?,
    val minimalAge: Short?,
    val maximalAge: Short?,
    val price: Int?,
    val tags: List<String>?,
    val deletedImages: List<String>?
)
```

```json
{
  "eventID": 12,
  "name": "Event upd",
  "description": "Event description upd",
  "startTime": "2024-03-11T10:00:48.315738500Z",
  "minimalAge": 12,
  "maximalAge": 19,
  "price": 100,
  "tags": null,
  "deletedImages": null
}
```

+ EventParticipant <a name="arguments_event_participant"></a>

```kotlin
class EventParticipant(
    val changingID: Int?,
    val eventID: Int
)
```

```json
{
  "changingID": null,
  "eventID": 1231
}
```

### Чат: <a name="arguments_chat"></a>

+ ChatCreate <a name="arguments_chat_create"></a>

```kotlin
class ChatCreate(
    val name: String,
    val administrators: List<Int>
)
```

```json
{
  "name": "name",
  "administrators": [
    0
  ]
}
```

+ ChatNameUpdate <a name="arguments_chat_name_update"></a>

```kotlin
class ChatNameUpdate(
    val id: Long,
    val name: String
)
```

```json
{
  "id": 23123,
  "name": "chatName"
}
```

+ ChatAdministratorUpdate <a name="arguments_chat_administrator_update"></a>

```kotlin
class ChatAdministratorUpdate(
    val updatingID: Int,
    val chatID: Long
)
```

```json
{
  "updatingID": 123213,
  "chatID": 1231
}
```

+ ChatParticipantUpdate <a name="arguments_chat_participant_update"></a>

```kotlin
class ChatParticipantUpdate(
    val chatID: Long,
    val changingID: Int?
)
```

```json
{
  "chatID": 12312312,
  "changingID": null
}
```

+ MessageSend <a name="arguments_chat_message_send"></a>

```kotlin
class MessageSend(
    val chatID: Long,
    val body: String,
    val timestamp: Instant,
    val attachment: String?
)
```

```json
{
  "chaID": 1231231,
  "body": "Hello, there!",
  "timestamp": "2024-03-11T10:00:48.315738500Z",
  "attachment": null
}
```

+ MessageUpdate <a name="arguments_chat_message_update"></a>

```kotlin
class MessageUpdate(
    val messageID: Long,
    val chatID: Long,
    val body: String
)
```

```json
{
  "messageID": 12312321,
  "chatID": 132,
  "body": "K..."
}
```

+ MessageDelete <a name="arguments_chat_message_delete"></a>

```kotlin
class MessageDelete(
    val messageID: Long,
    val chatID: Long
)
```

```json
{
  "messageID": 12312312,
  "chatID": 12321313
}
```

## Результаты выполнения запросов <a name="results"></a>

### Пользователь: <a name="results_user"></a>

+ User <a name="results_user_user"></a>

```kotlin
class User(
    val userID: Int,
    val email: String,
    val name: String,
    val nickname: String,
    val avatar: String,
    val dateOfBirth: LocalDate
)
```

```json
{
  "userID": 10,
  "email": "email@email.com",
  "name": "Alexa",
  "nickname": "alexa2000",
  "avatar": "avatar.png",
  "dateOfBirth": "2000-01-01"
}
```

+ UserShort <a name="results_user_short"></a>

```kotlin
class UserShort(
    val userID: Int,
    val nickname: String,
    val avatar: String
)
```

```json
{
  "userID": 1231231,
  "nickname": "nick",
  "avatar": "avatar.png"
}
```

+ UserFeedback <a name="results_user_feedback"></a>

```kotlin
class UserFeedback(
    val feedbackID: Long,
    val fromUserID: Int,
    val rating: Float,
    val comment: String
)
```

```json
{
  "feedbackID": 12312313,
  "fromUserID": 1,
  "rating": 4.5,
  "comment": "OK..."
}
```

### Мероприятие: <a name="results_event"></a>

+ Event <a name="results_event_event"></a>

```kotlin
class Event(
    val eventID: Int,
    val name: String,
    val images: List<String>,
    val description: String,
    val startTime: Instant,
    val minimalAge: UShort,
    val maximalAge: UShort?,
    val price: Int,
    val originator: Int,
    val organizers: List<Int>,
    val participants: List<Int>,
    val inFavourites: List<Int>,
    val tags: List<String>
)
```

```json
{
  "eventID": 120,
  "name": "Event",
  "images": "image1.jpg, image2.jpeg",
  "description": "Event description",
  "startTime": "2024-03-11T10:00:48.315738500Z",
  "minimalAge": 0,
  "maximalAge": null,
  "price": 120,
  "originator": 12,
  "organizers": [],
  "participants": [
    131,
    123123
  ],
  "inFavourites": [
    12,
    123123
  ],
  "tags": [
    "Cool event"
  ]
}
```

### Чат: <a name="results_chat"></a>

+ Chat <a name="results_chat_chat"></a>

```kotlin
class Chat(
    val chatID: Long,
    val name: String?,
    val originator: Int?,
    val participants: List<Int>,
    val administrators: List<Int>?,
    val lastMessages: List<Message>
)
```

```json
{
  "chatID": 3333,
  "name": "chatName",
  "originator": 5,
  "participants": [
    1,
    123,
    2,
    5
  ],
  "administrators": [
    1,
    2
  ],
  "lastMessages": []
}
```

+ Message <a name="results_chat_message"></a>

```kotlin
class Message(
    val messageID: Long,
    val chatID: Long,
    val senderID: Int,
    val body: String,
    val timestamp: Instant,
    val attachment: String?
)
```

```json
{
  "messageID": 123123,
  "chatID": 123123,
  "senderID": 12,
  "body": "",
  "timestamp": "2024-03-11T10:00:48.315738500Z",
  "attachment": "a.png"
}
```

+ MessageUpdated <a name="results_chat_updated"></a>

```kotlin
class MessageUpdated(
    val messageID: Long,
    val body: String
)
```

```json
{
  "messageID": 1231231,
  "body": "I have body..."
}
```

## Структура базы данных <a name="database"></a>

### Chats

+ id `bigint` `pk`
+ name `text`
+ originator `integer` `FK >- Users.id`
+ open `boolean`

### ChatsAdministrators

+ chat_id `bigint` `FK >- Chats.id`
+ administrator_id `integer` `FK >- Users.id`

### ChatsParticipants

+ chat_id `bigint` `FK >- Chats.id`
+ participant_id `integer` `FK >- Users.id`

### Dialogs

+ id `bigint`
+ first `integer` `FK >- Users.id`
+ second `integer` `FK >- Users.id`

### Events

+ id `integer` `pk`
+ name `text`
+ description `timestamp without time zone`
+ chat_id `bigint` `FK >- Chats.id`
+ minimal_age `smallint`
+ maximal_age `smallint`
+ price `integer`
+ originator `integer` `FK >- Users.id`

### EventsImages

+ event_id `integer` `FK >- Events.id`
+ image `text`

### EventsInFavourites

+ event_id `integer` `FK >- Events.id`
+ user_favourite_id `integer` `FK >- Users.id`

### EventsOrganizers

+ event_id `integer` `FK >- Events.id`
+ organizer_id `integer` `FK >- Users.id`

### EventsParticipants

+ event_id `integer` `FK >- Events.id`
+ participant_id `integer` `FK >- Users.id`

### EventsTags

+ tag `text`
+ event_id `integer` `FK >- Events.id`

### Messages

+ id `bigint` `pk`
+ chat_id `bigint` `FK >- Chats.id`
+ sender_id `integer` `FK >- Users.id`
+ body `text`
+ timestamp `timestamp without time zone`

### MessagesAttachments

+ message_id `bigint` `FK >- Messages.id`
+ attachment `text`

### Sessions

+ id `integer` `pk`
+ user_id `integer` `FK >- Users.id`
+ end_of_validity `bigint`

### Users

+ id `integer` `pk`
+ email `text`
+ password `text`
+ nickname `text`
+ name `text`
+ avatar `text`
+ date_of_birth `date`

### UsersEmailCodes

+ email `text`
+ code `varchar(6)`
+ expiration_time `bigint`

### UsersFeedback

+ id `bigint` `pk`
+ to_user_id `integer` `FK >- Users.id`
+ from_user_id `integer` `FK >- Users.id`
+ rating `real`
+ comment `text`
