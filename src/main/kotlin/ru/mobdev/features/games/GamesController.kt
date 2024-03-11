package ru.mobdev.features.games

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.mobdev.database.games.GamesTable
import ru.mobdev.database.games.mapToCreateGameResponse
import ru.mobdev.database.games.mapToGameDTO
import ru.mobdev.database.games.mapToGameResponse
import ru.mobdev.features.games.models.CreateGameRequest
import ru.mobdev.features.games.models.FetchGamesRequest
import ru.mobdev.features.games.models.FetchGamesResponse
import ru.mobdev.utils.TokenCheck

class GamesController(private val call: ApplicationCall) {

    suspend fun fetchAllGames() {
        val request = call.receive<FetchGamesRequest>()
        val token = call.request.headers["Bearer-Authorization"]

        if (TokenCheck.isTokenValid(token.orEmpty()) || TokenCheck.isTokenAdmin(token.orEmpty())) {
            if (request.searchQuery.isBlank()) {
                call.respond(FetchGamesResponse(games = GamesTable.fetchGames().map { it.mapToGameResponse() }))
            } else {
                call.respond(GamesTable.fetchGames().filter { it.name.contains(request.searchQuery, ignoreCase = true) })
            }
        } else {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }

    suspend fun addGame() {
        val token = call.request.headers["Bearer-Authorization"]
        if (TokenCheck.isTokenAdmin(token.orEmpty())) {
            val request = call.receive<CreateGameRequest>()
            val game = request.mapToGameDTO()
            GamesTable.insert(game)
            call.respond(game.mapToCreateGameResponse())
        } else {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }


}