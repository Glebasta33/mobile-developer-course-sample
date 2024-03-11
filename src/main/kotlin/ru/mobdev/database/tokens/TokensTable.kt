package ru.mobdev.database.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object TokensTable : Table("tokens") {
    private val login = TokensTable.varchar("login", 50)
    private val id = TokensTable.varchar("id", 50)
    private val token = TokensTable.varchar("token", 50)

    fun insert(tokenDto: TokensDTO) {
        transaction {
            TokensTable.insert {
                it[id] = tokenDto.id
                it[login] = tokenDto.login
                it[token] = tokenDto.token
            }
        }
    }

    fun fetchTokens(): List<TokensDTO> {
        return try {
            transaction {
                TokensTable.selectAll().toList()
                    .map {
                        TokensDTO(
                            id = it[TokensTable.id],
                            token = it[token],
                            login = it[login]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

}