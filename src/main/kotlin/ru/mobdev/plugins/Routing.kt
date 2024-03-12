package ru.mobdev.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.mobdev.features.games.configureGamesRouting
import ru.mobdev.features.registration.configureRegistrationRouting
import ru.mobdev.features.login.configureLoginRouting

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello Backend!")
        }
        get("/demo") {
            call.respondText("HELLO! I LOVE YOU :)")
        }
    }
    configureLoginRouting()
    configureRegistrationRouting()
    configureGamesRouting()
}
