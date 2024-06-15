package ru.carrenberies.features.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterReceiveRemote(
//    val id_user: Int,
    val first_name: String,
//    val second_name: String,
    val user_type: String,
//    val rating: Int,
//    val avatar_picture: String,
    val phone_number: String,
    val password: String
)

@Serializable
data class RegisterResponseRemote(
    val phone_number: String,
    val password: String
)