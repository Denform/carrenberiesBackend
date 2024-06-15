package ru.carrenberies.database.reservations

class ReservationDTO (
    val id_book: Int,
    val id_table: Int,
    val id_user: Int,
    val id_rest: Int,
    var data: String,
    val time: String,
    val duration_time: String,
    val count_vis: Int
) {
    constructor(id_rest: Int, data: String) : this(
        0,
        0,
        0,
        id_rest,
        data,
        "",
        "",
        0
    )
}