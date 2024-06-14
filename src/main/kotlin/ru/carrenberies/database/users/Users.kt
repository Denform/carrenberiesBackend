package ru.carrenberies.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Users: Table() {
    private val login = Users.integer("login")
    private val password = Users.varchar("password", 45)
    private val email = Users.varchar("email", 45)
    private val username = Users.varchar("username", 45)

    fun insert(userDTO: UserDTO) {
        transaction {
            Users.insert {
//                it[login] = userDTO.login
                it[password] = userDTO.password
                it[username] = userDTO.username
                it[email] = userDTO.email
            }
        }
    }

    fun fetchUser(email: String): UserDTO? {
       return try {
           transaction { val userModel = Users.select { Users.email.eq(email)}.single()
               UserDTO(
                   login = userModel[Users.login],
                   password = userModel[password],
                   username = userModel[username],
                   email = userModel[Users.email]
               )
           }
       } catch (e: Exception){
           null
       }
    }
}