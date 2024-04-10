package com.example.meteortracker.presentation.dataList.components.card

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.meteortracker.data.Meteorite
import com.example.meteortracker.presentation.map.components.MapView

/**
 * Displays a dialog showing the location of a single [meteorite] on a map.
 * The dialog can be dismissed via [onClose]. Utilizes `MapView` to render the meteorite's location.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDialog(meteorite: Meteorite, onClose: () -> Unit) {
    BasicAlertDialog(
        onDismissRequest = onClose,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnClickOutside = true,
            dismissOnBackPress = true
        ),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.6f)
    ) {
        MapView(
            meteorites = listOf(meteorite),
            modifier = Modifier.clip(RoundedCornerShape(25.dp))
        )
    }
}