/*
 * SPDX-FileCopyrightText: The uwuAOSP Project
 * SPDX-License-Identifier: Apache-2.0
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.uwuaosp.compose.R
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

/** Material owns the app-bar measurement, scrolling, fling, and restored scroll state. */
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
    expandedToolbarHeight: Dp = TopAppBarDefaults.LargeFlexibleAppBarWithoutSubtitleExpandedHeight,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    header: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    val context = LocalContext.current
    LaunchedEffect(context, title) { context.findSettingsActivity()?.title = title }
    val expandedHeight =
        expandedToolbarHeight.coerceAtLeast(TopAppBarDefaults.LargeAppBarCollapsedHeight)
    val offsetLimit =
        with(LocalDensity.current) {
            -(expandedHeight - TopAppBarDefaults.LargeAppBarCollapsedHeight).toPx()
        }
    val state =
        rememberTopAppBarState(
            initialHeightOffsetLimit = offsetLimit,
            initialHeightOffset = if (collapsing && startCollapsed) offsetLimit else 0f,
        )
    val behavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(state)
    val barColors = TopAppBarDefaults.topAppBarColors(containerColor = containerColor)
    val navigation: @Composable () -> Unit = {
        if (showBackButton) {
            FilledTonalIconButton(onClick = onNavigateUp, shapes = IconButtonDefaults.shapes()) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    stringResource(R.string.settings_navigate_up),
                )
            }
        }
    }
    val titleSlot: @Composable () -> Unit = titleContent ?: { Text(title) }
    Scaffold(
        modifier =
            if (collapsing) modifier.nestedScroll(behavior.nestedScrollConnection) else modifier,
        containerColor = containerColor,
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            Column {
                if (collapsing) {
                    LargeFlexibleTopAppBar(
                        title = titleSlot,
                        navigationIcon = navigation,
                        actions = actions,
                        expandedHeight = expandedHeight,
                        colors = barColors,
                        scrollBehavior = behavior,
                    )
                } else {
                    TopAppBar(
                        title = titleSlot,
                        navigationIcon = navigation,
                        actions = actions,
                        colors = barColors,
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
