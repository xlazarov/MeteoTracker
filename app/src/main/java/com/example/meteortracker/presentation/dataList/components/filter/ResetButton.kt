package com.example.meteortracker.presentation.dataList.components.filter

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.RestartAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.meteortracker.R

@Composable
fun ResetButton(
    onClick: () -> Unit,
) {
    TextButton(
        onClick = onClick,
        content = {
            Icon(imageVector = Icons.TwoTone.RestartAlt, contentDescription = "Reset Filter")
            Text(stringResource(id = R.string.reset))
        }
    )
}