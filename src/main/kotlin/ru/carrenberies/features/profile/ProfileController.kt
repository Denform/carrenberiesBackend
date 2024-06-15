package ru.carrenberies.features.profile

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.carrenberies.database.reservations.Reservations
import ru.carrenberies.database.restaurants.Restaurants
import ru.carrenberies.database.users.Users

class ProfileController(private val call: ApplicationCall) {
    suspend fun getUserData() {

        val profileReceiveRemote = call.receive<ProfileReceiveRemote>()
        val userDTO = Users.fetchUserBuId(profileReceiveRemote.id_user)
        val reservationDTO = Reservations.fetchReservationByUserId(profileReceiveRemote.id_user)
        val date_info: MutableList<String> = mutableListOf()
        val rest_info: MutableList<String> = mutableListOf()

        for (item in reservationDTO) {
            date_info.add(item.data)
            rest_info.add(Restaurants.findRestByRestId(item.id_rest)!!.name)
        }


        if (userDTO != null) {
            call.respond(ProfileResponseRemote(
                id_user = userDTO.id_user,
                first_name = userDTO.first_name,
                second_name = userDTO.second_name,
                phone_number = userDTO.phone_number,
                rating = userDTO.rating,
                date_info = date_info,
                rest_info = rest_info
            ))
        }
    }
}