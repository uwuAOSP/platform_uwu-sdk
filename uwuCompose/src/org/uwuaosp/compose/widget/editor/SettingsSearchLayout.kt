/*
 * SPDX-FileCopyrightText: The uwuAOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

@file:OptIn(
    androidx.compose.material3.ExperimentalMaterial3Api::class,
    androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class,
)

package org.uwuaosp.compose.widget.editor

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.AppBarWithSearch
import androidx.compose.material3.ExpandedFullScreenContainedSearchBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SearchBarState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberContainedSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

/** Hosts the native contained search layout without owning the query or result data. */
@Composable
fun SettingsSearchLayout(
    textFieldState: TextFieldState,
    placeholder: String,
    clearContentDescription: String,
    modifier: Modifier = Modifier,
    searchBarState: SearchBarState = rememberContainedSearchBarState(),
    windowInsets: WindowInsets = SearchBarDefaults.windowInsets,
    onSearch: (String) -> Unit = {},
    results: @Composable ColumnScope.() -> Unit,
) {
    val scope = rememberCoroutineScope()
    val colors =
        SearchBarDefaults.appBarWithSearchColors(
            searchBarColors = SearchBarDefaults.containedColors(state = searchBarState)
        )
    val inputField: @Composable () -> Unit = {
        SearchBarDefaults.InputField(
            textFieldState = textFieldState,
            searchBarState = searchBarState,
            onSearch = { query ->
                onSearch(query)
                scope.launch { searchBarState.animateToCollapsed() }
            },
            placeholder = { Text(placeholder) },
            leadingIcon = { Icon(Icons.Outlined.Search, null) },
            trailingIcon = {
                if (textFieldState.text.isNotEmpty()) {
                    IconButton(
                        onClick = { textFieldState.setTextAndPlaceCursorAtEnd("") },
                        shapes = IconButtonDefaults.shapes(),
                    ) {
                        Icon(Icons.Outlined.Clear, clearContentDescription)
                    }
                }
            },
            colors = colors.searchBarColors.inputFieldColors,
        )
    }
    AppBarWithSearch(
        state = searchBarState,
        inputField = inputField,
        colors = colors,
        windowInsets = windowInsets,
        modifier = modifier,
    )
    ExpandedFullScreenContainedSearchBar(
        state = searchBarState,
        inputField = inputField,
        colors = colors.searchBarColors,
        content = results,
    )
}
