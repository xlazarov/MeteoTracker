package com.example.meteortracker.presentation.dataList.components.filter

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meteortracker.R
import com.example.meteortracker.data.MeteoriteFilter
import com.example.meteortracker.presentation.dataList.viewModel.MeteoriteListViewModel

/**
 * Presents a dialog allowing the user to apply filters to the meteorite data list.
 */
@Composable
fun FilterDialog(
    onDismissRequest: () -> Unit,
    viewModel: MeteoriteListViewModel = hiltViewModel()
) {
    val oldFilter = viewModel.filter.value ?: MeteoriteFilter()

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                stringResource(id = R.string.filter),
                style = typography.titleLarge
            )
        },
        text = {
            FilterDialogContent()
        },
        confirmButton = {
            Button(
                onClick = {
                    viewModel.fetchByFilter()
                    onDismissRequest()
                }
            ) {
                Text(stringResource(id = R.string.confirm))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    viewModel.setFilter(oldFilter)
                    onDismissRequest()
                },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = colorScheme.primary
                )
            ) {
                Text(stringResource(id = R.string.close))
            }
        },
        icon = {
            ResetButton {
                viewModel.clearFilter()
                onDismissRequest()
            }
        }
    )
}
