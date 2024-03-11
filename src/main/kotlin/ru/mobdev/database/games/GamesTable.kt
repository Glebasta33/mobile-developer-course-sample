package ru.mobdev.database.games

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object GamesTable : Table("games") {
    private val gamesId = GamesTable.varchar("gameId", 100)
    private val name = GamesTable.varchar("name", 100)
    private val backdrop = GamesTable.varchar("backdrop", 50).nullable()
    private val logo = GamesTable.varchar("logo", 50)
    private val description = GamesTable.varchar("description", 500)
    private val downloadCount = GamesTable.integer("download_count")
    private val version = GamesTable.varchar("version", 15)
    private val weight = GamesTable.varchar("weight", 10)

    fun insert(gameDTO: GameDTO) {
        transaction {
            GamesTable.insert {
                it[gamesId] = gameDTO.id
                it[name] = gameDTO.name
                it[backdrop] = gameDTO.backdrop
                it[logo] = gameDTO.logo
                it[description] = gameDTO.description
                it[downloadCount] = gameDTO.downloadCount
                it[version] = gameDTO.version
                it[weight] = gameDTO.weight
            }
        }
    }

    fun fetchGames(): List<GameDTO> {
        return try {
            transaction {
                GamesTable.selectAll().toList()
                    .map {
                        GameDTO(
                            id = it[gamesId],
                            name = it[name],
                            backdrop = it[backdrop],
                            logo = it[logo],
                            description = it[description],
                            downloadCount = it[downloadCount],
                            version = it[version],
                            weight = it[weight],
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}