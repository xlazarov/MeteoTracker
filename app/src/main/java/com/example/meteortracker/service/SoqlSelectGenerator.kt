package com.example.meteortracker.service

fun getSelectQuery(select: String): String {
    val data = when (select) {
        "recclass" -> "recclass"
        "year" -> "date_extract_y(year)"
        else -> return ""
    }
    return "$data AS data,count(*)"
}
