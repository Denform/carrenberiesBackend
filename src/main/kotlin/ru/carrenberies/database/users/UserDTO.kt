package ru.carrenberies.database.users

class UserDTO(
    val id_user: Int,
    val first_name: String,
    val second_name: String,
    val user_type: String,
    val rating: Int,
//    val avatar_picture: String,
    val phone_number: String,
    val password: String
)