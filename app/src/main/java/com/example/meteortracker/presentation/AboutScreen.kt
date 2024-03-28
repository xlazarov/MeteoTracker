package com.example.meteortracker.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "About Meteorite Tracker", style = typography.titleLarge)
        HorizontalDivider()
        Text(
            text = "Meteorite Explorer is an application that leverages open data provided by NASA to offer users insights into meteorite landings across the globe. Utilizing the comprehensive datasets available through NASA's open data portal, the app aims to educate and engage space enthusiasts and researchers alike.",
            style = typography.bodyLarge
        )
        Text(
            text = "Data Attribution",
            style = typography.titleMedium
        )
        HorizontalDivider()
        Text(
            text = "The meteorite landing data used in this app is sourced from NASA's open data portal, specifically the Meteorite Landings dataset, which includes details on meteorite landings documented around the world.",
            style = typography.bodyLarge
        )
        Text(
            text = "For more information on the dataset and the API used to retrieve this data:",
            style = typography.bodyLarge
        )
        LinkText(
            text = "Meteorite Landings Dataset",
            url = "https://data.nasa.gov/Space-Science/Meteorite-Landings/gh4g-9sfh/about_data"
        )
        LinkText(
            text = "NASA Open Data API (Socrata)",
            url = "https://dev.socrata.com/foundry/data.nasa.gov/y77d-th95"
        )
        Text(
            text = "Legal and Privacy Information",
            style = typography.titleMedium
        )
        HorizontalDivider()
        Text(
            text = "This app is developed for educational purposes and is not affiliated with NASA or Socrata. All data is attributed to their respective sources.",
            style = typography.bodyLarge
        )
    }
}

@Composable
fun LinkText(text: String, url: String) {
    val uriHandler = LocalUriHandler.current
    Text(
        text = text,
        modifier = Modifier.clickable { uriHandler.openUri(url) },
        color = colorScheme.primary,
        textDecoration = TextDecoration.Underline
    )
}