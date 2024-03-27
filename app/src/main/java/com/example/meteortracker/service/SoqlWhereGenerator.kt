package com.example.meteortracker.service

import com.example.meteortracker.data.MeteoriteFilter

fun getWhereQuery(filter: MeteoriteFilter): String {
    val queryParts = mutableListOf<String>()

    filter.year?.let {
        queryParts.add("year = '$it-01-01T00:00:00.000'")
    }

    filter.yearFrom?.let {
        queryParts.add("year >= '$it-01-01T00:00:00.000'")
    }

    filter.yearTo?.let {
        queryParts.add("year <= '$it-01-01T00:00:00.000'")
    }

    filter.massMin?.let {
        queryParts.add("mass >= $it")
    }

    filter.massMax?.let {
        queryParts.add("mass <= $it")
    }

    filter.nameType?.let {
        queryParts.add("nametype = '$it'")
    }

    filter.fall?.let {
        queryParts.add("fall = '$it'")
    }

    filter.nameQuery?.let {
        queryParts.add("starts_with(name, '${it}')")
    }
    return queryParts.joinToString(" AND ")
}
