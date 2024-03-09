package ru.mobdev.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.mobdev.cache.InMemoryCache
import ru.mobdev.cache.TokenCache
import java.util.UUID

fun Application.configureLoginRouting() {
    routing {
        post("/login") {
            val login = call.receive<Login>() // десерилизация объекта из JSON
            val first = InMemoryCache.userList.firstOrNull() { it.login == login.login }

            if (first == null) {
                call.respond(HttpStatusCode.BadRequest, "User not found")
            } else if (first.password == login.password){
                val isLoginInCache = InMemoryCache.userList.map { it.login }.contains(login.login)
                if (isLoginInCache) {
                    val token = UUID.randomUUID().toString()
                    val tokenCache = TokenCache(
                        login = login.login,
                        token = token
                    )
                    InMemoryCache.token.add(tokenCache)
                    call.respond(LoginResponse(token))
                    return@post
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }
    }
}