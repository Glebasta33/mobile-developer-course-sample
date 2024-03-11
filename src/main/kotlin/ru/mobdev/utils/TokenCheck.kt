package ru.mobdev.utils

import ru.mobdev.database.tokens.TokensTable

object TokenCheck {

    fun isTokenValid(token: String): Boolean = TokensTable.fetchTokens().firstOrNull { it.token == token } != null

    fun isTokenAdmin(token: String): Boolean = token == "bf8487ae-7d47-11ec-90d6-0242ac120003"
}