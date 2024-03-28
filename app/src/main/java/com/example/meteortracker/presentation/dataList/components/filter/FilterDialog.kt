package com.example.meteortracker.presentation.dataList.components.filter

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.RestartAlt
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meteortracker.R
import com.example.meteortracker.data.MeteoriteFilter
import com.example.meteortracker.presentation.dataList.viewModel.MeteoriteListViewModel

@Composable
fun FilterDialog(
    onDismissRequest: () -> Unit,
    viewModel: MeteoriteListViewModel = hiltViewModel()
) {
    var filter by remember { mutableStateOf(viewModel.filter.value ?: MeteoriteFilter()) }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                stringResource(id = R.string.filter),
                style = typography.titleLarge
            )
        },
        text = {
            FilterDialogContent(
                filter = filter,
                onSelectRequest = { filter = it }
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    onDismissRequest()
                    viewModel.setFilter(filter)
                    viewModel.fetchByFilter()
                }
            ) {
                Text(stringResource(id = R.string.confirm))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = colorScheme.primary
                )
            ) {
                Text(stringResource(id = R.string.close))
            }
        },
        icon = {
            TextButton(
                onClick = {
                    viewModel.clearFilter()
                    onDismissRequest()
                },
                content = {
                    Icon(imageVector = Icons.TwoTone.RestartAlt, contentDescription = null)
                    Text(stringResource(id = R.string.reset))
                }
            )
        }
    )
}
