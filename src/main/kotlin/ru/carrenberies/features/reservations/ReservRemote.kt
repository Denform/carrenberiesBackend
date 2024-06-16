package ru.carrenberies.features.reservations

import kotlinx.serialization.Serializable
@Serializable
data class ReservReceiveRemote(
    val id_table: Int,
    val id_user: Int,
    val id_rest: Int,
    val data: String,
    val time: String,
    val duration_time: Int,
    val count_vis: Int
)

@Serializable
data class ReservReceiveRemoteForFindId(
    val id_table: Int,
    val id_rest: Int,
    val data: String,
    val time: String,
    val listFood: MutableList<MutableMap<String,Int>>
)

//@Serializable
//data class ReservResponseRemote(
//    val phone_number: String,
//    val password: String
//)