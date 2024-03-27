package com.example.meteortracker.presentation.map

import androidx.compose.foundation.layout.Box
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
import com.example.meteortracker.presentation.map.components.MapView
import com.example.meteortracker.presentation.map.components.YearSelector
import com.example.meteortracker.presentation.map.viewModel.MeteoriteMapViewModel

@Composable
fun MeteorMap(
    viewModel: MeteoriteMapViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.observeAsState(false)
    val meteorites by viewModel.mapData.observeAsState(emptyList())

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            MapView(meteorites = meteorites, modifier = Modifier.fillMaxSize())
        }
        YearSelector(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopEnd)
        )
    }
}