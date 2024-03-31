package com.example.meteortracker.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
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
import com.example.meteortracker.presentation.home.components.TextCard
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
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            TextCard(
                title = stringResource(id = R.string.meteorite_landings),
                mainCard = true,
                description = stringResource(id = R.string.welcome_text),
                buttonText = stringResource(id = R.string.go_to_data),
                onClick = {
                    navController.navigate(Screen.Meteorites.name)
                }
            )
            TextCard(
                title = stringResource(id = R.string.meteorite_map),
                description = stringResource(id = R.string.map_text),
                buttonText = stringResource(id = R.string.go_to_map),
                onClick = {
                    navController.navigate(Screen.MeteoriteMap.name)
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.End)
                    .padding(end = 8.dp)
            )
            TextCard(
                title = stringResource(id = R.string.statistics),
                description = stringResource(id = R.string.stats_text),
                buttonText = stringResource(id = R.string.go_to_stats),
                onClick = {
                    navController.navigate(Screen.Statistics.name)
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.Start)
                    .padding(start = 8.dp)
            )
        }
    }
}