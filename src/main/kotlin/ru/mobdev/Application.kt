package ru.mobdev

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import ru.mobdev.database.games.GamesTable
import ru.mobdev.database.tokens.TokensTable
import ru.mobdev.database.users.UsersTable
import ru.mobdev.plugins.configureRouting
import ru.mobdev.plugins.configureSerialization

fun main() {
    initDatabase()

    embeddedServer(
        Netty,
        port = 8080,
        module = Application::module
    ).start(wait = true)
}

private fun initDatabase() {
    Database.connect(
        url = "jdbc:postgresql://62.113.115.167/test_db",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "admin"
    )

    transaction {
        SchemaUtils.create(GamesTable, TokensTable, UsersTable)
    }
}

fun Application.module() {
    configureSerialization()
    configureRouting()
}
