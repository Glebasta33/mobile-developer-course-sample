package ru.mobdev.cache

import ru.mobdev.features.registration.Registration

/**
 * Временный объект для хранения данных (простейший аналог БД).
 */
object InMemoryCache {
    val userList: MutableList<Registration> = mutableListOf()
    val token: MutableList<TokenCache> = mutableListOf()
}

data class TokenCache(
    val login: String,
    val token: String
)