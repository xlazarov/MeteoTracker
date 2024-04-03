package com.example.meteortracker.presentation.common.components.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.meteortracker.util.getIcon
import com.example.meteortracker.util.getName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Represents an individual item in the navigation drawer, configured for navigation to the [screen] specified.
 * When clicked, navigates to the associated screen and closes the drawer.
 */
@Composable
fun NavigationDrawerItem(
    navController: NavController,
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
    screen: Screen
) {
    NavigationDrawerItem(
        label = { Text(text = screen.getName()) },
        selected = navController.currentDestination?.navigatorName == screen.name,
        icon = {
            Icon(
                imageVector = screen.getIcon(),
                contentDescription = "Screen Icon"
            )
        },
        colors = NavigationDrawerItemDefaults.colors(
          //  unselectedIconColor = colorScheme.primary,
          //  unselectedTextColor = colorScheme.primary
        ),
        onClick = {
            navController.navigate(screen.name)
            coroutineScope.launch { drawerState.close() }
        }
    )
}