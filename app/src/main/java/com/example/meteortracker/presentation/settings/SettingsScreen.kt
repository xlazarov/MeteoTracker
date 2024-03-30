package com.example.meteortracker.presentation.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Image
import androidx.compose.material.icons.twotone.InvertColors
import androidx.compose.material.icons.twotone.Language
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.meteortracker.R
import com.example.meteortracker.presentation.settings.components.HeroPicker
import com.example.meteortracker.presentation.settings.components.LanguageDropdown
import com.example.meteortracker.presentation.settings.components.SettingRow
import com.example.meteortracker.presentation.settings.components.ThemeSwitch

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row {
            SettingRow(
                icon = Icons.TwoTone.InvertColors,
                title = stringResource(id = R.string.mode)
            ) {
                ThemeSwitch()
            }
        }
        HorizontalDivider()

        Row {
            SettingRow(
                icon = Icons.TwoTone.Language,
                title = stringResource(id = R.string.language)
            ) {
                LanguageDropdown()
            }
        }
        HorizontalDivider()

        SettingRow(
            icon = Icons.TwoTone.Image,
            title = stringResource(id = R.string.home_background)
        )
        HeroPicker()
    }
}