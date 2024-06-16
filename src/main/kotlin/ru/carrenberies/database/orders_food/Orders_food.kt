package ru.carrenberies.database.orders_food

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import ru.carrenberies.database.reservations.Reservations

object Orders_food: Table() {
    private val id_food = Orders_food.integer("id_food")
    private val id_book = Orders_food.integer("id_book")
    private val count_portion = Orders_food.integer("count_portion")

    fun insert(orders_foodDTO: Orders_foodDTO) {
        transaction {
            Orders_food.insert {
                it[id_food] = orders_foodDTO.id_food
                it[id_book] = orders_foodDTO.id_book
                it[count_portion] = orders_foodDTO.count_portion
            }
        }
    }
}

