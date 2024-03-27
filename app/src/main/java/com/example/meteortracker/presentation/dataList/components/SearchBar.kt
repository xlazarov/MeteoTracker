package com.example.meteortracker.presentation.dataList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FilterAlt
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meteortracker.R
import com.example.meteortracker.data.MeteoriteFilter
import com.example.meteortracker.presentation.dataList.viewModel.MeteoriteListViewModel

@Composable
fun SearchBar(
    onFilterClick: () -> Unit,
    viewModel: MeteoriteListViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val filter by viewModel.filter.observeAsState(MeteoriteFilter())
    var text by rememberSaveable { mutableStateOf(filter.nameQuery ?: "") }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = colorScheme.secondaryContainer)
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    viewModel.setFilter(filter.copy(nameQuery = text))
                    viewModel.fetchByFilter()
                }
            ),
            label = { Text(text = stringResource(id = R.string.search)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null
                )
            },
            singleLine = true,
            trailingIcon = {
                FilledIconButton(
                    onClick = onFilterClick,
                    shape = CardDefaults.shape
                ) {
                    Icon(
                        imageVector = Icons.Rounded.FilterAlt,
                        contentDescription = null
                    )
                }
            },
            colors = TextFieldDefaults.colors().copy(
                unfocusedPlaceholderColor = colorScheme.onPrimaryContainer.copy(0.5f),
                focusedPlaceholderColor = colorScheme.onPrimaryContainer.copy(0.5f),
                unfocusedLeadingIconColor = colorScheme.onPrimaryContainer,
                focusedLeadingIconColor = colorScheme.primary,
                focusedIndicatorColor = colorScheme.primary,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}