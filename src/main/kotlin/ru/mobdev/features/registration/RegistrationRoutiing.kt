package ru.mobdev.features.registration

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.mobdev.features.cache.InMemoryCache
import ru.mobdev.features.cache.TokenCache
import ru.mobdev.utils.isValidEmail
import java.util.*

fun Application.configureRegistrationRouting() {
    routing {
        post("/registration") {
            val registration = call.receive<Registration>()
            if (!registration.email.isValidEmail()) {
                call.respond(status = HttpStatusCode.BadRequest, message = "Email is not valid")
            }

            val isUserInCache = InMemoryCache.userList.map { it.login }.contains(registration.login)
            if (isUserInCache) {
                call.respond(status = HttpStatusCode.Conflict, message = "User already exists")
                return@post
            }

            val token = UUID.randomUUID().toString()
            val tokenCache = TokenCache(
                login = registration.login,
                token = token
            )
            InMemoryCache.token.add(tokenCache)
            InMemoryCache.userList.add(registration)


            println("configureRegistrationRouting: ${InMemoryCache.userList} ")

            call.respond(RegistrationResponse(token = token))
        }
    }
}