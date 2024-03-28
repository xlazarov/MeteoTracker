package com.example.meteortracker.presentation.dataList

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meteortracker.R
import com.example.meteortracker.presentation.dataList.components.MeteoriteCard
import com.example.meteortracker.presentation.dataList.components.SearchBar
import com.example.meteortracker.presentation.dataList.components.filter.FilterDialog
import com.example.meteortracker.presentation.dataList.viewModel.MeteoriteListViewModel

@Composable
fun DataListScreen(
    viewModel: MeteoriteListViewModel = hiltViewModel()
) {
    val meteorites by viewModel.dataList.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)

    var showDialog by rememberSaveable { mutableStateOf(false) }
    val listState = rememberLazyListState()

    Column {
        SearchBar(onFilterClick = { showDialog = true })
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 8.dp),
            modifier = Modifier.padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
        ) {
            itemsIndexed(
                items = meteorites
            ) { index, meteorite ->
                MeteoriteCard(
                    meteorite = meteorite,
                    modifier = Modifier.padding(top = 8.dp),
                    listState = listState,
                    itemIndex = index
                )
                if (index >= meteorites.size - 1) {
                    LaunchedEffect(Unit) {
                        viewModel.fetchDataList()
                    }
                }
            }
            if (isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
            if (meteorites.isEmpty() && !isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = stringResource(id = R.string.no_data))
                    }
                }
            }
        }
        if (showDialog) {
            FilterDialog(
                onDismissRequest = { showDialog = false }
            )
        }
    }
}