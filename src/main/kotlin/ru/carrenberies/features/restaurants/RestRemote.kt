package ru.carrenberies.features.restaurants

import kotlinx.serialization.Serializable

@Serializable
data class RestReceiveRemote(
    val id_rest: Int,
)

@Serializable
data class RestResponseRemote(
    val name: String,
    val address: String,
    val description: String,
    val open_time: String,
    val email: String,
    val INN: String,
    val account_number: String
)