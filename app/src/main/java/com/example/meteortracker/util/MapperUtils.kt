package com.example.meteortracker.util


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.automirrored.rounded.ShowChart
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.QuestionMark
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.meteortracker.R
import com.example.meteortracker.presentation.AboutScreen
import com.example.meteortracker.presentation.common.components.navigation.Screen
import com.example.meteortracker.presentation.dataList.DataListScreen
import com.example.meteortracker.presentation.HomeScreen
import com.example.meteortracker.presentation.map.MapScreen
import com.example.meteortracker.presentation.settings.SettingsScreen
import com.example.meteortracker.presentation.statistics.ChartScreen
import com.example.meteortracker.presentation.statistics.StatisticsScreen
import com.example.meteortracker.presentation.statistics.components.chart.LineChart
import com.example.meteortracker.presentation.statistics.components.chart.PieChart

fun Screen.getIcon() = when (this) {
    Screen.Home -> Icons.Rounded.Home
    Screen.MeteoriteMap -> Icons.Rounded.Map
    Screen.Meteorites -> Icons.AutoMirrored.Rounded.List
    Screen.Statistics -> Icons.AutoMirrored.Rounded.ShowChart
    Screen.Settings -> Icons.Rounded.Settings
    else -> Icons.Rounded.QuestionMark
}

@Composable
fun Screen.getName() = when (this) {
    Screen.Home -> stringResource(id = R.string.home)
    Screen.MeteoriteMap -> stringResource(id = R.string.meteorite_map)
    Screen.Meteorites -> stringResource(id = R.string.meteorite_data)
    Screen.Statistics -> stringResource(id = R.string.statistics)
    Screen.Settings -> stringResource(id = R.string.settings)
    Screen.About -> stringResource(id = R.string.about)
    else -> ""
}


@Composable
fun ScreenContent(screen: Screen, navController: NavController) = when (screen) {
    Screen.Home -> HomeScreen(navController)
    Screen.MeteoriteMap -> MapScreen()
    Screen.Meteorites -> DataListScreen()
    Screen.Statistics -> StatisticsScreen(navController)
    Screen.PieChart -> ChartScreen(
        data = "recclass",
        content = {
            PieChart(
                dataName = stringResource(id = R.string.recclass),
                chartName = stringResource(id = R.string.per_class)
            )
        }
    )

    Screen.LineChart -> ChartScreen(
        data = "year",
        content = {
            LineChart(
                dataName = stringResource(id = R.string.year),
                chartName = stringResource(id = R.string.meteorite_landings)
            )
        }
    )

    Screen.Settings -> SettingsScreen()
    Screen.About -> AboutScreen()
}
