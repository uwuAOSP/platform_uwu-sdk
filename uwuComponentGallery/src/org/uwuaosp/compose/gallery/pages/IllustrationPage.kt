/*
 * SPDX-FileCopyrightText: UwUniverse
 * SPDX-License-Identifier: Apache-2.0
 */

package org.uwuaosp.compose.gallery.pages

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.uwuaosp.compose.gallery.R
import org.uwuaosp.compose.widget.preference.SettingsSectionHeader
import org.uwuaosp.compose.widget.scaffold.SettingsScaffold

@Composable
fun IllustrationPage(onBack: () -> Unit) {
    SettingsScaffold(
        title = stringResource(R.string.subtitle_illustration),
        showBackButton = true,
        onNavigateUp = onBack,
        contentTopPadding = 0.dp,
        content = {
            SettingsSectionHeader(stringResource(R.string.illustration_demo))
            Text(
                text = stringResource(R.string.illustration_content),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(modifier = Modifier.height(24.dp))
        },
    )
}
