package com.example.meteortracker.presentation.dataList.components.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meteortracker.R
import com.example.meteortracker.data.MeteoriteFilter
import com.example.meteortracker.presentation.dataList.viewModel.MeteoriteListViewModel

@Composable
fun FilterYear(
    viewModel: MeteoriteListViewModel = hiltViewModel()
){
    val filter by viewModel.filter.observeAsState(MeteoriteFilter())

    var yearFrom by remember { mutableStateOf(filter.yearFrom) }
    var yearTo by remember { mutableStateOf(filter.yearTo) }

    Text(text = stringResource(id = R.string.year))
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = yearFrom.orEmpty(),
            onValueChange = {
                yearFrom = it
                viewModel.setFilter(filter.copy(yearFrom = it))
            },
            label = { Text(stringResource(id = R.string.year_from)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.weight(0.5f)
        )
        OutlinedTextField(
            value = yearTo.orEmpty(),
            onValueChange = {
                yearTo = it
                viewModel.setFilter(filter.copy(yearTo = it))
            },
            label = { Text(stringResource(id = R.string.year_to)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.weight(0.5f)
        )
    }
}