package ru.carrenberies.features.reservations

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureReservRouting() {
    routing {
        post("/book") {
            ReservController(call).book()
        }

        post("/setOrderFood") {
            ReservController(call).setOrderFood()
        }
    }
}