package com.example.meteortracker.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meteortracker.R
import com.example.meteortracker.presentation.common.components.navigation.Screen
import com.example.meteortracker.presentation.home.components.DashboardButton
import com.example.meteortracker.util.ThemeManager

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val isDarkTheme = ThemeManager.getTheme(context)
    val heroImage = ThemeManager.getHeroImage(context, isDarkTheme)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = heroImage),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 48.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            DashboardButton(
                image = R.drawable.list_image,
                title = stringResource(id = R.string.meteorite_landings),
                color = colorScheme.primary,
                description = stringResource(id = R.string.welcome_text),
                onClick = {
                    navController.navigate(Screen.Meteorites.name)
                }
            )
            DashboardButton(
                image = R.drawable.pin_image,
                title = stringResource(id = R.string.meteorite_map),
                description = stringResource(id = R.string.map_text),
                color = colorScheme.tertiary,
                onClick = {
                    navController.navigate(Screen.MeteoriteMap.name)
                }
            )
            DashboardButton(
                image = R.drawable.chart_image,
                title = stringResource(id = R.string.statistics),
                color = colorScheme.outline,
                description = stringResource(id = R.string.stats_text),
                onClick = {
                    navController.navigate(Screen.Statistics.name)
                }
            )
        }
    }
}