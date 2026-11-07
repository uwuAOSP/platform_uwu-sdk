/*
 * SPDX-FileCopyrightText: The uwuAOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package org.uwuaosp.compose.widget.expressive

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.SplitButtonDefaults
import androidx.compose.material3.SplitButtonLayout
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.ToggleButton
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import org.uwuaosp.compose.widget.menu.SettingsDropdownMenu

/** A connected single-choice group. Selection belongs to the caller. */
@Composable
fun SettingsConnectedButtonGroup(
    options: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: (@Composable (Int) -> Unit)? = null,
) {
    FlowRow(
        modifier = modifier.selectableGroup(),
        horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween),
        verticalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween),
    ) {
        options.forEachIndexed { index, label ->
            ToggleButton(
                checked = selectedIndex == index,
                onCheckedChange = { if (selectedIndex != index) onSelect(index) },
                enabled = enabled,
                modifier = Modifier.semantics { role = Role.RadioButton },
                shapes =
                    when {
                        options.size == 1 -> ToggleButtonDefaults.shapes()
                        index == 0 -> ButtonGroupDefaults.connectedLeadingButtonShapes()
                        index == options.lastIndex ->
                            ButtonGroupDefaults.connectedTrailingButtonShapes()
                        else -> ButtonGroupDefaults.connectedMiddleButtonShapes()
                    },
            ) {
                if (leadingIcon != null || selectedIndex == index) {
                    if (leadingIcon != null) leadingIcon(index)
                    else Icon(Icons.Filled.Check, contentDescription = null)
                    androidx.compose.foundation.layout.Spacer(
                        Modifier.size(ToggleButtonDefaults.IconSpacing)
                    )
                }
                Text(label)
            }
        }
    }
}

/** Uses the native toolbar container, expansion motion, and color roles. */
@Composable
fun SettingsFloatingToolbar(
    expanded: Boolean,
    modifier: Modifier = Modifier,
    vibrant: Boolean = true,
    leadingContent: (@Composable RowScope.() -> Unit)? = null,
    trailingContent: (@Composable RowScope.() -> Unit)? = null,
    content: @Composable RowScope.() -> Unit,
) {
    HorizontalFloatingToolbar(
        expanded = expanded,
        modifier = modifier,
        leadingContent = leadingContent,
        trailingContent = trailingContent,
        colors =
            if (vibrant) FloatingToolbarDefaults.vibrantFloatingToolbarColors()
            else FloatingToolbarDefaults.standardFloatingToolbarColors(),
        content = content,
    )
}

enum class SettingsSplitButtonStyle {
    Filled,
    Tonal,
    Outlined,
    Elevated,
}

/** Compatibility entry point for the standard filled split button. */
@Composable
fun SettingsSplitButton(
    label: String,
    expanded: Boolean,
    onClick: () -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    menuContentDescription: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    menuContent: @Composable ColumnScope.() -> Unit,
) =
    SettingsSplitButton(
        label = label,
        expanded = expanded,
        onClick = onClick,
        onExpandedChange = onExpandedChange,
        menuContentDescription = menuContentDescription,
        size = SettingsButtonSize.Small,
        modifier = modifier,
        enabled = enabled,
        menuContent = menuContent,
    )

/** Both segments take their geometry and colors from the native size and style variants. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsSplitButton(
    label: String,
    expanded: Boolean,
    onClick: () -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    menuContentDescription: String,
    size: SettingsButtonSize,
    style: SettingsSplitButtonStyle = SettingsSplitButtonStyle.Filled,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    menuContent: @Composable ColumnScope.() -> Unit,
) {
    val height =
        when (size) {
            SettingsButtonSize.ExtraSmall -> SplitButtonDefaults.ExtraSmallContainerHeight
            SettingsButtonSize.Small -> SplitButtonDefaults.SmallContainerHeight
            SettingsButtonSize.Medium -> SplitButtonDefaults.MediumContainerHeight
            SettingsButtonSize.Large -> SplitButtonDefaults.LargeContainerHeight
            SettingsButtonSize.ExtraLarge -> SplitButtonDefaults.ExtraLargeContainerHeight
        }
    val leadingContent: @Composable RowScope.() -> Unit = {
        if (leadingIcon != null) {
            Icon(
                leadingIcon,
                contentDescription = null,
                modifier = Modifier.size(SplitButtonDefaults.leadingButtonIconSizeFor(height)),
            )
            androidx.compose.foundation.layout.Spacer(
                Modifier.size(ButtonDefaults.iconSpacingFor(height))
            )
        }
        Text(label, style = ButtonDefaults.textStyleFor(height))
    }
    val trailingContent: @Composable RowScope.() -> Unit = {
        val rotation by
            animateFloatAsState(
                targetValue = if (expanded) 180f else 0f,
                animationSpec = MaterialTheme.motionScheme.defaultSpatialSpec(),
                label = "splitMenuArrow",
            )
        Icon(
            Icons.Filled.KeyboardArrowDown,
            menuContentDescription,
            Modifier.size(SplitButtonDefaults.trailingButtonIconSizeFor(height)).graphicsLayer {
                rotationZ = rotation
            },
        )
    }
    SplitButtonLayout(
        modifier = modifier,
        leadingButton = {
            when (style) {
                SettingsSplitButtonStyle.Filled ->
                    SplitButtonDefaults.LeadingButton(
                        onClick = onClick,
                        enabled = enabled,
                        modifier = Modifier.heightIn(min = height),
                        shapes = SplitButtonDefaults.leadingButtonShapesFor(height),
                        contentPadding = SplitButtonDefaults.leadingButtonContentPaddingFor(height),
                        content = leadingContent,
                    )
                SettingsSplitButtonStyle.Tonal ->
                    SplitButtonDefaults.TonalLeadingButton(
                        onClick = onClick,
                        enabled = enabled,
                        modifier = Modifier.heightIn(min = height),
                        shapes = SplitButtonDefaults.leadingButtonShapesFor(height),
                        contentPadding = SplitButtonDefaults.leadingButtonContentPaddingFor(height),
                        content = leadingContent,
                    )
                SettingsSplitButtonStyle.Outlined ->
                    SplitButtonDefaults.OutlinedLeadingButton(
                        onClick = onClick,
                        enabled = enabled,
                        modifier = Modifier.heightIn(min = height),
                        shapes = SplitButtonDefaults.leadingButtonShapesFor(height),
                        contentPadding = SplitButtonDefaults.leadingButtonContentPaddingFor(height),
                        content = leadingContent,
                    )
                SettingsSplitButtonStyle.Elevated ->
                    SplitButtonDefaults.ElevatedLeadingButton(
                        onClick = onClick,
                        enabled = enabled,
                        modifier = Modifier.heightIn(min = height),
                        shapes = SplitButtonDefaults.leadingButtonShapesFor(height),
                        contentPadding = SplitButtonDefaults.leadingButtonContentPaddingFor(height),
                        content = leadingContent,
                    )
            }
        },
        trailingButton = {
            Box {
                TooltipBox(
                    positionProvider =
                        TooltipDefaults.rememberTooltipPositionProvider(
                            TooltipAnchorPosition.Above
                        ),
                    tooltip = { PlainTooltip { Text(menuContentDescription) } },
                    state = rememberTooltipState(),
                ) {
                    when (style) {
                        SettingsSplitButtonStyle.Filled ->
                            SplitButtonDefaults.TrailingButton(
                                checked = expanded,
                                onCheckedChange = onExpandedChange,
                                enabled = enabled,
                                modifier = Modifier.heightIn(min = height),
                                shapes = SplitButtonDefaults.trailingButtonShapesFor(height),
                                contentPadding =
                                    SplitButtonDefaults.trailingButtonContentPaddingFor(height),
                                content = trailingContent,
                            )
                        SettingsSplitButtonStyle.Tonal ->
                            SplitButtonDefaults.TonalTrailingButton(
                                checked = expanded,
                                onCheckedChange = onExpandedChange,
                                enabled = enabled,
                                modifier = Modifier.heightIn(min = height),
                                shapes = SplitButtonDefaults.trailingButtonShapesFor(height),
                                contentPadding =
                                    SplitButtonDefaults.trailingButtonContentPaddingFor(height),
                                content = trailingContent,
                            )
                        SettingsSplitButtonStyle.Outlined ->
                            SplitButtonDefaults.OutlinedTrailingButton(
                                checked = expanded,
                                onCheckedChange = onExpandedChange,
                                enabled = enabled,
                                modifier = Modifier.heightIn(min = height),
                                shapes = SplitButtonDefaults.trailingButtonShapesFor(height),
                                contentPadding =
                                    SplitButtonDefaults.trailingButtonContentPaddingFor(height),
                                content = trailingContent,
                            )
                        SettingsSplitButtonStyle.Elevated ->
                            SplitButtonDefaults.ElevatedTrailingButton(
                                checked = expanded,
                                onCheckedChange = onExpandedChange,
                                enabled = enabled,
                                modifier = Modifier.heightIn(min = height),
                                shapes = SplitButtonDefaults.trailingButtonShapesFor(height),
                                contentPadding =
                                    SplitButtonDefaults.trailingButtonContentPaddingFor(height),
                                content = trailingContent,
                            )
                    }
                }
                SettingsDropdownMenu(expanded, { onExpandedChange(false) }, content = menuContent)
            }
        },
    )
}

/** Native button size names map to the platform's token scale. */
enum class SettingsButtonSize {
    ExtraSmall,
    Small,
    Medium,
    Large,
    ExtraLarge,
}

enum class SettingsButtonStyle {
    Filled,
    Tonal,
    Outlined,
    Text,
    Elevated,
}

@Composable
fun SettingsActionButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    size: SettingsButtonSize = SettingsButtonSize.Small,
    style: SettingsButtonStyle = SettingsButtonStyle.Filled,
) {
    val height =
        when (size) {
            SettingsButtonSize.ExtraSmall -> ButtonDefaults.ExtraSmallContainerHeight
            SettingsButtonSize.Small -> ButtonDefaults.MinHeight
            SettingsButtonSize.Medium -> ButtonDefaults.MediumContainerHeight
            SettingsButtonSize.Large -> ButtonDefaults.LargeContainerHeight
            SettingsButtonSize.ExtraLarge -> ButtonDefaults.ExtraLargeContainerHeight
        }
    val shapes = ButtonDefaults.shapesFor(height)
    val padding = ButtonDefaults.contentPaddingFor(height)
    val sized = modifier.heightIn(min = height)
    val content: @Composable RowScope.() -> Unit = {
        Text(label, style = ButtonDefaults.textStyleFor(height))
    }
    when (style) {
        SettingsButtonStyle.Elevated ->
            ElevatedButton(
                onClick = onClick,
                modifier = sized,
                enabled = enabled,
                shapes = shapes,
                contentPadding = padding,
                content = content,
            )
        SettingsButtonStyle.Filled ->
            Button(
                onClick = onClick,
                modifier = sized,
                enabled = enabled,
                shapes = shapes,
                contentPadding = padding,
                content = content,
            )
        SettingsButtonStyle.Tonal ->
            FilledTonalButton(
                onClick = onClick,
                modifier = sized,
                enabled = enabled,
                shapes = shapes,
                contentPadding = padding,
                content = content,
            )
        SettingsButtonStyle.Outlined ->
            OutlinedButton(
                onClick = onClick,
                modifier = sized,
                enabled = enabled,
                shapes = shapes,
                contentPadding = padding,
                content = content,
            )
        SettingsButtonStyle.Text ->
            TextButton(
                onClick = onClick,
                modifier = sized,
                enabled = enabled,
                shapes = shapes,
                contentPadding = padding,
                content = content,
            )
    }
}
