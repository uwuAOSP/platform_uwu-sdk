/*
 * SPDX-FileCopyrightText: The uwuAOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.uwuaosp.compose.widget.preference

import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role

@Composable
fun MainSwitchPreference(
    title: String? = null,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    PreferenceItem(
        modifier = modifier,
        checked = checked,
        onCheckedChange = onCheckedChange,
        role = Role.Switch,
        trailingContent = { Switch(checked = checked, onCheckedChange = null) },
        content = { title?.let { PreferenceTitle(it) } },
    )
}

@Composable
fun SwitchPreferenceRow(
    title: String,
    summary: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector? = null,
    iconContent: (@Composable () -> Unit)? = null,
    index: Int = 0,
    itemCount: Int = 1,
    showSummary: Boolean = true,
) {
    PreferenceItem(
        modifier = modifier,
        enabled = enabled,
        position =
            if (itemCount > 1) preferenceGroupPosition(index, itemCount)
            else LocalPreferenceGroupPosition.current,
        role = Role.Switch,
        checked = checked,
        onCheckedChange = onCheckedChange,
        leadingContent =
            iconContent
                ?: if (icon != null) {
                    { PreferenceIcon(icon) }
                } else null,
        supportingContent =
            if (showSummary) {
                { PreferenceSummary(summary) }
            } else null,
        trailingContent = { Switch(checked = checked, onCheckedChange = null, enabled = enabled) },
        content = { PreferenceTitle(title) },
    )
}

/** Navigation and the trailing switch have independent actions. */
@Composable
fun PrimarySwitchPreferenceRow(
    title: String,
    summary: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    switchEnabled: Boolean = enabled,
    icon: ImageVector? = null,
    iconContent: (@Composable () -> Unit)? = null,
    index: Int = 0,
    itemCount: Int = 1,
    showSummary: Boolean = true,
) {
    PreferenceRow(
        title = title,
        summary = summary,
        modifier = modifier,
        enabled = enabled,
        icon = icon,
        iconContent = iconContent,
        index = index,
        itemCount = itemCount,
        showSummary = showSummary,
        onClick = onClick,
        trailingContent = {
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                enabled = enabled && switchEnabled,
            )
        },
    )
}
