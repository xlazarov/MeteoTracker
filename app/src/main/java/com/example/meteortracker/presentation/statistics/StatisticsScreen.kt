package com.example.meteortracker.presentation.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.PieChart
import androidx.compose.material.icons.twotone.SsidChart
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meteortracker.presentation.common.components.navigation.Screen

@Composable
fun StatisticsScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(48.dp)
    ) {
        ElevatedButton(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth(),
            shape = CardDefaults.shape,
            onClick = {
                navController.navigate(Screen.PieChart.name)
            }
        ) {
            Icon(imageVector = Icons.TwoTone.PieChart, contentDescription = null)
            Text(text = " Classification Pie Chart")
        }
        ElevatedButton(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth(),
            shape = CardDefaults.shape,
            onClick = {
                navController.navigate(Screen.LineChart.name)
            }
        ) {
            Icon(imageVector = Icons.TwoTone.SsidChart, contentDescription = null)
            Text(text = " Landings per Year Line Chart")
        }
    }
}