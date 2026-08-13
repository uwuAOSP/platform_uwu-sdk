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
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Palette
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
fun CategoryPage(onBack: () -> Unit) {
    SettingsScaffold(
        title = stringResource(R.string.subtitle_category),
        showBackButton = true,
        onNavigateUp = onBack,
        contentTopPadding = 0.dp,
        content = {
            SettingsCategory(stringResource(R.string.category_section1))
            PreferenceRow(
                title = stringResource(R.string.category_demo_item),
                summary = stringResource(R.string.category_section1),
                icon = Icons.Outlined.Palette,
                position = PreferencePosition.Top,
                onClick = {},
            )
            PreferenceGroupSpacer()
            PreferenceRow(
                title = stringResource(R.string.pref_basic_title),
                summary = stringResource(R.string.pref_basic_summary),
                position = PreferencePosition.Bottom,
                onClick = {},
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingsCategory(stringResource(R.string.category_section2))
            PreferenceRow(
                title = stringResource(R.string.category_demo_item),
                summary = stringResource(R.string.category_section2),
                icon = Icons.Outlined.Notifications,
                onClick = {},
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingsCategory(stringResource(R.string.category_section3))
            PreferenceRow(
                title = stringResource(R.string.category_demo_item),
                summary = stringResource(R.string.category_section3),
                position = PreferencePosition.Top,
                onClick = {},
            )
            PreferenceGroupSpacer()
            PreferenceRow(
                title = stringResource(R.string.pref_basic_title),
                summary = stringResource(R.string.pref_with_icon_summary),
                position = PreferencePosition.Bottom,
                onClick = {},
            )
            Spacer(modifier = Modifier.height(24.dp))
        },
    )
}
