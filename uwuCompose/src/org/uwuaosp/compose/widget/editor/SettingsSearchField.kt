/*
 * SPDX-FileCopyrightText: The uwuAOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

@file:OptIn(
    androidx.compose.material3.ExperimentalMaterial3Api::class,
    androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class,
)

package org.uwuaosp.compose.widget.editor

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun SettingsSearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    placeholder: String,
    clearContentDescription: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector = Icons.Outlined.Search,
    trailingContent: (@Composable RowScope.() -> Unit)? = null,
) {
    SearchBarDefaults.InputField(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        expanded = false,
        onExpandedChange = {},
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text(placeholder) },
        leadingIcon = { Icon(leadingIcon, contentDescription = null) },
        trailingIcon =
            if (trailingContent != null) {
                { Row(content = trailingContent) }
            } else if (query.isNotEmpty()) {
                {
                    IconButton(
                        onClick = { onQueryChange("") },
                        shapes = IconButtonDefaults.shapes(),
                    ) {
                        Icon(Icons.Outlined.Clear, contentDescription = clearContentDescription)
                    }
                }
            } else null,
    )
}
