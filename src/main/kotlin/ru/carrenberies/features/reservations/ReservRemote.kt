package ru.carrenberies.features.reservations

import kotlinx.serialization.Serializable
@Serializable
data class ReservReceiveRemote(
    val id_table: Int,
    val id_user: Int,
    val id_rest: Int,
    val data: String,
    val time: String,
    val duration_time: String,
    val count_vis: Int
)

//@Serializable
//data class ReservResponseRemote(
//    val phone_number: String,
//    val password: String
//)