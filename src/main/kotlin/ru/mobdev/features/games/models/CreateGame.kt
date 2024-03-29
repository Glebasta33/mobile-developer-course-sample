package ru.mobdev.features.games.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateGameRequest(
    val title: String,
    val description: String,
    val version: String
)

@Serializable
data class CreateGameResponse(
    val gameID: String,
    val title: String,
    val description: String,
    val version: String
)