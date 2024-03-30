package com.example.meteortracker.presentation.settings.components

import android.app.Activity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.DarkMode
import androidx.compose.material.icons.twotone.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import com.example.meteortracker.util.ThemeManager

@Composable
fun ThemeSwitch(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var isDarkTheme = ThemeManager.getTheme(context)
    val modeIcon by rememberUpdatedState(
        newValue =
        if (isDarkTheme) Icons.TwoTone.DarkMode else Icons.TwoTone.LightMode
    )

    Switch(
        checked = isDarkTheme,
        onCheckedChange = { isChecked ->
            isDarkTheme = isChecked
            ThemeManager.setTheme(context, isChecked)
            (context as? Activity)?.recreate()
        },
        thumbContent = {
            Icon(imageVector = modeIcon, contentDescription = null)
        },
        colors = SwitchDefaults.colors(
            uncheckedIconColor = colorScheme.onPrimary,
            uncheckedThumbColor = colorScheme.primary,
            uncheckedTrackColor = colorScheme.primaryContainer,
            uncheckedBorderColor = colorScheme.primaryContainer
        ),
        modifier = modifier.scale(1.2f)
    )
}