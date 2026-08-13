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
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.uwuaosp.compose.gallery.R
import org.uwuaosp.compose.settingslib.MainSwitchPreference
import org.uwuaosp.compose.settingslib.PreferenceGroupSpacer
import org.uwuaosp.compose.settingslib.PreferencePosition
import org.uwuaosp.compose.settingslib.PrimarySwitchPreferenceRow
import org.uwuaosp.compose.settingslib.SettingsCategory
import org.uwuaosp.compose.settingslib.SettingsScaffold
import org.uwuaosp.compose.settingslib.SwitchPreferenceRow

@Composable
fun SwitchPage(onBack: () -> Unit) {
    var mainChecked by remember { mutableStateOf(true) }
    var basicChecked by remember { mutableStateOf(false) }
    var primaryChecked by remember { mutableStateOf(true) }
    var iconChecked by remember { mutableStateOf(false) }

    SettingsScaffold(
        title = stringResource(R.string.category_switch),
        showBackButton = true,
        onNavigateUp = onBack,
        contentTopPadding = 0.dp,
        content = {
            SettingsCategory(stringResource(R.string.switch_main_title))
            MainSwitchPreference(
                title = stringResource(R.string.switch_main_label),
                checked = mainChecked,
                onCheckedChange = { mainChecked = it },
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingsCategory(stringResource(R.string.switch_basic_title))
            SwitchPreferenceRow(
                title = stringResource(R.string.switch_basic_title),
                summary = stringResource(R.string.switch_basic_summary),
                checked = basicChecked,
                onCheckedChange = { basicChecked = it },
                position = PreferencePosition.Top,
            )
            PreferenceGroupSpacer()
            SwitchPreferenceRow(
                title = stringResource(R.string.switch_disabled_title),
                summary = stringResource(R.string.switch_disabled_summary),
                checked = false,
                onCheckedChange = {},
                enabled = false,
                position = PreferencePosition.Bottom,
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingsCategory(stringResource(R.string.switch_primary_title))
            PrimarySwitchPreferenceRow(
                title = stringResource(R.string.switch_primary_title),
                summary = stringResource(R.string.switch_primary_summary),
                checked = primaryChecked,
                onCheckedChange = { primaryChecked = it },
                onClick = {},
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingsCategory(stringResource(R.string.switch_with_icon_title))
            SwitchPreferenceRow(
                title = stringResource(R.string.switch_with_icon_title),
                summary = stringResource(R.string.switch_with_icon_summary),
                checked = iconChecked,
                onCheckedChange = { iconChecked = it },
                icon = Icons.Outlined.DarkMode,
            )
            Spacer(modifier = Modifier.height(24.dp))
        },
    )
}
