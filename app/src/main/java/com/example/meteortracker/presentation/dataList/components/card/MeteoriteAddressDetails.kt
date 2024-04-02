package com.example.meteortracker.presentation.dataList.components.card

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.meteortracker.R
import com.example.meteortracker.data.Meteorite
import com.example.meteortracker.util.getMeteoriteAddress

/**
 * Displays geographical details (country, administrative area, and locality) for a given [meteorite].
 * Utilizes asynchronous data fetching to retrieve address information based on the meteorite's location.
 */
@Composable
fun MeteoriteAddressDetails(meteorite: Meteorite) {
    Spacer(modifier = Modifier.height(16.dp))

    val context = LocalContext.current

    val addressState =
        produceState<Triple<String?, String?, String?>>(Triple(null, null, null), meteorite) {
            getMeteoriteAddress(context, meteorite) { countryName, adminArea, localityName ->
                value = Triple(countryName, adminArea, localityName)
            }
        }

    val (country, area, locality) = addressState.value

    country?.let {
        MeteoriteDetail(
            description = stringResource(id = R.string.country),
            value = it
        )
    }
    area?.let {
        MeteoriteDetail(
            description = stringResource(id = R.string.admin_area),
            value = it
        )
    }
    locality?.let {
        MeteoriteDetail(
            description = stringResource(id = R.string.locality),
            value = it
        )
    }
}