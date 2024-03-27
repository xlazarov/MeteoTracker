package com.example.meteortracker.presentation.statistics.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meteortracker.R
import com.example.meteortracker.data.MeteoriteFilter
import com.example.meteortracker.presentation.statistics.viewModel.MeteoriteChartViewModel

@Composable
fun YearRangeSelector(
    viewModel: MeteoriteChartViewModel = hiltViewModel(),
    data: String,
    modifier: Modifier = Modifier
) {
    val filter by viewModel.filter.observeAsState(MeteoriteFilter())

    var yearFrom by remember { mutableStateOf(filter.yearFrom ?: "") }
    var yearTo by remember { mutableStateOf(filter.yearTo ?: "") }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        OutlinedTextField(
            value = yearFrom,
            onValueChange = { yearFrom = it },
            label = { Text(stringResource(id = R.string.year_from)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.weight(0.4f),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorScheme.background,
                unfocusedContainerColor = colorScheme.background
            )
        )
        OutlinedTextField(
            value = yearTo,
            onValueChange = { yearTo = it },
            label = { Text(stringResource(id = R.string.year_to)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.weight(0.4f),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorScheme.background,
                unfocusedContainerColor = colorScheme.background
            )
        )
        TextButton(
            colors = ButtonDefaults.textButtonColors(
                containerColor = colorScheme.primary,
                contentColor = Color.White
            ),
            shape = OutlinedTextFieldDefaults.shape,
            onClick = {
                viewModel.chartSetup(
                    filter = MeteoriteFilter(
                        yearFrom = yearFrom.ifBlank { null },
                        yearTo = yearTo.ifBlank { null }
                    ),
                    data = data
                )
            },
            modifier = Modifier
                .weight(0.3f)
                .height(56.dp)
        ) {
            Text(stringResource(id = R.string.confirm))
        }
    }
}