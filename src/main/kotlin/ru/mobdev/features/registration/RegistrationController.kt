package ru.mobdev.features.registration

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.mobdev.database.tokens.TokensDTO
import ru.mobdev.database.tokens.TokensTable
import ru.mobdev.database.users.UserDTO
import ru.mobdev.database.users.UsersTable
import ru.mobdev.utils.isValidEmail
import java.util.*

class RegistrationController(private val call: ApplicationCall) {

    suspend fun registerNewUser() {
        val registration: Registration = try {
            call.receive<Registration>()
        } catch (e: Exception) {
            call.respond(status = HttpStatusCode.BadRequest, message = "Not a valid contract of request object")
            return
        }

        println("MY_TEST: $registration")

        if (!registration.email.isValidEmail()) {
            call.respond(status = HttpStatusCode.BadRequest, message = "Email is not valid")
        }

        val userDTO = UsersTable.fetchUser(registration.login) //обращение к таблице БД

        if (userDTO != null) {
            call.respond(status = HttpStatusCode.Conflict, message = "User already exists")
            return
        } else {
            val token = UUID.randomUUID().toString()

            UsersTable.insert( // вставка объекта в таблицу
                UserDTO(
                    login = registration.login,
                    password = registration.password,
                    username = "",
                    email = registration.email
                )
            )

            TokensTable.insert(
                TokensDTO(
                    login = registration.login,
                    token = token,
                    id = UUID.randomUUID().toString()
                )
            )

            call.respond(RegistrationResponse(token = token))
        }
    }

}