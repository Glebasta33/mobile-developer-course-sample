package ru.mobdev.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello Backend!")
        }
        get("/test") {
            call.respond(Test(text = "Hello, Serialization!"))
        }
    }
}

@Serializable
data class Test(
    val text: String
)
