package ru.mobdev.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.mobdev.database.tokens.TokensDTO
import ru.mobdev.database.tokens.TokensTable
import ru.mobdev.database.users.UsersTable
import java.util.*

class LoginController(private val call: ApplicationCall) {

    suspend fun performLogin() {
        val login = call.receive<Login>() // десерилизация объекта из JSON

        val userDTO = UsersTable.fetchUser(login.login)

        if (userDTO == null) {
            call.respond(HttpStatusCode.BadRequest, "User not found")
        } else if (userDTO.password == login.password) {
            val token = UUID.randomUUID().toString()

            TokensTable.insert(
                TokensDTO(
                    login = login.login,
                    token = token,
                    id = UUID.randomUUID().toString()
                )
            )
            call.respond(LoginResponse(token))
            return
        } else {
            call.respond(HttpStatusCode.BadRequest, "Invalid password")
        }
    }

}