package ru.carrenberies.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.carrenberies.database.users.Users
import java.util.*

class LoginController(private val call: ApplicationCall) {

//    suspend fun performLogin() {
//        val receive = call.receive<LoginReceiveRemote>()
//        val userDTO = Users.fetchUser(receive.login)
//
//        println("receive -> $receive, dto -> $userDTO")
//
//        if (userDTO == null) {
//            call.respond(HttpStatusCode.BadRequest, "User not found")
//        } else {
//            if (userDTO.password == receive.password) {
//                val token = UUID.randomUUID().toString()
//                Tokens.insert(
//                    TokenDTO(
//                        rowId = 0,
//                        login = receive.login,
//                        token = token
//                    )
//                )
//                call.respond(LoginResponseRemote(token = token, login = receive.login.toString()))
//            } else {
//                call.respond(HttpStatusCode.BadRequest, "Invalid password")
//            }
//        }
//    }

    suspend fun checkUser() {
        val receive = call.receive<LoginReceiveRemote>()


    }
}