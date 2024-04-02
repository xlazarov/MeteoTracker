package com.example.meteortracker.util

import android.content.Context
import android.location.Geocoder
import android.os.Build
import android.util.Log
import com.example.meteortracker.data.Meteorite
import java.util.Locale

@Suppress("DEPRECATION")
fun Geocoder.getAddress(
    latitude: Double,
    longitude: Double,
    address: (android.location.Address?) -> Unit
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getFromLocation(latitude, longitude, 1) { address(it.firstOrNull()) }
    }
    try {
        address(getFromLocation(latitude, longitude, 1)?.firstOrNull())
    } catch (e: Exception) {
        address(null)
    }
}

fun getMeteoriteAddress(
    context: Context,
    meteorite: Meteorite,
    setCountryInfo: (String, String, String) -> Unit
) {
    val locale = Locale(LocaleManager.get(context))
    return Geocoder(context, locale)
        .getAddress(meteorite.reclat, meteorite.reclong) { address: android.location.Address? ->
            address?.let {
                Log.i("address", it.toString())
                setCountryInfo(it.countryName, it.adminArea, it.locality)
            }
        }
}