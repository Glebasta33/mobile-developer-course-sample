package ru.mobdev.database.users

data class UserDTO(
    val login: String,
    val password: String,
    val username: String,
    val email: String?
)