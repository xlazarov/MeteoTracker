package com.example.meteortracker.presentation.statistics.components.chart

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meteortracker.data.ChartData
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
    modifier: Modifier = Modifier,
) {
    val chartData by viewModel.chartData.observeAsState(emptyList())

    Crossfade(targetState = chartData, label = "") { pieChartData ->
        AndroidView(factory = { context ->
            PieChart(context).apply {
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
                description.isEnabled = false
                isDrawHoleEnabled = false
                setDrawEntryLabels(false)
                setUsePercentValues(true)
            }
        },
            modifier = modifier.wrapContentSize(),
            update = { updatePieChartWithData(it, pieChartData, dataLabel) }
        )
    }
}

fun generateRandomColorList(size: Int): List<Int> {
    val random = Random()
    return List(size) {
        val r = random.nextInt(256)
        val g = random.nextInt(256)
        val b = random.nextInt(256)
        Color(r, g, b).toArgb()
    }
}

fun updatePieChartWithData(
    chart: PieChart,
    data: List<ChartData>,
    dataLabel: String
) {
    val maxEntrySize = if (data.size < 50) data.size else 50
    val entries: List<PieEntry> = data
        .map { PieEntry(it.count.toFloat(), it.data ?: "Unknown") }
        .sortedBy { it.value }
        .reversed()
        .subList(0, maxEntrySize)

    val dataSet = PieDataSet(entries, "\n$dataLabel")
    dataSet.colors = generateRandomColorList(data.size)
    chart.data = PieData(dataSet)

    chart.invalidate()
}