package com.example.meteortracker.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
    onClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val filter by viewModel.filter.observeAsState(MeteoriteFilter())

    var yearFrom by remember { mutableStateOf(filter.yearFrom ?: "") }
    var yearTo by remember { mutableStateOf(filter.yearTo ?: "") }

    Row(
        modifier = modifier
            .background(colorScheme.primaryContainer)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = yearFrom,
            onValueChange = { yearFrom = it },
            label = { Text(stringResource(id = R.string.year_from)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.weight(0.4f),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )
        TextField(
            value = yearTo,
            onValueChange = { yearTo = it },
            label = { Text(stringResource(id = R.string.year_to)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.weight(0.4f),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.width(16.dp))
        TextButton(
            colors = ButtonDefaults.textButtonColors(
                containerColor = colorScheme.primary,
                contentColor = Color.White
            ),
            onClick = { onClick(yearFrom, yearTo) },
            modifier = Modifier.weight(0.2f)
        ) {
            Text(stringResource(id = R.string.confirm))
        }
    }
}