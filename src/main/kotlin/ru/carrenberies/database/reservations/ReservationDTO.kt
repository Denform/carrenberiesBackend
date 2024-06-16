package ru.carrenberies.database.reservations

class ReservationDTO (
    val id_book: Int,
    val id_table: Int,
    val id_user: Int,
    val id_rest: Int,
    var data: String,
    val time: String,
    val duration_time: Int,
    val count_vis: Int
) {
    constructor(id_rest: Int, data: String) : this(
        0,
        0,
        0,
        id_rest,
        data,
        "",
        0,
        0
    )

    constructor(id_book: Int) : this(
        id_book,
        0,
        0,
        0,
        "",
        "",
        0,
        0
    )
}