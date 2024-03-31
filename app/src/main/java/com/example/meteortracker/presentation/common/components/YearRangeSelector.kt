package com.example.meteortracker.presentation.common.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.meteortracker.R
import com.example.meteortracker.data.MeteoriteFilter

@Composable
fun YearRangeSelector(
    filter: MeteoriteFilter,
    onClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var yearFrom by remember { mutableStateOf(filter.yearFrom ?: "2010") }
    var yearTo by remember { mutableStateOf(filter.yearTo ?: "2024") }

    val context = LocalContext.current
    val wrongRange = stringResource(id = R.string.wrong_year_range)

    Row(
        modifier = modifier
            .background(colorScheme.secondaryContainer)
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
        Button(
            onClick = {
                if ((yearFrom.toIntOrNull() ?: 0) > (yearTo.toIntOrNull() ?: 0)) {
                    Toast.makeText(
                        context,
                        wrongRange,
                        Toast.LENGTH_LONG
                    ).show()
                } else onClick(yearFrom, yearTo)
            }
        ) {
            Text(stringResource(id = R.string.confirm))
        }
    }
}