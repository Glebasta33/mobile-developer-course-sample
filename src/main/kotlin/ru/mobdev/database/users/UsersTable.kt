package ru.mobdev.database.users

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

//Объект для работы с таблицей из базы данных
object UsersTable : Table("users") {
    private val login = UsersTable.varchar("login", 50)
    private val password = UsersTable.varchar("password", 50)
    private val username = UsersTable.varchar("username", 50)
    private val email = UsersTable.varchar("email", 50)

    fun insert(userDTO: UserDTO) {
        transaction {
            UsersTable.insert {
                it[login] = userDTO.login
                it[password] = userDTO.password
                it[username] = userDTO.username
                it[email] = userDTO.email ?: ""
            }
        }
    }

    fun fetchUser(requestLogin: String): UserDTO? {
        return try {
            transaction {
                val userModel = UsersTable.selectAll().where { login.eq(requestLogin) }.singleOrNull()

                println("MY_TEST: $userModel")
                userModel?.let {
                    UserDTO(
                        login = userModel[login],
                        password = userModel[password],
                        username = userModel[username],
                        email = userModel[email]
                    )
                }
            }
        } catch (e: Exception) {
            println("MY_TEST: ${e.message}")
            null
        }

    }
}