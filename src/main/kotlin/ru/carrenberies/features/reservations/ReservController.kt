package ru.carrenberies.features.reservations

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import ru.carrenberies.database.reservations.ReservationDTO
import ru.carrenberies.database.reservations.Reservations

class ReservController(private val call: ApplicationCall) {

    suspend fun book() {
        val reservReceiveRemote = call.receive<ReservReceiveRemote>()

        try {
            Reservations.insert(
                ReservationDTO(
                    id_book = 0,
                    id_table = reservReceiveRemote.id_table,
                    id_user = reservReceiveRemote.id_user,
                    id_rest = reservReceiveRemote.id_rest,
                    data = reservReceiveRemote.data,
                    time = reservReceiveRemote.time,
                    duration_time = reservReceiveRemote.duration_time,
                    count_vis = reservReceiveRemote.count_vis
                )
            )

        } catch (e: ExposedSQLException) {
            call.respond(HttpStatusCode.Conflict, "400")
        }

        call.respond("200")
    }
}