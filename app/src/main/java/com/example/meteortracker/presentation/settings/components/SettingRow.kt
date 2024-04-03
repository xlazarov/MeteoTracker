package com.example.meteortracker.presentation.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * A reusable row for settings, displaying an [icon], a [title], and custom [content]
 */
@Composable
fun SettingRow(icon: ImageVector, title: String, content: @Composable () -> Unit? = {}) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Icon(
                imageVector = icon,
                contentDescription = "Setting Icon",
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "$title:",
                color = MaterialTheme.colorScheme.primary
            )
        }
        content()
    }
}