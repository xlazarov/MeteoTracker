package com.example.meteortracker.presentation.dataList.components.card

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.meteortracker.R
import com.example.meteortracker.data.Meteorite
import com.example.meteortracker.util.formatNumber

/**
 * Presents detailed information about a [meteorite].
 * Each detail is displayed using the MeteoriteDetail component for consistency.
 */
@Composable
fun MeteoriteDetails(meteorite: Meteorite) {
    HorizontalDivider(
        modifier = Modifier.padding(vertical = 16.dp),
        color = colorScheme.onSecondaryContainer.copy(0.1f)
    )

    MeteoriteDetail(
        description = stringResource(id = R.string.type),
        value = stringResource(
            id =
            if (meteorite.type == "Valid") R.string.valid else R.string.relict
        )
    )
    MeteoriteDetail(
        description = stringResource(id = R.string.recclass),
        value = meteorite.recclass
    )
    meteorite.mass?.let {
        MeteoriteDetail(
            description = stringResource(id = R.string.mass),
            value = "${formatNumber(meteorite.mass)} g"
        )
    }
    MeteoriteDetail(
        description = stringResource(id = R.string.fall),
        value = stringResource(
            id = if (meteorite.fall == "Fell") R.string.fell else R.string.found
        )
    )
    MeteoriteDetail(
        description = stringResource(id = R.string.year),
        value = meteorite.year.substring(0, 4)
    )
    HorizontalDivider(
        modifier = Modifier.padding(vertical = 16.dp),
        color = colorScheme.onSecondaryContainer.copy(0.1f)
    )

    MeteoriteDetail(
        description = stringResource(id = R.string.latitude),
        value = "${meteorite.reclat}°"
    )
    MeteoriteDetail(
        description = stringResource(id = R.string.longitude),
        value = "${meteorite.reclong}°"
    )
    MeteoriteDetail(
        description = stringResource(id = R.string.coordinates),
        value = "(${meteorite.reclat},  "
                + "${meteorite.reclong})"
    )

    MeteoriteAddressDetails(meteorite = meteorite)
}

@Composable
fun MeteoriteDetail(
    description: String,
    value: String
) =
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "$description: ",
            style = typography.bodyMedium,
            color = colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
            modifier = Modifier.weight(0.5f)
        )
        Text(
            text = value,
            style = typography.bodyMedium,
            color = colorScheme.onPrimaryContainer,
            modifier = Modifier.weight(0.5f)
        )
    }