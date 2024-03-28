package com.example.meteortracker.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.meteortracker.ui.theme.nasa_url_color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopAppBar(
    drawerState: DrawerState,
    coroutineScope: CoroutineScope
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorScheme.primary)
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "MeteoTracker",
                style = typography.titleLarge,
                color = colorScheme.onPrimary,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = "DATA.NASA.GOV",
                style = typography.titleSmall,
                color = nasa_url_color,
                fontWeight = FontWeight.SemiBold
            )
        }
        IconButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = { coroutineScope.launch { drawerState.open() } }
        ) {
            Icon(
                imageVector = Icons.Rounded.Menu,
                contentDescription = null,
                modifier = Modifier.size(28.dp),
                tint = colorScheme.onPrimary
            )
        }
    }
}