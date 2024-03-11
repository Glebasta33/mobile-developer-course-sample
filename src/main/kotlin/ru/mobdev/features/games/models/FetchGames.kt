package ru.mobdev.features.games.models

import kotlinx.serialization.Serializable

@Serializable
data class FetchGamesRequest(
    val searchQuery: String
)

@Serializable
data class FetchGamesResponse(
    val games: List<GameResponse>
)

@Serializable
data class GameResponse(
    val gameId: String,
    val title: String,
    val description: String,
    val version: String,
    val weight: String
)