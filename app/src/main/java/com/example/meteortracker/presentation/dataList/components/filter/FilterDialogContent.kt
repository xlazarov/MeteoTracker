package com.example.meteortracker.presentation.dataList.components.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.meteortracker.R
import com.example.meteortracker.data.MeteoriteFilter
/**
 * Composable that presents filter options for meteorite search criteria, including fall status, type, year range, and mass range.
 * Allows users to select filters and applies them by invoking [onSelectRequest] with the updated filter.
 */
@Composable
fun FilterDialogContent(
    filter: MeteoriteFilter,
    onSelectRequest: (MeteoriteFilter) -> Unit
) {
    var selectedFound by remember { mutableStateOf(filter.fall == "Found") }
    var selectedFell by remember { mutableStateOf(filter.fall == "Fell") }
    var selectedValid by remember { mutableStateOf(filter.nameType == "Valid") }
    var selectedRelict by remember { mutableStateOf(filter.nameType == "Relict") }

    var yearFrom by remember { mutableStateOf(filter.yearFrom) }
    var yearTo by remember { mutableStateOf(filter.yearTo) }
    var massMin by remember { mutableStateOf(filter.massMin) }
    var massMax by remember { mutableStateOf(filter.massMax) }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = stringResource(id = R.string.fall))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(
                label = { Text(text = stringResource(id = R.string.fell)) },
                selected = selectedFell,
                onClick = {
                    selectedFell = !selectedFell
                    onSelectRequest(
                        filter.copy(
                            fall = getFilter(
                                selectedFell,
                                selectedFound,
                                "Fell",
                                "Found"
                            )
                        )
                    )
                },
                leadingIcon = {
                    if (selectedFell) Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = null
                    )
                }
            )
            FilterChip(
                label = { Text(stringResource(id = R.string.found)) },
                selected = selectedFound,
                onClick = {
                    selectedFound = !selectedFound
                    onSelectRequest(
                        filter.copy(
                            fall = getFilter(
                                selectedFell,
                                selectedFound,
                                "Fell",
                                "Found"
                            )
                        )
                    )
                },
                leadingIcon = {
                    if (selectedFound) Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = null
                    )
                }
            )
        }
        HorizontalDivider()
        Text(text = stringResource(id = R.string.type))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(
                label = { Text(stringResource(id = R.string.valid)) },
                selected = selectedValid,
                onClick = {
                    selectedValid = !selectedValid
                    onSelectRequest(
                        filter.copy(
                            nameType = getFilter(
                                selectedValid,
                                selectedRelict,
                                "Valid",
                                "Relict"
                            )
                        )
                    )
                },
                leadingIcon = {
                    if (selectedValid) Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = null
                    )
                }
            )
            FilterChip(
                label = { Text(stringResource(id = R.string.relict)) },
                selected = selectedRelict,
                onClick = {
                    selectedRelict = !selectedRelict
                    onSelectRequest(
                        filter.copy(
                            nameType = getFilter(
                                selectedValid,
                                selectedRelict,
                                "Valid",
                                "Relict"
                            )
                        )
                    )
                },
                leadingIcon = {
                    if (selectedRelict) Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = null
                    )
                }
            )
        }
        HorizontalDivider()
        Text(text = stringResource(id = R.string.year))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = yearFrom ?: "",
                onValueChange = {
                    yearFrom = it
                    onSelectRequest(filter.copy(yearFrom = if (it == "") null else it))
                },
                label = { Text("Year From") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.weight(0.5f)
            )
            OutlinedTextField(
                value = yearTo ?: "",
                onValueChange = {
                    yearTo = it
                    onSelectRequest(filter.copy(yearTo = if (it == "") null else it))
                },
                label = { Text("Year To") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.weight(0.5f)
            )
        }

        HorizontalDivider(modifier = Modifier.padding(top = 4.dp))
        Text(text = stringResource(id = R.string.mass))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = massMin ?: "",
                onValueChange = {
                    massMin = it
                    onSelectRequest(filter.copy(massMin = if (it == "") null else it))
                },
                label = { Text("Mass From") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.weight(0.5f)

            )
            OutlinedTextField(
                value = massMax ?: "",
                onValueChange = {
                    massMax = it
                    onSelectRequest(filter.copy(massMax = if (it == "") null else it))
                },
                label = { Text("Mass To") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.weight(0.5f)
            )
        }
    }
}

/**
 * Determines the filter string based on boolean selections for a given category (e.g., fall status or type).
 */
private fun getFilter(
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
