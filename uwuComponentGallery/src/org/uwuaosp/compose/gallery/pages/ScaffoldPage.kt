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
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Layers
import androidx.compose.material3.FilterChip
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
import org.uwuaosp.compose.widget.scaffold.SettingsAppBarScaffold
import org.uwuaosp.compose.widget.scaffold.SettingsCollapsingAppBarScaffold
import org.uwuaosp.compose.widget.scaffold.SettingsScaffold
import org.uwuaosp.compose.widget.ui.SettingsContent

@Composable
fun ScaffoldPage(onBack: () -> Unit) {
    var mode by rememberSaveable { mutableIntStateOf(0) }
    val title = stringResource(R.string.category_scaffold)
    val controls: @Composable () -> Unit = {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 8.dp),
        ) {
            listOf(
                    R.string.scaffold_standard_title,
                    R.string.scaffold_collapsing_title,
                    R.string.scaffold_appbar_title,
                )
                .forEachIndexed { index, label ->
                    FilterChip(
                        selected = mode == index,
                        onClick = { mode = index },
                        label = { androidx.compose.material3.Text(stringResource(label)) },
                    )
                }
        }
    }
    val lazyContent: @Composable (PaddingValues) -> Unit = { padding ->
        SettingsContent {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding).consumeWindowInsets(padding),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                item(key = "controls") { controls() }
                items(count = 50, key = { it }) { index ->
                    ScaffoldDemoRow(index = index, itemCount = 50)
                }
            }
        }
    }
    when (mode) {
        0 ->
            SettingsScaffold(
                title = title,
                showBackButton = true,
                onNavigateUp = onBack,
                useCollapsingToolbar = false,
            ) {
                controls()
                SettingsSection(stringResource(R.string.scaffold_standard_title)) {
                    repeat(50) { index -> item { ScaffoldDemoRow(index) } }
                }
            }
        1 ->
            SettingsCollapsingAppBarScaffold(
                title = title,
                showBackButton = true,
                onNavigateUp = onBack,
                content = lazyContent,
            )
        else ->
            SettingsAppBarScaffold(
                title = title,
                showBackButton = true,
                onNavigateUp = onBack,
                content = lazyContent,
            )
    }
}

@Composable
private fun ScaffoldDemoRow(index: Int, itemCount: Int = 1) {
    PreferenceRow(
        title = "${stringResource(R.string.scaffold_lazy_item)} ${index + 1}",
        summary = stringResource(R.string.scaffold_demo_content),
        icon = Icons.Outlined.Layers,
        index = index,
        itemCount = itemCount,
        onClick = {},
    )
}
