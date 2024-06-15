package ru.carrenberies.features.restaurants

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.carrenberies.features.reservations.ReservController

fun Application.configureRestRouting() {
    routing {
        post("/getRestInfo") {
            RestController(call).getRestInfo()
        }

        post("/getRestFoodTypes") {
            RestController(call).getFoodTypes()
        }
    }
}