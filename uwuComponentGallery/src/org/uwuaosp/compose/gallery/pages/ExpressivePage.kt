/*
 * SPDX-FileCopyrightText: The uwuAOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

@file:OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)

package org.uwuaosp.compose.gallery.pages

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.ToggleFloatingActionButtonDefaults.animateIcon
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.uwuaosp.compose.gallery.R
import org.uwuaosp.compose.widget.expressive.SettingsActionButton
import org.uwuaosp.compose.widget.expressive.SettingsButtonSize
import org.uwuaosp.compose.widget.expressive.SettingsConnectedButtonGroup
import org.uwuaosp.compose.widget.expressive.SettingsFloatingToolbar
import org.uwuaosp.compose.widget.expressive.SettingsSplitButton
import org.uwuaosp.compose.widget.expressive.SettingsSplitButtonStyle
import org.uwuaosp.compose.widget.menu.SettingsMenuGroup
import org.uwuaosp.compose.widget.menu.SettingsMenuItem
import org.uwuaosp.compose.widget.scaffold.SettingsScaffold
import org.uwuaosp.compose.widget.ui.SettingsAdaptiveGrid

/** Native Expressive controls are previewed outside of preference rows. */
@Composable
fun ExpressivePage(onBack: () -> Unit) {
    var enabled by rememberSaveable { mutableStateOf(true) }
    var selected by rememberSaveable { mutableIntStateOf(0) }
    var toolbarExpanded by rememberSaveable { mutableStateOf(true) }
    var listSelection by rememberSaveable { mutableIntStateOf(0) }
    var barSelection by rememberSaveable { mutableIntStateOf(0) }
    var railSelection by rememberSaveable { mutableIntStateOf(0) }
    var bold by rememberSaveable { mutableStateOf(true) }
    var italic by rememberSaveable { mutableStateOf(false) }
    var underline by rememberSaveable { mutableStateOf(false) }
    var progress by rememberSaveable { mutableFloatStateOf(0.5f) }
    var moving by rememberSaveable { mutableStateOf(true) }
    var menuExpanded by rememberSaveable { mutableStateOf(false) }
    var fabExpanded by rememberSaveable { mutableStateOf(false) }
    var splitSize by rememberSaveable { mutableIntStateOf(1) }
    var splitStyle by rememberSaveable { mutableIntStateOf(0) }
    var splitAction by rememberSaveable { mutableIntStateOf(0) }
    var actionCount by rememberSaveable { mutableIntStateOf(0) }
    val periods =
        listOf(
            stringResource(R.string.expressive_day),
            stringResource(R.string.expressive_week),
            stringResource(R.string.expressive_month),
        )
    val shareActions =
        listOf(
            stringResource(R.string.expressive_share),
            stringResource(R.string.expressive_copy_link),
        )
    val moreActions = stringResource(R.string.expressive_more_actions)
    val groupActions = periods + shareActions + moreActions
    BackHandler(enabled = fabExpanded) { fabExpanded = false }
    LaunchedEffect(enabled) {
        if (!enabled) {
            menuExpanded = false
            fabExpanded = false
        }
    }

    SettingsScaffold(
        title = stringResource(R.string.category_expressive),
        showBackButton = true,
        onNavigateUp = onBack,
        startCollapsed = false,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(stringResource(R.string.expressive_enabled), Modifier.weight(1f))
            Switch(checked = enabled, onCheckedChange = { enabled = it })
        }
        Text(
            stringResource(R.string.expressive_action_count, actionCount),
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(Modifier.height(16.dp))
        SettingsAdaptiveGrid { cardWidth ->
            ExpressiveCard(
                stringResource(R.string.expressive_toolbars),
                Modifier.width(cardWidth),
            ) {
                Box(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())) {
                    SettingsFloatingToolbar(
                        expanded = toolbarExpanded,
                        trailingContent = {
                            IconButton(
                                onClick = { actionCount++ },
                                enabled = enabled,
                                shapes = IconButtonDefaults.shapes(),
                            ) {
                                Icon(Icons.Filled.Save, stringResource(R.string.expressive_save))
                            }
                        },
                    ) {
                        IconToggleButton(
                            checked = bold,
                            onCheckedChange = { bold = it },
                            enabled = enabled,
                            shapes = IconButtonDefaults.toggleableShapes(),
                        ) {
                            Icon(Icons.Filled.FormatBold, stringResource(R.string.expressive_bold))
                        }
                        IconToggleButton(
                            checked = italic,
                            onCheckedChange = { italic = it },
                            enabled = enabled,
                            shapes = IconButtonDefaults.toggleableShapes(),
                        ) {
                            Icon(
                                Icons.Filled.FormatItalic,
                                stringResource(R.string.expressive_italic),
                            )
                        }
                        IconToggleButton(
                            checked = underline,
                            onCheckedChange = { underline = it },
                            enabled = enabled,
                            shapes = IconButtonDefaults.toggleableShapes(),
                        ) {
                            Icon(
                                Icons.Filled.FormatUnderlined,
                                stringResource(R.string.expressive_underline),
                            )
                        }
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(stringResource(R.string.expressive_expand_toolbar), Modifier.weight(1f))
                    Switch(
                        checked = toolbarExpanded,
                        onCheckedChange = { toolbarExpanded = it },
                        enabled = enabled,
                    )
                }
            }
            ExpressiveCard(
                stringResource(R.string.expressive_split_button),
                Modifier.width(cardWidth),
            ) {
                SettingsConnectedButtonGroup(
                    listOf("XS", "S", "M", "L", "XL"),
                    splitSize,
                    { splitSize = it },
                )
                SettingsConnectedButtonGroup(
                    listOf(
                        stringResource(R.string.expressive_filled),
                        stringResource(R.string.expressive_tonal),
                        stringResource(R.string.expressive_outlined),
                        stringResource(R.string.expressive_elevated),
                    ),
                    splitStyle,
                    { splitStyle = it },
                )
                Box(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())) {
                    SettingsSplitButton(
                        label = shareActions[splitAction],
                        size = SettingsButtonSize.entries[splitSize],
                        style = SettingsSplitButtonStyle.entries[splitStyle],
                        expanded = menuExpanded,
                        onClick = { actionCount++ },
                        onExpandedChange = { menuExpanded = it },
                        menuContentDescription = moreActions,
                        enabled = enabled,
                    ) {
                        SettingsMenuGroup {
                            shareActions.forEachIndexed { index, label ->
                                SettingsMenuItem(
                                    text = label,
                                    selected = splitAction == index,
                                    index = index,
                                    itemCount = shareActions.size,
                                    onClick = {
                                        splitAction = index
                                        menuExpanded = false
                                    },
                                )
                            }
                        }
                    }
                }
            }
            ExpressiveCard(
                stringResource(R.string.expressive_button_groups),
                Modifier.width(cardWidth),
            ) {
                SettingsConnectedButtonGroup(
                    periods,
                    selected,
                    { selected = it },
                    enabled = enabled,
                )
                ButtonGroup(
                    overflowIndicator = { ButtonGroupDefaults.OverflowIndicator(menuState = it) }
                ) {
                    groupActions.forEach { label ->
                        clickableItem(label = label, onClick = { actionCount++ }, enabled = enabled)
                    }
                }
            }
            ExpressiveCard(stringResource(R.string.expressive_buttons), Modifier.width(cardWidth)) {
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = { actionCount++ },
                        enabled = enabled,
                        shapes = ButtonDefaults.shapes(),
                    ) {
                        Text(stringResource(R.string.expressive_filled))
                    }
                    ElevatedButton(
                        onClick = { actionCount++ },
                        enabled = enabled,
                        shapes = ButtonDefaults.shapes(),
                    ) {
                        Text(stringResource(R.string.expressive_elevated))
                    }
                    FilledTonalButton(
                        onClick = { actionCount++ },
                        enabled = enabled,
                        shapes = ButtonDefaults.shapes(),
                    ) {
                        Text(stringResource(R.string.expressive_tonal))
                    }
                    OutlinedButton(
                        onClick = { actionCount++ },
                        enabled = enabled,
                        shapes = ButtonDefaults.shapes(),
                    ) {
                        Text(stringResource(R.string.expressive_outlined))
                    }
                    TextButton(
                        onClick = { actionCount++ },
                        enabled = enabled,
                        shapes = ButtonDefaults.shapes(),
                    ) {
                        Text(stringResource(R.string.expressive_text))
                    }
                }
            }
            ExpressiveCard(
                stringResource(R.string.expressive_progress),
                Modifier.width(cardWidth),
            ) {
                Slider(value = progress, onValueChange = { progress = it }, enabled = enabled)
                LinearWavyProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.fillMaxWidth(),
                    waveSpeed =
                        if (moving) WavyProgressIndicatorDefaults.LinearDeterminateWavelength
                        else 0.dp,
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CircularWavyProgressIndicator(
                        progress = { progress },
                        waveSpeed =
                            if (moving) WavyProgressIndicatorDefaults.CircularWavelength else 0.dp,
                    )
                    Spacer(Modifier.width(16.dp))
                    if (moving) LoadingIndicator() else LoadingIndicator(progress = { progress })
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(stringResource(R.string.expressive_motion), Modifier.weight(1f))
                    Switch(checked = moving, onCheckedChange = { moving = it })
                }
            }
            ExpressiveCard(
                stringResource(R.string.expressive_icon_buttons),
                Modifier.width(cardWidth),
            ) {
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    IconButton(
                        onClick = { actionCount++ },
                        enabled = enabled,
                        shapes = IconButtonDefaults.shapes(),
                    ) {
                        Icon(Icons.Filled.Add, stringResource(R.string.expressive_add))
                    }
                    OutlinedIconButton(
                        onClick = { actionCount++ },
                        enabled = enabled,
                        shapes = IconButtonDefaults.shapes(),
                    ) {
                        Icon(Icons.Filled.Add, stringResource(R.string.expressive_add))
                    }
                    FilledTonalIconButton(
                        onClick = { actionCount++ },
                        enabled = enabled,
                        shapes = IconButtonDefaults.shapes(),
                    ) {
                        Icon(Icons.Filled.Add, stringResource(R.string.expressive_add))
                    }
                    FilledIconButton(
                        onClick = { actionCount++ },
                        enabled = enabled,
                        shapes = IconButtonDefaults.shapes(),
                    ) {
                        Icon(Icons.Filled.Add, stringResource(R.string.expressive_add))
                    }
                    FilledTonalIconToggleButton(
                        checked = bold,
                        onCheckedChange = { bold = it },
                        enabled = enabled,
                        shapes = IconButtonDefaults.toggleableShapes(),
                    ) {
                        Icon(Icons.Filled.FormatBold, stringResource(R.string.expressive_bold))
                    }
                }
            }
            ExpressiveCard(stringResource(R.string.expressive_sizes), Modifier.width(cardWidth)) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    SettingsButtonSize.entries.forEach { size ->
                        SettingsActionButton(
                            label = stringResource(R.string.expressive_create),
                            onClick = { actionCount++ },
                            enabled = enabled,
                            size = size,
                        )
                    }
                }
            }
            ExpressiveCard(
                stringResource(R.string.expressive_navigation_bar),
                Modifier.width(cardWidth),
            ) {
                ShortNavigationBar(windowInsets = WindowInsets(0, 0, 0, 0)) {
                    periods.forEachIndexed { index, label ->
                        ShortNavigationBarItem(
                            selected = barSelection == index,
                            onClick = { barSelection = index },
                            icon = { Icon(Icons.Filled.CalendarMonth, null) },
                            label = { Text(label) },
                            enabled = enabled,
                        )
                    }
                }
            }
            ExpressiveCard(
                stringResource(R.string.expressive_navigation_rail),
                Modifier.width(cardWidth),
            ) {
                WideNavigationRail(
                    modifier = Modifier.height(360.dp),
                    windowInsets = WindowInsets(0, 0, 0, 0),
                ) {
                    periods.forEachIndexed { index, label ->
                        WideNavigationRailItem(
                            selected = railSelection == index,
                            onClick = { railSelection = index },
                            icon = { Icon(Icons.Filled.CalendarMonth, null) },
                            label = { Text(label) },
                            railExpanded = false,
                            enabled = enabled,
                        )
                    }
                }
            }
            ExpressiveCard(
                stringResource(R.string.expressive_native_list),
                Modifier.width(cardWidth),
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    periods.forEachIndexed { index, label ->
                        SegmentedListItem(
                            selected = listSelection == index,
                            onClick = { listSelection = index },
                            shapes = ListItemDefaults.segmentedShapes(index, periods.size),
                            enabled = enabled,
                        ) {
                            Text(label)
                        }
                    }
                }
            }
            ExpressiveCard(
                stringResource(R.string.expressive_fab_menu),
                Modifier.width(cardWidth),
            ) {
                Box(
                    Modifier.fillMaxWidth().heightIn(min = 220.dp),
                    contentAlignment = Alignment.BottomEnd,
                ) {
                    FloatingActionButtonMenu(
                        expanded = fabExpanded,
                        button = {
                            ToggleFloatingActionButton(
                                checked = fabExpanded,
                                onCheckedChange = { if (enabled) fabExpanded = it },
                            ) {
                                Icon(
                                    painter =
                                        rememberVectorPainter(
                                            if (checkedProgress > 0.5f) Icons.Filled.Close
                                            else Icons.Filled.Add
                                        ),
                                    contentDescription = moreActions,
                                    modifier = Modifier.animateIcon({ checkedProgress }),
                                )
                            }
                        },
                    ) {
                        shareActions.forEach { label ->
                            FloatingActionButtonMenuItem(
                                onClick = {
                                    actionCount++
                                    fabExpanded = false
                                },
                                icon = { Icon(Icons.Filled.Share, null) },
                                text = { Text(label) },
                            )
                        }
                    }
                }
            }
            ExpressiveCard(stringResource(R.string.expressive_fab), Modifier.width(cardWidth)) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    SmallFloatingActionButton(onClick = { if (enabled) actionCount++ }) {
                        Icon(Icons.Filled.Add, stringResource(R.string.expressive_add))
                    }
                    FloatingActionButton(onClick = { if (enabled) actionCount++ }) {
                        Icon(Icons.Filled.Add, stringResource(R.string.expressive_add))
                    }
                    MediumFloatingActionButton(onClick = { if (enabled) actionCount++ }) {
                        Icon(
                            Icons.Filled.Add,
                            stringResource(R.string.expressive_add),
                            Modifier.size(FloatingActionButtonDefaults.MediumIconSize),
                        )
                    }
                    LargeFloatingActionButton(onClick = { if (enabled) actionCount++ }) {
                        Icon(
                            Icons.Filled.Add,
                            stringResource(R.string.expressive_add),
                            Modifier.size(FloatingActionButtonDefaults.LargeIconSize),
                        )
                    }
                }
                SmallExtendedFloatingActionButton(onClick = { if (enabled) actionCount++ }) {
                    Text(stringResource(R.string.expressive_create))
                }
                MediumExtendedFloatingActionButton(onClick = { if (enabled) actionCount++ }) {
                    Text(stringResource(R.string.expressive_create))
                }
                LargeExtendedFloatingActionButton(onClick = { if (enabled) actionCount++ }) {
                    Text(stringResource(R.string.expressive_create))
                }
            }
        }
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun ExpressiveCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(modifier) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            content()
        }
    }
}
