package com.example.meteortracker.presentation.dataList.components.filter

import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
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
    val filter by viewModel.filter.observeAsState(MeteoriteFilter())

    val context = LocalContext.current
    val wrongRangeToast = stringResource(id = R.string.wrong_year_range)

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
                    if (isCorrectMassRange(filter.massMin, filter.massMax) &&
                        isCorrectYearRange(filter.yearFrom, filter.yearTo)
                    ) {
                        viewModel.fetchByFilter()
                        onDismissRequest()
                    } else {
                        Toast.makeText(
                            context,
                            wrongRangeToast,
                            Toast.LENGTH_LONG
                        ).show()
                    }
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

fun isCorrectYearRange(yearFrom: String?, yearTo: String?): Boolean {
    if (yearFrom.isNullOrBlank() || yearTo.isNullOrBlank()) {
        return true
    }
    return yearFrom <= yearTo
}

fun isCorrectMassRange(massMin: String?, massMax: String?): Boolean {
    if (massMin.isNullOrBlank() || massMax.isNullOrBlank()) {
        return true
    }
    return massMin <= massMax
}