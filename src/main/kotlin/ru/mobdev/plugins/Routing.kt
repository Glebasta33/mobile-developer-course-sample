package ru.mobdev.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello Backend!")
        }
        get("/route") {
            call.respondText("Another respond")
        }
    }
}
