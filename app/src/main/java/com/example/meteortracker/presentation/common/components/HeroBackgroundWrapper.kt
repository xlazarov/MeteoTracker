package com.example.meteortracker.presentation.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.meteortracker.util.ThemeManager

/**
 * Wraps [content] with a hero background image, automatically determining the theme to use.
 */
@Composable
fun HeroBackgroundWrapper(
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val isDarkTheme = ThemeManager.getTheme(context)
    val heroImage = ThemeManager.getHeroImage(context, isDarkTheme)

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = heroImage),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        content()
    }
}