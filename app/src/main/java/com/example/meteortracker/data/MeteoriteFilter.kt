package com.example.meteortracker.data

data class MeteoriteFilter(
    val nameQuery: String? = null,
    val yearFrom: String? = null,
    val yearTo: String? = null,
    val year: String? = null,
    val massMin: String? = null,
    val massMax: String? = null,
    val nameType: String? = null,
    val fall: String? = null,
    val country: String? = null
)