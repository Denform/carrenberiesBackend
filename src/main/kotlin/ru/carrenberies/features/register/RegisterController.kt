package ru.carrenberies.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import ru.carrenberies.database.users.UserDTO
import ru.carrenberies.database.users.Users
import ru.carrenberies.utils.isValidEmail
import java.util.*

class RegisterController(private val call: ApplicationCall) {

    suspend fun registerNewUser() {
        val registerReceiveRemote = call.receive<RegisterReceiveRemote>()
        if (!registerReceiveRemote.email.isValidEmail()) {
            call.respond(HttpStatusCode.BadRequest, "Email is not Valid")
        }

        val userDTO = Users.fetchUser(registerReceiveRemote.email)

        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists, UserId: ${userDTO.login}")
        } else {
            try {
                Users.insert(
                    UserDTO(
                        login = 0,
                        password = registerReceiveRemote.password,
                        email = registerReceiveRemote.email,
                        username = ""
                    )
                )
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            }

            call.respond(RegisterResponseRemote(email = registerReceiveRemote.email, password = registerReceiveRemote.password))
        }
    }
}