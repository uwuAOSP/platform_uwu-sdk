/*
 * SPDX-FileCopyrightText: UwUniverse
 * SPDX-License-Identifier: Apache-2.0
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
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Wifi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.uwuaosp.compose.gallery.R
import org.uwuaosp.compose.widget.preference.PreferenceRow
import org.uwuaosp.compose.widget.preference.SettingsSectionHeader
import org.uwuaosp.compose.widget.scaffold.SettingsScaffold
import org.uwuaosp.compose.widget.ui.SettingsHomepageIcon

@Composable
fun IconPage(onBack: () -> Unit) {
    SettingsScaffold(
        title = stringResource(R.string.subtitle_icons),
        showBackButton = true,
        onNavigateUp = onBack,
        contentTopPadding = 0.dp,
        content = {
            SettingsSectionHeader(stringResource(R.string.icon_basic))
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
            SettingsSectionHeader(stringResource(R.string.icon_preference))
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
