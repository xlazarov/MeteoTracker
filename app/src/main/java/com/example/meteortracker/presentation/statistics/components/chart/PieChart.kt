package com.example.meteortracker.presentation.statistics.components.chart

import android.view.ContextThemeWrapper
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
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
    chartName: String,
    dataName: String,
    color: Color = colorScheme.onBackground,
    modifier: Modifier = Modifier,
) {
    val chartData by viewModel.chartData.observeAsState(emptyList())
    if (chartData.isEmpty()) Text(text = stringResource(id = R.string.no_data))

    val maxEntrySize = if (chartData.size < 30) chartData.size else 30

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

                val dataSet = PieDataSet(entries, " - $dataName").apply {
                    colors = generateRandomColorList(entries.size)
                    setDrawValues(false)
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
                    textColor = color.toArgb()
                    verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
                    horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                    orientation = Legend.LegendOrientation.HORIZONTAL
                    setDrawInside(false)
                    isWordWrapEnabled = true
                }

                description.apply {
                    isEnabled = true
                    text = chartName
                    textColor = color.toArgb()
                }

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
        Color(r, g, b).toArgb()
    }
}