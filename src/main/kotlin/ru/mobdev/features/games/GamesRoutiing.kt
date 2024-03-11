package ru.mobdev.features.games

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureGamesRouting() {
    routing {
        post("/games/search") {
            GamesController(call).fetchAllGames()
        }

        post("/games/create") {
            GamesController(call).addGame()
        }
    }
}