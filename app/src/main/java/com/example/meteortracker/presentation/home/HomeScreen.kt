package com.example.meteortracker.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
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
import com.example.meteortracker.presentation.home.components.WelcomeText
import com.example.meteortracker.util.ThemeManager

@Composable
fun HomeScreen(navController: NavController) {
    val isDarkTheme = ThemeManager.get(LocalContext.current)
    val heroImage by rememberUpdatedState(newValue =
    if (isDarkTheme) R.drawable.hero3 else R.drawable.hero_light)

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = heroImage),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            WelcomeText()
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly) {
                DashboardButton(
                    title = stringResource(id = R.string.meteorite_map),
                    description = stringResource(id = R.string.dashbutton_map),
                    onClick = {
                        navController.navigate(Screen.MeteoriteMap.name)
                    },
                    modifier = Modifier.align(Alignment.Start)
                )
                DashboardButton(
                    title = stringResource(id = R.string.meteorite_data),
                    description = stringResource(id = R.string.dashbutton_data),
                    onClick = {
                        navController.navigate(Screen.Meteorites.name)
                    },
                    modifier = Modifier.align(Alignment.End)
                )
                DashboardButton(
                    title = stringResource(id = R.string.statistics),
                    description = stringResource(id = R.string.dashbutton_stats),
                    onClick = {
                        navController.navigate(Screen.Statistics.name)
                    },
                    modifier = Modifier.align(Alignment.Start)
                )
            }
        }
    }
}
