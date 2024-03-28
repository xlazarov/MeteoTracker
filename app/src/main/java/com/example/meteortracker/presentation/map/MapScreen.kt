package com.example.meteortracker.presentation.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meteortracker.data.MeteoriteFilter
import com.example.meteortracker.presentation.common.components.YearRangeSelector
import com.example.meteortracker.presentation.map.components.MapView
import com.example.meteortracker.presentation.map.viewModel.MeteoriteMapViewModel

@Composable
fun MapScreen(
    viewModel: MeteoriteMapViewModel = hiltViewModel()
) {
    val filter by viewModel.filter.observeAsState(MeteoriteFilter())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val meteorites by viewModel.mapData.observeAsState(emptyList())

    viewModel.setFilter(MeteoriteFilter(yearFrom = "2010", yearTo = "2024"),)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            MapView(meteorites = meteorites, modifier = Modifier.fillMaxSize())
        }
        YearRangeSelector(
            filter = filter,
            onClick = { yearFrom, yearTo ->
                viewModel.setFilter(
                    MeteoriteFilter(
                        yearFrom = yearFrom.ifBlank { null },
                        yearTo = yearTo.ifBlank { null }
                    )
                )
            },
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}