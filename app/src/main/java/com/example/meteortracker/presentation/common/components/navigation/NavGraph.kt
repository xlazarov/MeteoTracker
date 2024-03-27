package com.example.meteortracker.presentation.common.components.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meteortracker.presentation.common.components.TopAppBar
import com.example.meteortracker.util.ScreenContent
import kotlinx.coroutines.CoroutineScope

/**
 * enum values that represent the screens in the app
 */
enum class Screen {
    Home,
    MeteoriteMap,
    Meteorites,
    Statistics,
    PieChart,
    LineChart,
    Settings,
    About
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) {
    ModalNavigationDrawer(
        drawerContent = {
            NavigationDrawer(navController, coroutineScope, drawerState)
        },
        drawerState = drawerState,
        gesturesEnabled = false
    ) {
        Scaffold(
            topBar = { TopAppBar(drawerState, coroutineScope) }
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.Home.name,
                modifier = Modifier.padding(it)
            ) {
                for (screen in Screen.entries) {
                    composable(route = screen.name) {
                        ScreenContent(screen,navController)
                    }
                }
            }
        }
    }
}