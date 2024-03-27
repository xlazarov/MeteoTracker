package com.example.meteortracker.presentation.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meteortracker.presentation.statistics.components.YearRangeSelector
import com.example.meteortracker.presentation.statistics.viewModel.MeteoriteChartViewModel

@Composable
fun ChartScreen(
    content: @Composable () -> Unit,
    dataType: String,
    viewModel: MeteoriteChartViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.observeAsState(false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        YearRangeSelector(data = dataType)
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            content()
        }
    }
}