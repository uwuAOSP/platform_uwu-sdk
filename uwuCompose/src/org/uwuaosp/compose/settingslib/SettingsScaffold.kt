/*
 * SPDX-FileCopyrightText: The uwuAOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.uwuaosp.compose.settingslib

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Compatibility entry points for applications using the original settingslib package.
@Composable
fun SettingsAppBarScaffold(
    title: String,
    modifier: Modifier = Modifier,
    showBackButton: Boolean = false,
    onNavigateUp: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    titleContent: (@Composable () -> Unit)? = null,
    contentTopPaddingAdjustment: Dp = 0.dp,
    content: @Composable (PaddingValues) -> Unit,
) =
    org.uwuaosp.compose.widget.scaffold.SettingsAppBarScaffold(
        title = title,
        modifier = modifier,
        showBackButton = showBackButton,
        onNavigateUp = onNavigateUp,
        actions = actions,
        titleContent = titleContent,
        contentTopPaddingAdjustment = contentTopPaddingAdjustment,
        content = content,
    )

@Composable
fun SettingsScaffold(
    title: String,
    modifier: Modifier = Modifier,
    showBackButton: Boolean = false,
    onNavigateUp: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    useCollapsingToolbar: Boolean = true,
    startCollapsed: Boolean = true,
    contentTopPadding: Dp = 0.dp,
    content: @Composable ColumnScope.() -> Unit,
) =
    org.uwuaosp.compose.widget.scaffold.SettingsScaffold(
        title = title,
        modifier = modifier,
        showBackButton = showBackButton,
        onNavigateUp = onNavigateUp,
        actions = actions,
        useCollapsingToolbar = useCollapsingToolbar,
        startCollapsed = startCollapsed,
        contentTopPadding = contentTopPadding,
        content = content,
    )
