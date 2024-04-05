package com.example.meteortracker.presentation.common.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.meteortracker.presentation.dataList.components.filter.isCorrectYearRange

/**
 * Allows to set the [filter] with year range after validation. Displays a toast for invalid ranges.
 * Triggers [onClick] with start and end years if valid. Customizable with [modifier].
 */
@Composable
fun YearRangeSelector(
    filter: MeteoriteFilter,
    onClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var yearFrom by remember { mutableStateOf(filter.yearFrom ?: "2010") }
    var yearTo by remember { mutableStateOf(filter.yearTo ?: "2024") }

    val context = LocalContext.current
    val wrongRangeToast = stringResource(id = R.string.wrong_year_range)
    var wrongRange by rememberSaveable {
        mutableStateOf(false)
    }

    val onRangeSelected = {
        if (isCorrectYearRange(yearFrom, yearTo)) {
            onClick(yearFrom, yearTo)
        } else {
            Toast.makeText(
                context,
                wrongRangeToast,
                Toast.LENGTH_LONG
            ).show()
            wrongRange = true
        }
    }

    Row(
        modifier = modifier
            .background(colorScheme.secondaryContainer)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = yearFrom,
            onValueChange = {
                yearFrom = it
                wrongRange = false
            },
            label = { Text(stringResource(id = R.string.year_from)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.weight(0.4f),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            isError = wrongRange
        )
        TextField(
            value = yearTo,
            onValueChange = {
                yearTo = it
                wrongRange = false
            },
            label = { Text(stringResource(id = R.string.year_to)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onRangeSelected() }
            ),
            modifier = Modifier.weight(0.4f),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            isError = wrongRange
        )
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = onRangeSelected
        ) {
            Text(stringResource(id = R.string.confirm))
        }
    }
}