package com.example.meteortracker.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TextCard(
    title: String,
    mainCard: Boolean = false,
    description: String,
    buttonText: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = modifier,
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp)
        ) {
            Text(
                text = title,
                style = if (mainCard) typography.titleLarge.copy(fontWeight = FontWeight.Bold) else
                    typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = colorScheme.primary
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = description,
                style = typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                onClick = onClick
            ) {
                Text(
                    text = buttonText,
                    style = typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                )
            }
        }
    }
}