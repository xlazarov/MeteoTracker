package com.example.meteortracker.presentation.dataList.components.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meteortracker.R
import com.example.meteortracker.data.MeteoriteFilter
import com.example.meteortracker.presentation.dataList.viewModel.MeteoriteListViewModel

@Composable
fun FilterFall(
    viewModel: MeteoriteListViewModel = hiltViewModel()
) {
    val filter by viewModel.filter.observeAsState(MeteoriteFilter())

    var selectedFound by remember { mutableStateOf(filter.fall == "Found") }
    var selectedFell by remember { mutableStateOf(filter.fall == "Fell") }

    Text(text = stringResource(id = R.string.fall))
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        FilterChip(
            label = { Text(text = stringResource(id = R.string.fell)) },
            selected = selectedFell,
            onClick = {
                selectedFell = !selectedFell
                viewModel.setFilter(
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
                viewModel.setFilter(
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
}