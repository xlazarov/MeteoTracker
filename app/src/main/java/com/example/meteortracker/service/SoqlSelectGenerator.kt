package com.example.meteortracker.service

/**
 * Constructs a SELECT query part for database or API requests, based on the provided [select] parameter,
 * [select] is either 'recclass' or 'year', based on pie chart or line chart data entries.
 */
fun getSelectQuery(select: String): String {
    val data = when (select) {
        "recclass" -> "recclass"
        "year" -> "date_extract_y(year)"
        else -> return ""
    }
    return "$data AS data,count(*)"
}
