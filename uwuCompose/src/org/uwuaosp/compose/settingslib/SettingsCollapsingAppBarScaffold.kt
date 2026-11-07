/*
 * SPDX-FileCopyrightText: The uwuAOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

@file:OptIn(
    androidx.compose.material3.ExperimentalMaterial3Api::class,
    androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class,
)

package org.uwuaosp.compose.settingslib

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

// Compatibility entry points for applications using the original settingslib package.
@Composable
fun SettingsCollapsingAppBarScaffold(
    title: String,
    modifier: Modifier = Modifier,
    showBackButton: Boolean = false,
    onNavigateUp: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    titleContent: (@Composable () -> Unit)? = null,
    startCollapsed: Boolean = false,
    expandedToolbarHeight: Dp =
        androidx.compose.material3.TopAppBarDefaults
            .LargeFlexibleAppBarWithoutSubtitleExpandedHeight,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    content: @Composable (PaddingValues) -> Unit,
) =
    org.uwuaosp.compose.widget.scaffold.SettingsCollapsingAppBarScaffold(
        title = title,
        modifier = modifier,
        showBackButton = showBackButton,
        onNavigateUp = onNavigateUp,
        actions = actions,
        titleContent = titleContent,
        startCollapsed = startCollapsed,
        expandedToolbarHeight = expandedToolbarHeight,
        containerColor = containerColor,
        content = content,
    )
