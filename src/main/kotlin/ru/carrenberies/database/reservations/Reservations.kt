package ru.carrenberies.database.reservations

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Reservations: Table() {
    private val id_book = Reservations.integer("id_book")
    private val id_table = Reservations.integer("id_table")
    private val id_user = Reservations.integer("id_user")
    private val id_rest = Reservations.integer("id_rest")
    private val data = Reservations.varchar("data",45)
    private val time = Reservations.varchar("time",10)
    private val duration_time = Reservations.varchar("duration_time",10)
    private val count_vis = Reservations.integer("count_vis")

    fun insert(reservationDTO: ReservationDTO) {
        transaction {
            Reservations.insert {
                it[id_book] = reservationDTO.id_book
                it[id_table] = reservationDTO.id_table
                it[id_user] = reservationDTO.id_user
                it[id_rest] = reservationDTO.id_rest
                it[data] = reservationDTO.data
                it[time] = reservationDTO.time
                it[duration_time] = reservationDTO.duration_time
                it[count_vis] = reservationDTO.count_vis
            }
        }
    }

    fun fetchReservationByUserId(id_user: Int): List<ReservationDTO> {
        return transaction {
            (Reservations)
                .select {Reservations.id_user eq id_user}
                .map {
                    ReservationDTO(
                        data = it[data],
                        id_rest = it[id_rest]
                    )
                }
        }
    }


}