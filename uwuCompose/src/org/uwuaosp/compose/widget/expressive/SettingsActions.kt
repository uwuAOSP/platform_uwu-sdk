/*
 * SPDX-FileCopyrightText: The uwuAOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package org.uwuaosp.compose.widget.expressive

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Modifier
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
                if (selectedIndex == index) {
                    Icon(Icons.Filled.Check, contentDescription = null)
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

/** The trailing segment anchors a menu; the caller owns expansion and menu actions. */
@OptIn(ExperimentalMaterial3Api::class)
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
) {
    SplitButtonLayout(
        modifier = modifier,
        leadingButton = {
            SplitButtonDefaults.LeadingButton(onClick = onClick, enabled = enabled) { Text(label) }
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
                    SplitButtonDefaults.TrailingButton(
                        checked = expanded,
                        onCheckedChange = onExpandedChange,
                        enabled = enabled,
                    ) {
                        Icon(Icons.Filled.KeyboardArrowDown, menuContentDescription)
                    }
                }
                SettingsDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { onExpandedChange(false) },
                    content = menuContent,
                )
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
