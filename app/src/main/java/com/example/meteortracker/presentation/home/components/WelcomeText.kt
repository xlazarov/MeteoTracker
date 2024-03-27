package com.example.meteortracker.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.meteortracker.R

@Composable
fun WelcomeText(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "MeteoriteTracker",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = stringResource(id = R.string.welcome_text),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}