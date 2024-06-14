package ru.carrenberies.features.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginReceiveRemote(
    val login: Int,
    val password: String
)

@Serializable
data class LoginResponseRemote(
    val token: String,
    val login: String
)