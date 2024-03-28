package com.example.meteortracker.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.meteortracker.R

@Composable
fun WelcomeText(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(
                color = colorScheme.background,
                shape = RoundedCornerShape(2)
            )
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "MeteoriteTracker",
            style = MaterialTheme.typography.headlineLarge,
            color = colorScheme.secondary
        )
        Text(
            text = stringResource(id = R.string.welcome_text),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}