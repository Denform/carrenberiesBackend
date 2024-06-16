package ru.carrenberies.database.list_food

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object List_food: Table() {
    private val id_food = List_food.integer("id_food")
    private val food_name = List_food.varchar("food_name",45)
    private val food_type = List_food.varchar("food_type",45)
    private val food_desc = List_food.varchar("food_desc",45)
    private val price = List_food.integer("price")

    fun findFoodById(id_food: Int): List_foodDTO? {
        return transaction {
            List_food.select { List_food.id_food eq id_food }
                .map {
                    List_foodDTO(
                        id_food = id_food,
                        food_name = it[food_name],
                        food_type = it[food_type],
                        food_desc = it[food_desc],
                        price = it[price],
                    )
                }
                .singleOrNull()
        }
    }
}