package com.example.meteortracker.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meteortracker.R
import com.example.meteortracker.presentation.common.components.DashboardButton
import com.example.meteortracker.presentation.common.components.HeroBackgroundWrapper
import com.example.meteortracker.presentation.common.components.navigation.Screen

/**
 * The home screen of the app, presenting a dashboard with options to navigate to different features.
 */
@Composable
fun HomeScreen(navController: NavController) {
    HeroBackgroundWrapper {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 48.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            DashboardButton(
                imageId = R.drawable.list_image,
                title = stringResource(id = R.string.meteorite_landings),
                color = colorScheme.primary,
                description = stringResource(id = R.string.welcome_text),
                onClick = {
                    navController.navigate(Screen.Meteorites.name)
                }
            )
            DashboardButton(
                imageId = R.drawable.pin_image,
                title = stringResource(id = R.string.meteorite_map),
                description = stringResource(id = R.string.map_text),
                color = colorScheme.tertiary,
                onClick = {
                    navController.navigate(Screen.MeteoriteMap.name)
                }
            )
            DashboardButton(
                imageId = R.drawable.piechart_image,
                title = stringResource(id = R.string.statistics),
                color = colorScheme.secondary,
                description = stringResource(id = R.string.stats_text),
                onClick = {
                    navController.navigate(Screen.Statistics.name)
                }
            )
        }
    }
}