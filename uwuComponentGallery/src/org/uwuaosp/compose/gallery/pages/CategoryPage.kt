/*
 * SPDX-FileCopyrightText: UwUniverse
 * SPDX-License-Identifier: Apache-2.0
 */

package org.uwuaosp.compose.gallery.pages

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Palette
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
import org.uwuaosp.compose.widget.preference.PreferenceRow
import org.uwuaosp.compose.widget.preference.SettingsSection
import org.uwuaosp.compose.widget.preference.SettingsSectionSpacer
import org.uwuaosp.compose.widget.scaffold.SettingsScaffold

@Composable
fun CategoryPage(onBack: () -> Unit) {
    var actionCount by rememberSaveable { mutableIntStateOf(0) }
    SettingsScaffold(
        title = stringResource(R.string.subtitle_category),
        showBackButton = true,
        onNavigateUp = onBack,
        contentTopPadding = 0.dp,
        content = {
            Text(stringResource(R.string.expressive_action_count, actionCount))
            SettingsSection(stringResource(R.string.category_section1)) {
                item {
                    PreferenceRow(
                        title = stringResource(R.string.category_demo_item),
                        summary = stringResource(R.string.category_section1),
                        icon = Icons.Outlined.Palette,
                        onClick = { actionCount++ },
                    )
                }
                item {
                    PreferenceRow(
                        title = stringResource(R.string.pref_basic_title),
                        summary = stringResource(R.string.pref_basic_summary),
                        onClick = { actionCount++ },
                    )
                }
            }
            SettingsSectionSpacer()
            SettingsSection(stringResource(R.string.category_section2)) {
                item {
                    PreferenceRow(
                        title = stringResource(R.string.category_demo_item),
                        summary = stringResource(R.string.category_section2),
                        icon = Icons.Outlined.Notifications,
                        onClick = { actionCount++ },
                    )
                }
            }
            SettingsSectionSpacer()
            SettingsSection(stringResource(R.string.category_section3)) {
                item {
                    PreferenceRow(
                        title = stringResource(R.string.category_demo_item),
                        summary = stringResource(R.string.category_section3),
                        onClick = { actionCount++ },
                    )
                }
                item {
                    PreferenceRow(
                        title = stringResource(R.string.pref_basic_title),
                        summary = stringResource(R.string.pref_with_icon_summary),
                        onClick = { actionCount++ },
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        },
    )
}
