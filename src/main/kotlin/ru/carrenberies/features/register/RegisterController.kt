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
        if (!registerReceiveRemote.phone_number.isValidEmail()) {
            call.respond(HttpStatusCode.BadRequest, "Email is not Valid")
        }

        val userDTO = Users.fetchUser(registerReceiveRemote.phone_number)

        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists, UserId: ${userDTO.id_user}")
        } else {
            try {
                Users.insert(
                    UserDTO(
                        id_user = 0,
                        password = registerReceiveRemote.password,
                        phone_number = registerReceiveRemote.phone_number,
                        first_name = registerReceiveRemote.first_name,
                        second_name = "",
                        user_type = registerReceiveRemote.user_type,
                        rating = 0
                    )
                )
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            }

            call.respond(RegisterResponseRemote(phone_number = registerReceiveRemote.phone_number, password = registerReceiveRemote.password))
        }
    }
}