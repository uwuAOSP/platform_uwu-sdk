/*
 * Copyright (C) 2026 The uwuAOSP Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)

package org.uwuaosp.compose.widget.scaffold

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LargeFlexibleTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.uwuaosp.compose.framework.theme.SettingsDimension
import org.uwuaosp.compose.widget.ui.SettingsContent

/** Hosts content that owns its scrolling and applies the supplied window padding. */
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
) {
    SettingsPageScaffold(
        title = title,
        modifier = modifier,
        showBackButton = showBackButton,
        onNavigateUp = onNavigateUp,
        actions = actions,
        titleContent = titleContent,
        collapsing = false,
    ) { padding ->
        val direction = androidx.compose.ui.platform.LocalLayoutDirection.current
        content(
            PaddingValues(
                start = padding.calculateStartPadding(direction),
                top = padding.calculateTopPadding() + contentTopPaddingAdjustment,
                end = padding.calculateEndPadding(direction),
                bottom = padding.calculateBottomPadding(),
            )
        )
    }
}

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
) {
    SettingsPageScaffold(
        title = title,
        modifier = modifier,
        showBackButton = showBackButton,
        onNavigateUp = onNavigateUp,
        actions = actions,
        collapsing = useCollapsingToolbar,
        startCollapsed = startCollapsed,
        expandedToolbarHeight = 179.dp,
    ) { padding ->
        Column(
            Modifier.fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .verticalScroll(rememberScrollState())
        ) {
            SettingsContent(
                Modifier.padding(
                    start = SettingsDimension.pagePadding,
                    top = contentTopPadding,
                    end = SettingsDimension.pagePadding,
                    bottom = SettingsDimension.pageBottomPadding,
                ),
                content = content,
            )
        }
    }
}

/**
 * Shares Material's saved app-bar state, fling handling, and inset policy across settings pages.
 */
@Composable
internal fun SettingsPageScaffold(
    title: String,
    modifier: Modifier = Modifier,
    showBackButton: Boolean = false,
    onNavigateUp: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    titleContent: (@Composable () -> Unit)? = null,
    collapsing: Boolean = true,
    startCollapsed: Boolean = false,
    expandedToolbarHeight: Dp = 200.dp,
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainer,
    header: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    val context = LocalContext.current
    LaunchedEffect(context, title) { context.findSettingsActivity()?.title = title }
    val collapseRange =
        with(LocalDensity.current) {
            (expandedToolbarHeight - TopAppBarDefaults.LargeAppBarCollapsedHeight)
                .coerceAtLeast(0.dp)
                .toPx()
        }
    val state =
        rememberTopAppBarState(
            initialHeightOffsetLimit = -collapseRange,
            initialHeightOffset = if (startCollapsed) -collapseRange else 0f,
        )
    val behavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(state = state)
    val titleSlot: @Composable () -> Unit =
        titleContent ?: { Text(title, maxLines = 2, overflow = TextOverflow.Ellipsis) }
    val navigation: @Composable () -> Unit = {
        if (showBackButton) {
            SettingsToolbarActionButton(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription =
                    androidx.compose.ui.res.stringResource(
                        org.uwuaosp.compose.R.string.settings_navigate_up
                    ),
                onClick = onNavigateUp,
            )
        }
    }
    Scaffold(
        modifier =
            modifier.then(
                if (collapsing) Modifier.nestedScroll(behavior.nestedScrollConnection) else Modifier
            ),
        containerColor = containerColor,
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            Column {
                val colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = containerColor,
                        scrolledContainerColor = containerColor,
                    )
                if (collapsing) {
                    LargeFlexibleTopAppBar(
                        title = titleSlot,
                        navigationIcon = navigation,
                        actions = actions,
                        expandedHeight =
                            expandedToolbarHeight.coerceAtLeast(
                                TopAppBarDefaults.LargeAppBarCollapsedHeight
                            ),
                        colors = colors,
                        scrollBehavior = behavior,
                    )
                } else {
                    TopAppBar(
                        title = titleSlot,
                        navigationIcon = navigation,
                        actions = actions,
                        colors = colors,
                    )
                }
                header()
            }
        },
        content = content,
    )
}

private tailrec fun Context.findSettingsActivity(): Activity? =
    when (this) {
        is Activity -> this
        is ContextWrapper -> if (baseContext !== this) baseContext.findSettingsActivity() else null
        else -> null
    }
