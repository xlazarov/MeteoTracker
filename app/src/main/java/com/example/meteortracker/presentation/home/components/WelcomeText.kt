package com.example.meteortracker.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.meteortracker.R

@Composable
fun WelcomeText(modifier: Modifier = Modifier) {
    Card(modifier = modifier, shape = RectangleShape) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "MeteoTracker",
                style = typography.titleLarge,
                color = colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.welcome_text),
                style = typography.bodyLarge
            )
        }
    }
}