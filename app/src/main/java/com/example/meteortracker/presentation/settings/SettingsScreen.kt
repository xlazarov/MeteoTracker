package com.example.meteortracker.presentation.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.InvertColors
import androidx.compose.material.icons.twotone.Language
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.meteortracker.R
import com.example.meteortracker.presentation.settings.components.LanguageDropdown
import com.example.meteortracker.presentation.settings.components.ThemeSwitch

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Icon(
                    imageVector = Icons.TwoTone.InvertColors,
                    contentDescription = null,
                    tint = colorScheme.primary
                )
                Text(
                    text = stringResource(id = R.string.mode) + ":",
                    color = colorScheme.primary
                )
            }
            ThemeSwitch()
        }
        HorizontalDivider()
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Icon(
                    imageVector = Icons.TwoTone.Language,
                    contentDescription = null,
                    tint = colorScheme.primary
                )
                Text(
                    text = stringResource(id = R.string.language) + ":",
                    color = colorScheme.primary
                )
            }
            LanguageDropdown()
        }
    }
}



