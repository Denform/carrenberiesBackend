package ru.carrenberies.features.profile

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureProfileRouting() {
    routing {
        post("/getUserData") {
//            val registerController = RegisterController(call)
//            registerController.registerNewUser()

            ProfileController(call).getUserData()
        }
    }
}