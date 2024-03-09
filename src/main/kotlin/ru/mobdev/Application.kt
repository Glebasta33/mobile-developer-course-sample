package ru.mobdev

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database
import ru.mobdev.plugins.*

fun main() {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/mobile_developer_course",
        driver = "org.postgresql.Driver",
        password = "postgres"
    )

    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureSerialization()
}
