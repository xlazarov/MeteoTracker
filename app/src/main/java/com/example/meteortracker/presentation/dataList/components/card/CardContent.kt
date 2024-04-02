package com.example.meteortracker.presentation.dataList.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.meteortracker.R
import com.example.meteortracker.data.Meteorite

/**
 * Displays information about a [meteorite] and provides a button to show its location on a map.
 */
@Composable
fun MeteoriteCardContent(meteorite: Meteorite, onShowMap: () -> Unit) {
    MeteoriteDetails(meteorite = meteorite)
    Spacer(modifier = Modifier.height(24.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Button(
            onClick = onShowMap
        ) {
            Icon(
                imageVector = Icons.TwoTone.LocationOn,
                contentDescription = "Show on Map"
            )
            Text(text = " " + stringResource(id = R.string.show_map))
        }
    }
}