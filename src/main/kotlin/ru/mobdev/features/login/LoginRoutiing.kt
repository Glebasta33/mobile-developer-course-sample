package ru.mobdev.features.login

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureLoginRouting() {
    routing {
        post("/login") {
            LoginController(call).performLogin()
        }
    }
}