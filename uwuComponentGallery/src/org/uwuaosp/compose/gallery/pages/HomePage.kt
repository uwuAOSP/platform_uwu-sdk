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
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Layers
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.TextSnippet
import androidx.compose.material.icons.outlined.ToggleOn
import androidx.compose.material.icons.outlined.ViewAgenda
import androidx.compose.material.icons.outlined.Widgets
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.uwuaosp.compose.gallery.GalleryScreen
import org.uwuaosp.compose.gallery.R
import org.uwuaosp.compose.settingslib.PreferenceRow
import org.uwuaosp.compose.settingslib.SettingsScaffold
import org.uwuaosp.compose.settingslib.SettingsSection
import org.uwuaosp.compose.settingslib.SettingsSectionSpacer

@Composable
fun HomePage(onNavigate: (GalleryScreen) -> Unit) {
    SettingsScaffold(
        title = stringResource(R.string.home_title),
        contentTopPadding = 0.dp,
        content = {
            SettingsSection(stringResource(R.string.category_scaffold)) {
                item {
                    PreferenceRow(
                        title = stringResource(R.string.category_scaffold),
                        summary = stringResource(R.string.desc_scaffold),
                        icon = Icons.Outlined.Layers,
                        onClick = { onNavigate(GalleryScreen.Scaffold) },
                    )
                }
            }
            SettingsSectionSpacer()
            SettingsSection(stringResource(R.string.category_preference)) {
                item {
                    PreferenceRow(
                        title = stringResource(R.string.category_preference),
                        summary = stringResource(R.string.desc_preference),
                        icon = Icons.Outlined.Settings,
                        onClick = { onNavigate(GalleryScreen.Preference) },
                    )
                }
            }
            SettingsSectionSpacer()
            SettingsSection(stringResource(R.string.category_switch)) {
                item {
                    PreferenceRow(
                        title = stringResource(R.string.category_switch),
                        summary = stringResource(R.string.desc_switch),
                        icon = Icons.Outlined.ToggleOn,
                        onClick = { onNavigate(GalleryScreen.Switch) },
                    )
                }
            }
            SettingsSectionSpacer()
            SettingsSection(stringResource(R.string.category_applist)) {
                item {
                    PreferenceRow(
                        title = stringResource(R.string.category_applist),
                        summary = stringResource(R.string.desc_applist),
                        icon = Icons.Outlined.Apps,
                        onClick = { onNavigate(GalleryScreen.AppList) },
                    )
                }
            }
            SettingsSectionSpacer()
            SettingsSection(stringResource(R.string.category_dialog)) {
                item {
                    PreferenceRow(
                        title = stringResource(R.string.category_dialog),
                        summary = stringResource(R.string.desc_dialog),
                        icon = Icons.Outlined.Edit,
                        onClick = { onNavigate(GalleryScreen.Dialog) },
                    )
                }
            }
            SettingsSectionSpacer()
            SettingsSection(stringResource(R.string.category_glif)) {
                item {
                    PreferenceRow(
                        title = stringResource(R.string.glif_title),
                        summary = stringResource(R.string.desc_glif),
                        icon = Icons.Outlined.AutoAwesome,
                        onClick = { onNavigate(GalleryScreen.Glif) },
                    )
                }
            }
            SettingsSectionSpacer()
            SettingsSection(stringResource(R.string.category_widgets)) {
                item {
                    PreferenceRow(
                        title = stringResource(R.string.subtitle_footer),
                        summary = stringResource(R.string.desc_footer),
                        icon = Icons.Outlined.TextSnippet,
                        onClick = { onNavigate(GalleryScreen.Footer) },
                    )
                }
                item {
                    PreferenceRow(
                        title = stringResource(R.string.subtitle_category),
                        summary = stringResource(R.string.desc_category),
                        icon = Icons.Outlined.Category,
                        onClick = { onNavigate(GalleryScreen.Category) },
                    )
                }
                item {
                    PreferenceRow(
                        title = stringResource(R.string.subtitle_topintro),
                        summary = stringResource(R.string.desc_topintro),
                        icon = Icons.Outlined.ViewAgenda,
                        onClick = { onNavigate(GalleryScreen.TopIntro) },
                    )
                }
                item {
                    PreferenceRow(
                        title = stringResource(R.string.subtitle_typography),
                        summary = stringResource(R.string.desc_typography),
                        icon = Icons.Outlined.Palette,
                        onClick = { onNavigate(GalleryScreen.Typography) },
                    )
                }
                item {
                    PreferenceRow(
                        title = stringResource(R.string.subtitle_icons),
                        summary = stringResource(R.string.desc_icons),
                        icon = Icons.Outlined.Widgets,
                        onClick = { onNavigate(GalleryScreen.Icons) },
                    )
                }
                item {
                    PreferenceRow(
                        title = stringResource(R.string.subtitle_illustration),
                        summary = stringResource(R.string.desc_illustration),
                        icon = Icons.Outlined.Share,
                        onClick = { onNavigate(GalleryScreen.Illustration) },
                    )
                }
                item {
                    PreferenceRow(
                        title = stringResource(R.string.subtitle_search),
                        summary = stringResource(R.string.desc_search),
                        icon = Icons.Outlined.Search,
                        onClick = { onNavigate(GalleryScreen.Search) },
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        },
    )
}
