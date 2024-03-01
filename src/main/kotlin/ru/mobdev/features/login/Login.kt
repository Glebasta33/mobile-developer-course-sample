package ru.mobdev.features.login

import kotlinx.serialization.Serializable

/**
 * Модель для хранения логина и пароля для примера простой аутентификации.
 */
@Serializable
data class Login(
    val login: String,
    val password: String
)

/**
 * Токен, который отправится клиенту, чтобы после входа не вводить каждый раз логин и пароль.
 */
@Serializable
data class LoginResponse(
    val token: String
)