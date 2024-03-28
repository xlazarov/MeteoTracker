package com.example.meteortracker.util


import android.content.Context
import android.location.Geocoder
import android.os.Build
import android.util.Log
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
import com.example.meteortracker.data.Meteorite
import com.example.meteortracker.presentation.AboutScreen
import com.example.meteortracker.presentation.common.components.navigation.Screen
import com.example.meteortracker.presentation.dataList.DataListScreen
import com.example.meteortracker.presentation.home.HomeScreen
import com.example.meteortracker.presentation.map.MapScreen
import com.example.meteortracker.presentation.settings.SettingsScreen
import com.example.meteortracker.presentation.statistics.ChartScreen
import com.example.meteortracker.presentation.statistics.StatisticsScreen
import com.example.meteortracker.presentation.statistics.components.chart.LineChart
import com.example.meteortracker.presentation.statistics.components.chart.PieChart
import java.util.Locale

@Suppress("DEPRECATION")
fun Geocoder.getAddress(
    latitude: Double,
    longitude: Double,
    address: (android.location.Address?) -> Unit
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getFromLocation(latitude, longitude, 1) { address(it.firstOrNull()) }
    }
    try {
        address(getFromLocation(latitude, longitude, 1)?.firstOrNull())
    } catch (e: Exception) {
        address(null)
    }
}

fun getMeteoriteAddress(
    context: Context,
    meteorite: Meteorite,
    setCountryInfo: (String, String, String) -> Unit
) {
    val locale = Locale(LocaleManager.get(context))
    return Geocoder(context, locale)
        .getAddress(meteorite.reclat, meteorite.reclong) { address: android.location.Address? ->
            address?.let {
                Log.i("address", it.toString())
                setCountryInfo(it.countryName, it.adminArea, it.locality)
            }
        }
}

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
