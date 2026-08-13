/*
 * Copyright (C) 2026 UwUniverse
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

package org.uwuaosp.compose.gallery.pages

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.VpnKey
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.uwuaosp.compose.gallery.R
import org.uwuaosp.compose.settingslib.PreferenceGroupSpacer
import org.uwuaosp.compose.settingslib.PreferencePosition
import org.uwuaosp.compose.settingslib.PreferenceRow
import org.uwuaosp.compose.settingslib.SettingsCategory
import org.uwuaosp.compose.settingslib.SettingsScaffold

@Composable
fun PreferencePage(onBack: () -> Unit) {
    SettingsScaffold(
        title = stringResource(R.string.category_preference),
        showBackButton = true,
        onNavigateUp = onBack,
        contentTopPadding = 0.dp,
        content = {
            SettingsCategory(stringResource(R.string.pref_basic_title))
            PreferenceRow(
                title = stringResource(R.string.pref_basic_title),
                summary = stringResource(R.string.pref_basic_summary),
                position = PreferencePosition.Top,
                onClick = {},
            )
            PreferenceGroupSpacer()
            PreferenceRow(
                title = stringResource(R.string.pref_with_icon_title),
                summary = stringResource(R.string.pref_with_icon_summary),
                icon = Icons.Outlined.Settings,
                position = PreferencePosition.Bottom,
                onClick = {},
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingsCategory(stringResource(R.string.pref_disabled_title))
            PreferenceRow(
                title = stringResource(R.string.pref_disabled_title),
                summary = stringResource(R.string.pref_disabled_summary),
                enabled = false,
                icon = Icons.Outlined.VpnKey,
                onClick = {},
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingsCategory(stringResource(R.string.pref_long_title))
            PreferenceRow(
                title = stringResource(R.string.pref_long_title),
                summary = stringResource(R.string.pref_long_summary),
                position = PreferencePosition.Top,
                onClick = {},
            )
            PreferenceGroupSpacer()
            PreferenceRow(
                title = stringResource(R.string.pref_single_line),
                summary = stringResource(R.string.pref_basic_summary),
                showSummary = true,
                position = PreferencePosition.Middle,
                onClick = {},
            )
            PreferenceGroupSpacer()
            PreferenceRow(
                title = stringResource(R.string.pref_basic_title),
                summary = null,
                showSummary = false,
                icon = Icons.Outlined.Star,
                position = PreferencePosition.Bottom,
                onClick = {},
            )
            Spacer(modifier = Modifier.height(24.dp))
        },
    )
}
