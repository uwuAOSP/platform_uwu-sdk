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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.uwuaosp.compose.gallery.R
import org.uwuaosp.compose.settingslib.SettingsCategory
import org.uwuaosp.compose.settingslib.SettingsFooter
import org.uwuaosp.compose.settingslib.SettingsFooterLegacy
import org.uwuaosp.compose.settingslib.SettingsScaffold

@Composable
fun FooterPage(onBack: () -> Unit) {
    SettingsScaffold(
        title = stringResource(R.string.subtitle_footer),
        showBackButton = true,
        onNavigateUp = onBack,
        contentTopPadding = 0.dp,
        content = {
            SettingsCategory(stringResource(R.string.footer_text_title))
            SettingsFooter(text = stringResource(R.string.footer_text_content))
            Spacer(modifier = Modifier.height(8.dp))
            SettingsCategory(stringResource(R.string.footer_legacy_title))
            SettingsFooterLegacy(text = stringResource(R.string.footer_legacy_content))
            Spacer(modifier = Modifier.height(24.dp))
        },
    )
}
