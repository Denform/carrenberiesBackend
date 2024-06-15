package ru.carrenberies

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database
import ru.carrenberies.features.login.configureLoginRouting
import ru.carrenberies.features.profile.configureProfileRouting
import ru.carrenberies.features.register.configureRegisterRouting
import ru.carrenberies.features.reservations.configureReservRouting
import ru.carrenberies.features.restaurants.configureRestRouting
import ru.carrenberies.plugins.*

fun main() {
    Database.connect("jdbc:mysql://localhost:3306/carrenberies",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "Inform2001")

    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
    configureReservRouting()
    configureProfileRouting()
    configureRestRouting()
}
