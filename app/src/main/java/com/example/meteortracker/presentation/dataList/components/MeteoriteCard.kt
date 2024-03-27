package com.example.meteortracker.presentation.dataList.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.twotone.LocationOn
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.meteortracker.R
import com.example.meteortracker.data.Meteorite
import com.example.meteortracker.presentation.map.components.MapView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeteoriteCard(
    meteorite: Meteorite,
    modifier: Modifier = Modifier,
    listState: LazyListState? = null,
    itemIndex: Int? = null
) {
    val coroutineScope = rememberCoroutineScope()
    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }
    var showMap by rememberSaveable {
        mutableStateOf(false)
    }
    val icon by rememberUpdatedState(
        newValue = if (isExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = if (isExpanded) colorScheme.secondaryContainer
            else colorScheme.secondaryContainer.copy(0.2f)
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = meteorite.name,
                    style = typography.titleLarge,
                    color = colorScheme.primary
                )
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = colorScheme.onSecondaryContainer
                )
            }
            if (isExpanded) {
                MeteoriteDetails(meteorite = meteorite)
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    FilledTonalButton(
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = colorScheme.primary,
                            contentColor = colorScheme.onPrimary,
                        ),
                        onClick = { showMap = true }
                    ) {
                        Icon(
                            imageVector = Icons.TwoTone.LocationOn,
                            contentDescription = null
                        )
                        Text(text = " " + stringResource(id = R.string.show_map))
                    }
                }
                if (showMap) {
                    BasicAlertDialog(
                        onDismissRequest = { showMap = false },
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.5f),
                    ) {
                        MapView(
                            meteorites = listOf(meteorite),
                            modifier = Modifier.clip(RoundedCornerShape(4))
                        )
                    }
                }
            }
        }
    }
}