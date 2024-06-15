package ru.carrenberies.database.restaurants

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Restaurants: Table() {
    val id_rest = Restaurants.integer("id_rest")
    val name = Restaurants.varchar("name",45)
    private val address = Restaurants.varchar("address",45)
    private val description = Restaurants.varchar("description",45)
    private val open_time = Restaurants.varchar("open_time",45)
    private val email = Restaurants.varchar("email",45)
    private val INN = Restaurants.varchar("INN",45)
    private val account_number = Restaurants.varchar("account_number",45)

    fun findRestByRestId(id_rest: Int): RestaurantsDTO? {
        return transaction {
            Restaurants.select { Restaurants.id_rest eq id_rest }
                .map {
                    RestaurantsDTO(
                        id_rest = id_rest,
                        name = it[name],
                        address = it[address],
                        description = it[description],
                        open_time = it[open_time],
                        email = it[email],
                        INN = it[INN],
                        account_number = it[account_number]
                    )
                }
                .singleOrNull()
        }
    }
}