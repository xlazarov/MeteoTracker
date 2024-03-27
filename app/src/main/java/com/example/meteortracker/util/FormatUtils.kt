package com.example.meteortracker.util

import java.text.NumberFormat

fun formatNumber(number: Number): String {
    val formatter = NumberFormat.getNumberInstance()
    formatter.isGroupingUsed = true
    formatter.maximumFractionDigits = 2
    return formatter.format(number)
}