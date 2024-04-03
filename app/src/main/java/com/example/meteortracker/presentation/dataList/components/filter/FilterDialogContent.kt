package com.example.meteortracker.presentation.dataList.components.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FilterDialogContent() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterFall()
        HorizontalDivider()

        FilterNameType()
        HorizontalDivider()

        FilterYear()
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp))

        FilterMass()
    }
}

/**
 * Determines the filter string based on boolean selections for a given category (e.g., fall status or type).
 */
fun getFilter(
    first: Boolean,
    second: Boolean,
    firstLabel: String,
    secondLabel: String
) =
    when (first.compareTo(second)) {
        1 -> firstLabel
        -1 -> secondLabel
        else -> null
    }
