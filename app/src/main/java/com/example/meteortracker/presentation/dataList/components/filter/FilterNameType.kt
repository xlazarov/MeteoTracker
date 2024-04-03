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
fun FilterNameType(
    viewModel: MeteoriteListViewModel = hiltViewModel()
) {
    val filter by viewModel.filter.observeAsState(MeteoriteFilter())

    var selectedValid by remember { mutableStateOf(filter.nameType == "Valid") }
    var selectedRelict by remember { mutableStateOf(filter.nameType == "Relict") }

    Text(text = stringResource(id = R.string.type))
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        FilterChip(
            label = { Text(stringResource(id = R.string.valid)) },
            selected = selectedValid,
            onClick = {
                selectedValid = !selectedValid
                viewModel.setFilter(
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
                viewModel.setFilter(
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
}