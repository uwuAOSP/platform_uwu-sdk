/*
 * SPDX-FileCopyrightText: UwUniverse
 * SPDX-License-Identifier: Apache-2.0
 */

package org.uwuaosp.compose.gallery.pages

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.VpnKey
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.uwuaosp.compose.gallery.R
import org.uwuaosp.compose.widget.preference.Preference
import org.uwuaosp.compose.widget.preference.PreferenceModel
import org.uwuaosp.compose.widget.preference.PreferenceRow
import org.uwuaosp.compose.widget.preference.SettingsSection
import org.uwuaosp.compose.widget.preference.SettingsSectionSpacer
import org.uwuaosp.compose.widget.scaffold.SettingsScaffold

@Composable
fun PreferencePage(onBack: () -> Unit) {
    var actionCount by rememberSaveable { mutableIntStateOf(0) }
    SettingsScaffold(
        title = stringResource(R.string.category_preference),
        showBackButton = true,
        onNavigateUp = onBack,
        contentTopPadding = 0.dp,
        content = {
            Text(stringResource(R.string.expressive_action_count, actionCount))
            SettingsSection(stringResource(R.string.pref_basic_title)) {
                item {
                    val preferenceTitle = stringResource(R.string.pref_basic_title)
                    val preferenceSummary = stringResource(R.string.pref_basic_summary)
                    Preference(
                        model =
                            object : PreferenceModel {
                                override val title = preferenceTitle
                                override val summary = { preferenceSummary }
                                override val onClick = {
                                    actionCount++
                                    Unit
                                }
                            }
                    )
                }
                item {
                    PreferenceRow(
                        title = stringResource(R.string.pref_with_icon_title),
                        summary = stringResource(R.string.pref_with_icon_summary),
                        icon = Icons.Outlined.Settings,
                        onClick = { actionCount++ },
                    )
                }
            }
            SettingsSectionSpacer()
            SettingsSection(stringResource(R.string.pref_disabled_title)) {
                item {
                    PreferenceRow(
                        title = stringResource(R.string.pref_disabled_title),
                        summary = stringResource(R.string.pref_disabled_summary),
                        enabled = false,
                        icon = Icons.Outlined.VpnKey,
                        onClick = { actionCount++ },
                    )
                }
            }
            SettingsSectionSpacer()
            SettingsSection(stringResource(R.string.pref_long_title)) {
                item {
                    PreferenceRow(
                        title = stringResource(R.string.pref_long_title),
                        summary = stringResource(R.string.pref_long_summary),
                        onClick = { actionCount++ },
                    )
                }
                item {
                    PreferenceRow(
                        title = stringResource(R.string.pref_single_line),
                        summary = stringResource(R.string.pref_basic_summary),
                        showSummary = true,
                        onClick = { actionCount++ },
                    )
                }
                item {
                    PreferenceRow(
                        title = stringResource(R.string.pref_basic_title),
                        summary = null,
                        showSummary = false,
                        icon = Icons.Outlined.Star,
                        onClick = { actionCount++ },
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        },
    )
}
