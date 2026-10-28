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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

typealias SettingsPreferenceGroupScope =
    org.uwuaosp.compose.widget.preference.SettingsPreferenceGroupScope

// Compatibility entry points for applications using the original settingslib package.
@Composable
fun SettingsPreferenceGroup(
    modifier: Modifier = Modifier,
    content: SettingsPreferenceGroupScope.() -> Unit,
) =
    org.uwuaosp.compose.widget.preference.SettingsPreferenceGroup(
        modifier = modifier,
        content = content,
    )

@Composable
fun SettingsSection(
    title: String,
    modifier: Modifier = Modifier,
    content: SettingsPreferenceGroupScope.() -> Unit,
) =
    org.uwuaosp.compose.widget.preference.SettingsSection(
        title = title,
        modifier = modifier,
        content = content,
    )

@Composable
fun SettingsSectionSpacer() = org.uwuaosp.compose.widget.preference.SettingsSectionSpacer()

@Composable
fun SettingsSectionHeader(title: String, modifier: Modifier = Modifier, bottomPadding: Dp = 8.dp) =
    org.uwuaosp.compose.widget.preference.SettingsSectionHeader(
        title = title,
        modifier = modifier,
        bottomPadding = bottomPadding,
    )
