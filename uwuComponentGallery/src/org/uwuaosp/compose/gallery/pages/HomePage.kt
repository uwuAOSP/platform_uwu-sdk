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
import androidx.compose.material.icons.outlined.Layers
import androidx.compose.material.icons.outlined.Palette
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
import org.uwuaosp.compose.settingslib.PreferenceGroupSpacer
import org.uwuaosp.compose.settingslib.PreferencePosition
import org.uwuaosp.compose.settingslib.SettingsCategory
import org.uwuaosp.compose.settingslib.SettingsScaffold

@Composable
fun HomePage(onNavigate: (GalleryScreen) -> Unit) {
    SettingsScaffold(
        title = stringResource(R.string.home_title),
        contentTopPadding = 0.dp,
        content = {
            SettingsCategory(stringResource(R.string.category_scaffold))
            PreferenceRow(
                title = stringResource(R.string.category_scaffold),
                summary = stringResource(R.string.desc_scaffold),
                icon = Icons.Outlined.Layers,
                onClick = { onNavigate(GalleryScreen.Scaffold) },
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingsCategory(stringResource(R.string.category_preference))
            PreferenceRow(
                title = stringResource(R.string.category_preference),
                summary = stringResource(R.string.desc_preference),
                icon = Icons.Outlined.Settings,
                onClick = { onNavigate(GalleryScreen.Preference) },
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingsCategory(stringResource(R.string.category_switch))
            PreferenceRow(
                title = stringResource(R.string.category_switch),
                summary = stringResource(R.string.desc_switch),
                icon = Icons.Outlined.ToggleOn,
                onClick = { onNavigate(GalleryScreen.Switch) },
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingsCategory(stringResource(R.string.category_applist))
            PreferenceRow(
                title = stringResource(R.string.category_applist),
                summary = stringResource(R.string.desc_applist),
                icon = Icons.Outlined.Apps,
                onClick = { onNavigate(GalleryScreen.AppList) },
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingsCategory(stringResource(R.string.category_dialog))
            PreferenceRow(
                title = stringResource(R.string.category_dialog),
                summary = stringResource(R.string.desc_dialog),
                icon = Icons.Outlined.Edit,
                onClick = { onNavigate(GalleryScreen.Dialog) },
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingsCategory(stringResource(R.string.category_widgets))
            PreferenceRow(
                title = stringResource(R.string.subtitle_footer),
                summary = stringResource(R.string.desc_footer),
                icon = Icons.Outlined.TextSnippet,
                position = PreferencePosition.Top,
                onClick = { onNavigate(GalleryScreen.Footer) },
            )
            PreferenceGroupSpacer()
            PreferenceRow(
                title = stringResource(R.string.subtitle_category),
                summary = stringResource(R.string.desc_category),
                icon = Icons.Outlined.Category,
                position = PreferencePosition.Middle,
                onClick = { onNavigate(GalleryScreen.Category) },
            )
            PreferenceGroupSpacer()
            PreferenceRow(
                title = stringResource(R.string.subtitle_topintro),
                summary = stringResource(R.string.desc_topintro),
                icon = Icons.Outlined.ViewAgenda,
                position = PreferencePosition.Middle,
                onClick = { onNavigate(GalleryScreen.TopIntro) },
            )
            PreferenceGroupSpacer()
            PreferenceRow(
                title = stringResource(R.string.subtitle_typography),
                summary = stringResource(R.string.desc_typography),
                icon = Icons.Outlined.Palette,
                position = PreferencePosition.Middle,
                onClick = { onNavigate(GalleryScreen.Typography) },
            )
            PreferenceGroupSpacer()
            PreferenceRow(
                title = stringResource(R.string.subtitle_icons),
                summary = stringResource(R.string.desc_icons),
                icon = Icons.Outlined.Widgets,
                position = PreferencePosition.Middle,
                onClick = { onNavigate(GalleryScreen.Icons) },
            )
            PreferenceGroupSpacer()
            PreferenceRow(
                title = stringResource(R.string.subtitle_illustration),
                summary = stringResource(R.string.desc_illustration),
                icon = Icons.Outlined.Share,
                position = PreferencePosition.Bottom,
                onClick = { onNavigate(GalleryScreen.Illustration) },
            )
            Spacer(modifier = Modifier.height(24.dp))
        },
    )
}
