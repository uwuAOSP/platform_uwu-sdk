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
import androidx.compose.material.icons.outlined.Layers
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
fun ScaffoldPage(onBack: () -> Unit) {
    SettingsScaffold(
        title = stringResource(R.string.category_scaffold),
        showBackButton = true,
        onNavigateUp = onBack,
        contentTopPadding = 0.dp,
        content = {
            SettingsCategory(stringResource(R.string.scaffold_standard_title))
            for (i in 1..5) {
                val position = when (i) {
                    1 -> PreferencePosition.Top
                    5 -> PreferencePosition.Bottom
                    else -> PreferencePosition.Middle
                }
                PreferenceRow(
                    title = "${stringResource(R.string.scaffold_lazy_item)} $i",
                    summary = stringResource(R.string.scaffold_demo_content),
                    icon = Icons.Outlined.Layers,
                    position = position,
                    onClick = {},
                )
                if (i < 5) {
                    PreferenceGroupSpacer()
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        },
    )
}
