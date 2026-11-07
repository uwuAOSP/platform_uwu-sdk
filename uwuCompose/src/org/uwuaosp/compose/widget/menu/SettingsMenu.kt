/*
 * SPDX-FileCopyrightText: The uwuAOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package org.uwuaosp.compose.widget.menu

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenuGroup
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.DropdownMenuPopup
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    DropdownMenuPopup(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier.verticalScroll(rememberScrollState()),
        content = content,
    )
}

@Composable
fun SettingsMenuGroup(
    index: Int = 0,
    groupCount: Int = 1,
    title: String? = null,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    DropdownMenuGroup(
        shapes = MenuDefaults.groupShape(index, groupCount),
        modifier = modifier.fillMaxWidth(),
    ) {
        title?.let { MenuDefaults.Label { Text(it) } }
        content()
    }
    if (index < groupCount - 1) {
        Spacer(Modifier.height(MenuDefaults.GroupSpacing))
    }
}

/** Selection is owned by the host; menu items never reuse page preference surfaces. */
@Composable
fun SettingsMenuItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    index: Int,
    itemCount: Int,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    DropdownMenuItem(
        selected = selected,
        onClick = onClick,
        text = { Text(text) },
        shapes = MenuDefaults.itemShape(index, itemCount),
        selectedLeadingIcon = {
            Icon(
                Icons.Filled.Check,
                contentDescription = null,
                modifier = Modifier.size(MenuDefaults.LeadingIconSize),
            )
        },
        modifier = modifier,
        enabled = enabled,
    )
}
