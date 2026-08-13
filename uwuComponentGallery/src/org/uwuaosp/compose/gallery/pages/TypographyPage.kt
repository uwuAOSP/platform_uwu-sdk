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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.uwuaosp.compose.gallery.R
import org.uwuaosp.compose.settingslib.SettingsCategory
import org.uwuaosp.compose.settingslib.SettingsScaffold
import org.uwuaosp.compose.settingslib.rememberSettingsTypography

@Composable
fun TypographyPage(onBack: () -> Unit) {
    val typography = rememberSettingsTypography()

    SettingsScaffold(
        title = stringResource(R.string.subtitle_typography),
        showBackButton = true,
        onNavigateUp = onBack,
        contentTopPadding = 0.dp,
        content = {
            SettingsCategory(stringResource(R.string.typography_display_small))
            Text(stringResource(R.string.typography_display_small), style = typography.displaySmall)
            Spacer(modifier = Modifier.height(4.dp))
            SettingsCategory(stringResource(R.string.typography_headline_small))
            Text(stringResource(R.string.typography_headline_small), style = typography.headlineSmall)
            Spacer(modifier = Modifier.height(4.dp))
            SettingsCategory(stringResource(R.string.typography_title_large))
            Text(stringResource(R.string.typography_title_large), style = typography.titleLarge)
            Spacer(modifier = Modifier.height(4.dp))
            SettingsCategory(stringResource(R.string.typography_title_medium))
            Text(stringResource(R.string.typography_title_medium), style = typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            SettingsCategory(stringResource(R.string.typography_title_small))
            Text(stringResource(R.string.typography_title_small), style = typography.titleSmall)
            Spacer(modifier = Modifier.height(4.dp))
            SettingsCategory(stringResource(R.string.typography_body_large))
            Text(stringResource(R.string.typography_body_large), style = typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            SettingsCategory(stringResource(R.string.typography_body_medium))
            Text(stringResource(R.string.typography_body_medium), style = typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            SettingsCategory(stringResource(R.string.typography_body_small))
            Text(stringResource(R.string.typography_body_small), style = typography.bodySmall)
            Spacer(modifier = Modifier.height(4.dp))
            SettingsCategory(stringResource(R.string.typography_label_large))
            Text(stringResource(R.string.typography_label_large), style = typography.labelLarge)
            Spacer(modifier = Modifier.height(4.dp))
            SettingsCategory(stringResource(R.string.typography_label_medium))
            Text(stringResource(R.string.typography_label_medium), style = typography.labelMedium)
            Spacer(modifier = Modifier.height(4.dp))
            SettingsCategory(stringResource(R.string.typography_label_small))
            Text(stringResource(R.string.typography_label_small), style = typography.labelSmall)
            Spacer(modifier = Modifier.height(24.dp))
        },
    )
}
