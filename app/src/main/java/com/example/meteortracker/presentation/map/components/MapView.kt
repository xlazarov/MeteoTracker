package com.example.meteortracker.presentation.map.components

import android.os.Build
import android.view.ContextThemeWrapper
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.meteortracker.R
import com.example.meteortracker.data.Meteorite
import com.example.meteortracker.presentation.dataList.components.MeteoriteDetails
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapView(
    meteorites: List<Meteorite>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var selectedMeteorite by remember { mutableStateOf<Meteorite?>(null) }
    val worldMapBounds = BoundingBox(85.0, 180.0, -85.0, -180.0)

    val view = remember {
        MapView(ContextThemeWrapper(context, R.style.Theme_MeteorTracker)).apply {
            setTileSource(TileSourceFactory.MAPNIK)
            minZoomLevel = 3.0
            setMultiTouchControls(true)
            setScrollableAreaLimitDouble(worldMapBounds)
            controller.setZoom(if (meteorites.size == 1) 6.0 else 3.0)
            controller.setCenter(
                if (meteorites.size == 1) GeoPoint(meteorites[0].reclat, meteorites[0].reclong)
                else GeoPoint(0.0, 0.0)
            )
        }
    }

    AndroidView(
        { view },
        modifier = modifier
    ) { mapView ->

        mapView.overlays.clear()

        mapView.isHorizontalMapRepetitionEnabled = false
        mapView.isVerticalMapRepetitionEnabled = false

        meteorites.forEach { meteorite ->
            val geoPoint = GeoPoint(meteorite.reclat, meteorite.reclong)

            val marker = Marker(mapView).apply {
                position = geoPoint
                setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)

                setOnMarkerClickListener { _, _ ->
                    selectedMeteorite = meteorite
                    true
                }
            }
            if (meteorite.reclat != 0.0 || meteorite.reclong != 0.0) {
                mapView.overlays.add(marker)
            }
        }
        mapView.invalidate()
    }
    selectedMeteorite?.let { meteorite ->
        BasicAlertDialog(
            onDismissRequest = { selectedMeteorite = null }
        ) {
            Card {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = meteorite.name, style = typography.titleLarge)
                    MeteoriteDetails(meteorite = meteorite)
                }
            }
        }
    }
}
