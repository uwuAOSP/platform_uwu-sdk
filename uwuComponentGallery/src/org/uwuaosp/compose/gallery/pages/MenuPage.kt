/*
 * SPDX-FileCopyrightText: The uwuAOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.uwuaosp.compose.gallery.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import org.uwuaosp.compose.gallery.R
import org.uwuaosp.compose.widget.menu.SettingsDropdownMenu
import org.uwuaosp.compose.widget.menu.SettingsMenuGroup
import org.uwuaosp.compose.widget.menu.SettingsMenuItem
import org.uwuaosp.compose.widget.preference.PreferenceRow
import org.uwuaosp.compose.widget.preference.SettingsSection
import org.uwuaosp.compose.widget.preference.SettingsSectionSpacer
import org.uwuaosp.compose.widget.preference.SwitchPreferenceRow
import org.uwuaosp.compose.widget.scaffold.SettingsScaffold

@Composable
fun MenuPage(onBack: () -> Unit) {
    var selected by rememberSaveable { mutableIntStateOf(0) }
    var read by rememberSaveable { mutableIntStateOf(0) }
    var write by rememberSaveable { mutableIntStateOf(1) }
    var enabled by rememberSaveable { mutableStateOf(true) }
    var singleExpanded by rememberSaveable { mutableStateOf(false) }
    var groupedExpanded by rememberSaveable { mutableStateOf(false) }
    val labels =
        listOf(
            stringResource(R.string.menu_allow),
            stringResource(R.string.menu_ask),
            stringResource(R.string.menu_deny),
        )
    val singleLabels = labels + stringResource(R.string.menu_long_option)
    SettingsScaffold(
        title = stringResource(R.string.category_menus),
        showBackButton = true,
        onNavigateUp = onBack,
        startCollapsed = false,
    ) {
        SettingsSection(stringResource(R.string.menu_single_choice)) {
            item {
                Box {
                    PreferenceRow(
                        title = stringResource(R.string.menu_single_choice),
                        summary = singleLabels[selected],
                        enabled = enabled,
                        onClick = { singleExpanded = true },
                    )
                    SettingsDropdownMenu(singleExpanded, { singleExpanded = false }) {
                        SettingsMenuGroup {
                            singleLabels.forEachIndexed { index, label ->
                                SettingsMenuItem(
                                    text = label,
                                    selected = selected == index,
                                    enabled = index != 2,
                                    index = index,
                                    itemCount = singleLabels.size,
                                    onClick = {
                                        selected = index
                                        singleExpanded = false
                                    },
                                )
                            }
                        }
                    }
                }
            }
            item {
                SwitchPreferenceRow(
                    title = stringResource(R.string.menu_enabled),
                    summary = stringResource(R.string.menu_disabled_example),
                    checked = enabled,
                    onCheckedChange = { enabled = it },
                )
            }
        }
        SettingsSectionSpacer()
        SettingsSection(stringResource(R.string.menu_grouped_choice)) {
            item {
                Box {
                    PreferenceRow(
                        title = stringResource(R.string.menu_grouped_choice),
                        summary =
                            stringResource(
                                R.string.menu_group_summary,
                                labels[read],
                                labels[write],
                            ),
                        onClick = { groupedExpanded = true },
                    )
                    SettingsDropdownMenu(groupedExpanded, { groupedExpanded = false }) {
                        repeat(2) { group ->
                            SettingsMenuGroup(
                                index = group,
                                groupCount = 2,
                                title =
                                    stringResource(
                                        if (group == 0) R.string.menu_read else R.string.menu_write
                                    ),
                            ) {
                                labels.forEachIndexed { index, label ->
                                    SettingsMenuItem(
                                        text = label,
                                        selected = (if (group == 0) read else write) == index,
                                        index = index,
                                        itemCount = labels.size,
                                        onClick = {
                                            if (group == 0) read = index else write = index
                                            groupedExpanded = false
                                        },
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
