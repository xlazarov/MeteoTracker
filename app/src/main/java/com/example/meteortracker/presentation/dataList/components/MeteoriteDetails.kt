package com.example.meteortracker.presentation.dataList.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.meteortracker.R
import com.example.meteortracker.data.Meteorite
import com.example.meteortracker.util.formatNumber
import com.example.meteortracker.util.getMeteoriteAddress


@Composable
fun MeteoriteDetails(meteorite: Meteorite) {
    HorizontalDivider(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .alpha(0.7f)
    )
    Property(
        description = stringResource(id = R.string.type),
        value = stringResource(
            id =
            if (meteorite.type == "Valid") R.string.valid else R.string.relict
        )
    )
    Property(description = stringResource(id = R.string.recclass), value = meteorite.recclass)

    meteorite.mass?.let {
        Property(
            description = stringResource(id = R.string.mass),
            value = "${formatNumber(meteorite.mass)} g"
        )
    }

    Property(
        description = stringResource(id = R.string.fall),
        value = stringResource(
            id = if (meteorite.fall == "Fell") R.string.fell else R.string.found
        )
    )
    Property(
        description = stringResource(id = R.string.year),
        value = meteorite.year.substring(0, 4)
    )

    HorizontalDivider(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .alpha(0.7f)
    )
    Property(
        description = stringResource(id = R.string.latitude),
        value = "${meteorite.reclat}°"
    )
    Property(
        description = stringResource(id = R.string.longitude),
        value = "${meteorite.reclong}°"
    )
    Property(
        description = stringResource(id = R.string.coordinates),
        value = "(${meteorite.reclat},  "
                + "${meteorite.reclong})"
    )
    Spacer(modifier = Modifier.height(16.dp))

    var country: String? by remember { mutableStateOf(null) }
    var area: String? by remember { mutableStateOf(null) }
    var locality: String? by remember { mutableStateOf(null) }

    getMeteoriteAddress(
        LocalContext.current,
        meteorite
    ) { countryName, adminArea, localityName ->
        country = countryName
        area = adminArea
        locality = localityName
    }

    country?.let { Property(description = stringResource(id = R.string.country), value = it) }
    area?.let { Property(description = stringResource(id = R.string.admin_area), value = it) }
    locality?.let { Property(description = stringResource(id = R.string.locality), value = it) }
}


@Composable
fun Property(
    description: String,
    value: String
) =
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "$description: ",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
            modifier = Modifier.weight(0.5f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.weight(0.5f)
        )
    }