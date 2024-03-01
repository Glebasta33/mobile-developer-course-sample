package ru.mobdev.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {// Подключение плагина для сериализации объектов в JSON и обратно.
        json()
    }
}