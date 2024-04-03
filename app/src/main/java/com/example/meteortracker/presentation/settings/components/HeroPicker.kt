package com.example.meteortracker.presentation.settings.components

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.meteortracker.util.ThemeManager

/**
 * Allows selection of a hero image for the current theme, refreshing UI on change.
 */
@Composable
fun HeroPicker() {
    val context = LocalContext.current
    val currentTheme = ThemeManager.getTheme(context)
    val currentHero = ThemeManager.getHeroImage(context, currentTheme)

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(ThemeManager.getHeroOptions(currentTheme)) { hero ->
            HeroImageCard(
                imageResId = hero,
                isSelected = hero == currentHero,
                onImageSelected = {
                    ThemeManager.setHeroImage(context, currentTheme, hero)
                    (context as? Activity)?.recreate()
                }
            )
        }
    }
}