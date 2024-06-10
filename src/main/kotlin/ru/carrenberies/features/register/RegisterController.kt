package ru.carrenberies.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import ru.carrenberies.database.Id_count
import ru.carrenberies.database.tokens.TokenDTO
import ru.carrenberies.database.tokens.Tokens
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

        val userDTO = Users.fetchUser(registerReceiveRemote.login)

        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {
            val token = UUID.randomUUID().toString()

            try {
                Users.insert(
                    UserDTO(
                        login = registerReceiveRemote.login,
                        password = registerReceiveRemote.password,
                        email = registerReceiveRemote.email,
                        username = ""
                    )
                )
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            }


            Tokens.insert(
                TokenDTO(
                    rowId = Id_count.max_id_token,
                    login = registerReceiveRemote.login,
                    token = token
                )

            )

            Id_count.max_id_token++
            call.respond(RegisterResponseRemote(token = token))
        }
    }
}