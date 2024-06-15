package ru.carrenberies.features.reservations

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureReservRouting() {
    routing {
        post("/book") {
//            val registerController = RegisterController(call)
//            registerController.registerNewUser()

            ReservController(call).book()
        }
    }
}