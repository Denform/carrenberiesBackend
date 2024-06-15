package ru.carrenberies.database.rest_menu

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import ru.carrenberies.database.reservations.ReservationDTO
import ru.carrenberies.database.reservations.Reservations

object Rest_menu: Table() {
    private val id_rest = Rest_menu.integer("id_rest")
    private val id_food = Rest_menu.integer("id_food")

    fun findFoodByRestId(id_rest: Int): List<Rest_menuDTO> {
        return transaction {
            (Rest_menu)
                .select { Rest_menu.id_rest eq id_rest}
                .map {
                    Rest_menuDTO(
                        id_rest = id_rest,
                        id_food = it[id_food]
                    )
                }
        }
    }
}