package com.example.meteortracker.presentation.statistics

import androidx.compose.foundation.layout.Arrangement
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

@Composable
fun ChartScreen(
    content: @Composable () -> Unit,
    data: String,
    viewModel: MeteoriteChartViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.observeAsState(false)

    viewModel.chartSetup(
        filter = MeteoriteFilter(yearFrom = "1900", yearTo = "2000"),
        data = data
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        YearRangeSelector(
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
            CircularProgressIndicator()
        } else {
            content()
        }
    }
}