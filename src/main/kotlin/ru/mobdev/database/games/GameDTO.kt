package ru.mobdev.database.games

import kotlinx.serialization.Serializable
import ru.mobdev.features.games.models.CreateGameRequest
import ru.mobdev.features.games.models.CreateGameResponse
import ru.mobdev.features.games.models.GameResponse
import java.util.*

@Serializable
data class GameDTO(
    val id: String,
    val name: String,
    val backdrop: String?,
    val logo: String,
    val description: String,
    val downloadCount: Int,
    val version: String,
    val weight: String
)

fun CreateGameRequest.mapToGameDTO(): GameDTO =
    GameDTO(
        id = UUID.randomUUID().toString(),
        name = title,
        description = description,
        version = version,
        backdrop = "",
        logo = "",
        downloadCount = 0,
        weight = ""
    )

fun GameDTO.mapToCreateGameResponse(): CreateGameResponse =
    CreateGameResponse(
        gameID = id,
        title = name,
        description = description,
        version = version
    )

fun GameDTO.mapToGameResponse() = GameResponse(
    gameId = id,
    title = name,
    description = description,
    version = version,
    weight = weight
)