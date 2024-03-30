package com.example.meteortracker.presentation.settings.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ImageCard(imageResId: Int, isSelected: Boolean, onImageSelected: (Int) -> Unit) {
    Card(
        modifier = Modifier.height(100.dp)
            .clickable { onImageSelected(imageResId) },
        elevation = CardDefaults.cardElevation(6.dp),
        border = if (isSelected) BorderStroke(4.dp, colorScheme.primary) else null
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}
