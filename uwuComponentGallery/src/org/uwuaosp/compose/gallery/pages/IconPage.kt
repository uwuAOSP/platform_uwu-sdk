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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bluetooth
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Wifi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.uwuaosp.compose.gallery.R
import org.uwuaosp.compose.settingslib.PreferenceRow
import org.uwuaosp.compose.settingslib.SettingsCategory
import org.uwuaosp.compose.settingslib.SettingsHomepageIcon
import org.uwuaosp.compose.settingslib.SettingsScaffold

@Composable
fun IconPage(onBack: () -> Unit) {
    SettingsScaffold(
        title = stringResource(R.string.subtitle_icons),
        showBackButton = true,
        onNavigateUp = onBack,
        contentTopPadding = 0.dp,
        content = {
            SettingsCategory(stringResource(R.string.icon_basic))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                SettingsHomepageIcon(imageVector = Icons.Outlined.Wifi)
                SettingsHomepageIcon(imageVector = Icons.Outlined.Bluetooth)
                SettingsHomepageIcon(imageVector = Icons.Outlined.Notifications)
                SettingsHomepageIcon(imageVector = Icons.Outlined.Star)
            }
            Spacer(modifier = Modifier.height(8.dp))
            SettingsCategory(stringResource(R.string.icon_preference))
            PreferenceRow(
                title = stringResource(R.string.icon_preference),
                summary = stringResource(R.string.pref_with_icon_summary),
                icon = Icons.Outlined.Wifi,
                onClick = {},
            )
            Spacer(modifier = Modifier.height(24.dp))
        },
    )
}
