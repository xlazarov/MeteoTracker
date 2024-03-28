package com.example.meteortracker.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meteortracker.R
import com.example.meteortracker.presentation.common.components.navigation.Screen
import com.example.meteortracker.presentation.home.components.DashboardButton
import com.example.meteortracker.presentation.home.components.WelcomeText

@Composable
fun HomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.hero3),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WelcomeText()
            Spacer(modifier = Modifier.height(48.dp))
            Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                DashboardButton(
                    title = stringResource(id = R.string.meteorite_map),
                    description = stringResource(id = R.string.dashbutton_map),
                    onClick = {
                        navController.navigate(Screen.MeteoriteMap.name)
                    }
                )
                DashboardButton(
                    title = stringResource(id = R.string.meteorite_data),
                    description = stringResource(id = R.string.dashbutton_data),
                    onClick = {
                        navController.navigate(Screen.Meteorites.name)
                    }
                )
                DashboardButton(
                    title = stringResource(id = R.string.statistics),
                    description = stringResource(id = R.string.dashbutton_stats),
                    onClick = {
                        navController.navigate(Screen.Statistics.name)
                    }
                )
            }
        }
    }
}
