package com.example.meteortracker.presentation.settings.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.meteortracker.util.LocaleManager
import com.example.meteortracker.util.restartActivity

/**
 * Dropdown for language selection. Changes apply immediately by [restartActivity].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageDropdown(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val languages = LocaleManager.getLocaleOptions()

    var isExpanded by rememberSaveable { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf(LocaleManager.get(context)) }

    val icon by rememberUpdatedState(
        newValue = if (isExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown
    )

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = !isExpanded },
        modifier = modifier.fillMaxWidth(0.4f)
    ) {
        TextField(
            value = selectedLanguage.uppercase(),
            onValueChange = {},
            readOnly = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            ),
            modifier = Modifier.menuAnchor(),
            trailingIcon = { Icon(imageVector = icon, contentDescription = null) }
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            languages.filterNot { it == selectedLanguage }.forEach { language ->
                DropdownMenuItem(
                    text = { Text(text = language.uppercase()) },
                    onClick = {
                        selectedLanguage = language
                        isExpanded = false
                        LocaleManager.set(context, language)
                        restartActivity(context)
                    }
                )
            }
        }
    }
}