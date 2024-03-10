package ru.mobdev.features.registration

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRegistrationRouting() {
    routing {
        post("/registration") {
            RegistrationController(call).registerNewUser()
        }
    }
}