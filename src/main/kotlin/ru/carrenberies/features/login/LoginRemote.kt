package ru.carrenberies.features.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginReceiveRemote(
    val phone_number: String,
    val password: String
)

//@Serializable
//data class LoginResponseRemote(
//    val phone_number: String,
//    val password: String
//)