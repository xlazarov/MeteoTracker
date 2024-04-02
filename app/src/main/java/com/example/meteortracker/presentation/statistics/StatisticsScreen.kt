package com.example.meteortracker.presentation.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meteortracker.R
import com.example.meteortracker.presentation.common.components.DashboardButton
import com.example.meteortracker.presentation.common.components.HeroBackgroundWrapper
import com.example.meteortracker.presentation.common.components.navigation.Screen

/**
 * Statistics screen, offering navigation to either a pie chart or line chart visualization
 * of meteorite data.
 */
@Composable
fun StatisticsScreen(
    navController: NavController
) {
    HeroBackgroundWrapper {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(62.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            DashboardButton(
                imageId = R.drawable.piechart_image,
                title = stringResource(id = R.string.per_class),
                color = colorScheme.secondary,
                description = stringResource(id = R.string.pie_chart),
                modifier = Modifier.padding(vertical = 24.dp)
            ) {
                navController.navigate(Screen.PieChart.name)
            }
            DashboardButton(
                imageId = R.drawable.linechart_image,
                title = stringResource(id = R.string.per_year),
                color = colorScheme.secondary,
                description = stringResource(id = R.string.line_chart),
                modifier = Modifier.padding(vertical = 24.dp)
            ) {
                navController.navigate(Screen.LineChart.name)
            }
        }
    }
}