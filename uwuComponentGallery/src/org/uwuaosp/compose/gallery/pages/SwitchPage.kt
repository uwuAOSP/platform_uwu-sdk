/*
 * SPDX-FileCopyrightText: UwUniverse
 * SPDX-License-Identifier: Apache-2.0
 */

package org.uwuaosp.compose.gallery.pages

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.uwuaosp.compose.gallery.R
import org.uwuaosp.compose.widget.preference.MainSwitchPreference
import org.uwuaosp.compose.widget.preference.PrimarySwitchPreferenceRow
import org.uwuaosp.compose.widget.preference.SettingsSection
import org.uwuaosp.compose.widget.preference.SettingsSectionSpacer
import org.uwuaosp.compose.widget.preference.SwitchPreferenceRow
import org.uwuaosp.compose.widget.scaffold.SettingsScaffold

@Composable
fun SwitchPage(onBack: () -> Unit) {
    var mainChecked by rememberSaveable { mutableStateOf(true) }
    var basicChecked by rememberSaveable { mutableStateOf(false) }
    var primaryChecked by rememberSaveable { mutableStateOf(true) }
    var iconChecked by rememberSaveable { mutableStateOf(false) }

    SettingsScaffold(
        title = stringResource(R.string.category_switch),
        showBackButton = true,
        onNavigateUp = onBack,
        contentTopPadding = 0.dp,
        content = {
            SettingsSection(stringResource(R.string.switch_main_title)) {
                item {
                    MainSwitchPreference(
                        title = stringResource(R.string.switch_main_label),
                        checked = mainChecked,
                        onCheckedChange = { mainChecked = it },
                    )
                }
            }
            SettingsSectionSpacer()
            SettingsSection(stringResource(R.string.switch_basic_title)) {
                item {
                    SwitchPreferenceRow(
                        title = stringResource(R.string.switch_basic_title),
                        summary = stringResource(R.string.switch_basic_summary),
                        checked = basicChecked,
                        onCheckedChange = { basicChecked = it },
                    )
                }
                item {
                    SwitchPreferenceRow(
                        title = stringResource(R.string.switch_disabled_title),
                        summary = stringResource(R.string.switch_disabled_summary),
                        checked = false,
                        onCheckedChange = {},
                        enabled = false,
                    )
                }
            }
            SettingsSectionSpacer()
            SettingsSection(stringResource(R.string.switch_primary_title)) {
                item {
                    PrimarySwitchPreferenceRow(
                        title = stringResource(R.string.switch_primary_title),
                        summary = stringResource(R.string.switch_primary_summary),
                        checked = primaryChecked,
                        onCheckedChange = { primaryChecked = it },
                        onClick = {},
                    )
                }
            }
            SettingsSectionSpacer()
            SettingsSection(stringResource(R.string.switch_with_icon_title)) {
                item {
                    SwitchPreferenceRow(
                        title = stringResource(R.string.switch_with_icon_title),
                        summary = stringResource(R.string.switch_with_icon_summary),
                        checked = iconChecked,
                        onCheckedChange = { iconChecked = it },
                        icon = Icons.Outlined.DarkMode,
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        },
    )
}
