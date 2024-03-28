package com.example.meteortracker.presentation.statistics.components.chart

import android.view.ContextThemeWrapper
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meteortracker.R
import com.example.meteortracker.presentation.statistics.viewModel.MeteoriteChartViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import java.util.Random

@Composable
fun PieChart(
    viewModel: MeteoriteChartViewModel = hiltViewModel(),
    dataLabel: String,
    descriptionText: String,
    modifier: Modifier = Modifier,
) {
    val chartData by viewModel.chartData.observeAsState(emptyList())
    val maxEntrySize = if (chartData.size < 50) chartData.size else 50

    AndroidView(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        factory = { context ->
            val chart = PieChart(ContextThemeWrapper(context, R.style.Theme_MeteorTracker)).apply {
                val entries: List<PieEntry> = chartData
                    .map { PieEntry(it.count.toFloat(), it.data ?: "Unknown") }
                    .sortedBy { it.value }
                    .reversed()
                    .subList(0, maxEntrySize)

                val dataSet = PieDataSet(entries, dataLabel).apply {
                    colors = generateRandomColorList(entries.size)
                }
                data = PieData(dataSet)

                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                )
                legend.apply {
                    isEnabled = true
                    form = Legend.LegendForm.CIRCLE
                    textSize = 12f
                    verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
                    horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                    orientation = Legend.LegendOrientation.HORIZONTAL
                    setDrawInside(false)
                    isWordWrapEnabled = true
                }

                description.isEnabled = true
                description.text = descriptionText
                isDrawHoleEnabled = false
                setDrawEntryLabels(false)
                setUsePercentValues(true)

                setTouchEnabled(true)

                invalidate()
            }
            chart
        }
    )
}

private fun generateRandomColorList(size: Int): List<Int> {
    val random = Random()
    return List(size) {
        val r = random.nextInt(200)
        val g = random.nextInt(180)
        val b = random.nextInt(256)
        Color(r, g, b, 200).toArgb()
    }
}