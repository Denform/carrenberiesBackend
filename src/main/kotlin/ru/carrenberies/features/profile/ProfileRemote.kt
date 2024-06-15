package ru.carrenberies.features.profile

import kotlinx.serialization.Serializable
import ru.carrenberies.database.reservations.ReservationDTO

@Serializable
class ProfileReceiveRemote (
    val id_user: Int
)

@Serializable
class ProfileResponseRemote(
    val id_user: Int,
    val first_name: String,
    val second_name: String,
    val phone_number: String,
    val rating: Int,
    val date_info: List<String>,
    val rest_info: List<String>
)