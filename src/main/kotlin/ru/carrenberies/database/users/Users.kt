package ru.carrenberies.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Users: Table() {
    private val id_user = Users.integer("id_user")
    private val first_name = Users.varchar("first_name", 45)
    private val second_name = Users.varchar("second_name", 45)
    private val user_type = Users.varchar("user_type", 45)
    private val rating = Users.integer("rating")
//    private val avatar_picture = Users.varchar("avatar_picture", 45)
    private val phone_number = Users.varchar("phone_number", 45)
    private val password = Users.varchar("password", 45)

    fun insert(userDTO: UserDTO) {
        transaction {
            Users.insert {
                it[first_name] = userDTO.first_name
                it[second_name] = userDTO.second_name
                it[user_type] = userDTO.user_type
                it[rating] = userDTO.rating
//                it[avatar_picture] = userDTO.avatar_picture
                it[phone_number] = userDTO.phone_number
                it[password] = userDTO.password
            }
        }
    }

    fun fetchUser(phone_number: String): UserDTO? {
       return try {
           transaction { val userModel = Users.select { Users.phone_number.eq(phone_number)}.single()
               UserDTO(
                   id_user = userModel[id_user],
                   password = userModel[password],
                   first_name = userModel[first_name],
                   second_name = userModel[second_name],
                   user_type = userModel[user_type],
                   rating = userModel[rating],
//                   avatar_picture = userModel[avatar_picture],
                   phone_number = userModel[Users.phone_number]
               )
           }
       } catch (e: Exception){
           null
       }
    }

    fun fetchUserBuId(id_user: Int): UserDTO? {
        return try {
            transaction { val userModel = Users.select { Users.id_user.eq(id_user)}.single()
                UserDTO(
                    id_user = userModel[Users.id_user],
                    password = userModel[password],
                    first_name = userModel[first_name],
                    second_name = userModel[second_name],
                    user_type = userModel[user_type],
                    rating = userModel[rating],
//                   avatar_picture = userModel[avatar_picture],
                    phone_number = userModel[phone_number]
                )
            }
        } catch (e: Exception){
            null
        }
    }
}