package com.example.meteortracker.presentation.common.components.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Displays a navigation drawer with items for navigating to main screens.
 * Includes a close button to hide the drawer.
 */
@Composable
fun NavigationDrawer(
    navController: NavController,
    coroutineScope: CoroutineScope,
    drawerState: DrawerState
) {
    ModalDrawerSheet(
        drawerTonalElevation = 8.dp,
        modifier = Modifier.width(320.dp)
    ) {
        IconButton(
            onClick = { coroutineScope.launch { drawerState.close() } },
            modifier = Modifier
                .padding(top = 4.dp, end = 4.dp)
                .align(Alignment.End)
        ) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = "Close drawer",
                tint = colorScheme.primary
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            for (screen in Screen.entries - setOf(Screen.PieChart, Screen.LineChart)) {
                if (screen == Screen.Settings)
                    HorizontalDivider(
                        color = colorScheme.primary.copy(0.3f),
                        modifier = Modifier.padding(16.dp)
                    )
                NavigationDrawerItem(navController, coroutineScope, drawerState, screen)
            }
        }
    }
}