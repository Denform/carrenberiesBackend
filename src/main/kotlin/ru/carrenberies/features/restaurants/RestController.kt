package ru.carrenberies.features.restaurants

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import ru.carrenberies.database.list_food.List_food
import ru.carrenberies.database.rest_menu.Rest_menu
import ru.carrenberies.database.restaurants.Restaurants

class RestController(private val call: ApplicationCall) {

    suspend fun getRestInfo() {
        val restReceiveRemote = call.receive<RestReceiveRemote>()
        val restaurantsDTO = Restaurants.findRestByRestId(restReceiveRemote.id_rest)

        try {
            if (restaurantsDTO != null) {
                call.respond(RestResponseRemote(
                    name = restaurantsDTO.name,
                    address = restaurantsDTO.address,
                    description = restaurantsDTO.description,
                    open_time = restaurantsDTO.open_time,
                    email = restaurantsDTO.email,
                    INN = restaurantsDTO.INN,
                    account_number = restaurantsDTO.account_number
                ))
            } else {
                call.respond(HttpStatusCode.Conflict, "Rest not found")
            }

        } catch (e: ExposedSQLException) {
            call.respond(HttpStatusCode.Conflict, "400")
        }
    }

    suspend fun getFoodTypes() {
        val restReceiveRemote = call.receive<RestReceiveRemote>()
        val restMenudto = Rest_menu.findFoodByRestId(restReceiveRemote.id_rest)
        val menuSet: MutableSet<String> = mutableSetOf()

        for (item in restMenudto) {
            val foodType = List_food.findFoodById(item.id_food)?.food_type
            if (foodType != null) {
                menuSet.add(foodType)
            }
        }

        try {
            call.respond(menuSet.toList()) // преобразуем Set обратно в List и отправляем результат
        } catch (e: ExposedSQLException) {
            call.respond(HttpStatusCode.Conflict, "400")
        }
    }
}