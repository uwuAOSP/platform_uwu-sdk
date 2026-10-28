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

package org.uwuaosp.compose.settingslib

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

// Compatibility entry points for applications using the original settingslib package.
@Composable
fun MainSwitchPreference(
    title: String? = null,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) =
    org.uwuaosp.compose.widget.preference.MainSwitchPreference(
        title = title,
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
    )

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
) =
    org.uwuaosp.compose.widget.preference.SwitchPreferenceRow(
        title = title,
        summary = summary,
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        icon = icon,
        iconContent = iconContent,
        index = index,
        itemCount = itemCount,
        showSummary = showSummary,
    )

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
) =
    org.uwuaosp.compose.widget.preference.PrimarySwitchPreferenceRow(
        title = title,
        summary = summary,
        checked = checked,
        onCheckedChange = onCheckedChange,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        switchEnabled = switchEnabled,
        icon = icon,
        iconContent = iconContent,
        index = index,
        itemCount = itemCount,
        showSummary = showSummary,
    )
