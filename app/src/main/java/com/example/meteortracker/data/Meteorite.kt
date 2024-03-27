package com.example.meteortracker.data

data class Meteorite(
    val id: String,
    val name: String,
    val type: String,
    val recclass: String,
    val mass: Number?,
    val fall: String,
    val year: String,
    val reclat: Double,
    val reclong: Double
)