package com.example.meteortracker.presentation.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meteortracker.data.MeteoriteFilter
import com.example.meteortracker.presentation.common.components.YearRangeSelector
import com.example.meteortracker.presentation.statistics.viewModel.MeteoriteChartViewModel

/**
 * Renders a screen for displaying meteorite chart visualizations based on selected criteria.
 * Allows for year range selection, triggering chart updates to reflect the chosen timeframe.
 * [content] defines the specific chart to be displayed, with [data] indicating the type of data visualization.
 */
@Composable
fun ChartScreen(
    content: @Composable () -> Unit,
    data: String,
    viewModel: MeteoriteChartViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.observeAsState(false)
    val filter by viewModel.filter.observeAsState(MeteoriteFilter())

    viewModel.chartSetup(
        filter = MeteoriteFilter(yearFrom = "2000", yearTo = "2024"),
        data = data
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        YearRangeSelector(
            filter = filter,
            onClick = { yearFrom, yearTo ->
                viewModel.chartSetup(
                    filter = MeteoriteFilter(
                        yearFrom = yearFrom.ifBlank { null },
                        yearTo = yearTo.ifBlank { null }
                    ),
                    data = data)
            }
        )
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            content()
        }
    }
}