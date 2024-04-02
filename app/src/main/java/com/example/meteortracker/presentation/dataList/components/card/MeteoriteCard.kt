package com.example.meteortracker.presentation.dataList.components.card

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.meteortracker.data.Meteorite
import kotlinx.coroutines.launch

/**
 * Displays [meteorite] name in a card view.
 * Expands on click to show more details and snaps to the top of the list.
 */
@Composable
fun MeteoriteCard(
    meteorite: Meteorite,
    modifier: Modifier = Modifier,
    listState: LazyListState? = null,
    itemIndex: Int? = null
) {
    val coroutineScope = rememberCoroutineScope()

    var isExpanded by rememberSaveable { mutableStateOf(false) }
    var showMap by rememberSaveable { mutableStateOf(false) }

    val cardColor by rememberUpdatedState(
        newValue = if (isExpanded) colorScheme.secondaryContainer else
            colorScheme.secondaryContainer.copy(0.2f)
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        ),
        onClick = {
            isExpanded = !isExpanded
            if (isExpanded && listState != null && itemIndex != null) {
                coroutineScope.launch {
                    listState.animateScrollToItem(itemIndex)
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            MeteoriteCardHeader(meteorite = meteorite, isExpanded = isExpanded)
            if (isExpanded) {
                MeteoriteCardContent(meteorite = meteorite, onShowMap = { showMap = true })
            }
            if (showMap) {
                LocationDialog(meteorite = meteorite, onClose = { showMap = false })
            }
        }
    }
}