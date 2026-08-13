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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.uwuaosp.compose.gallery.R
import org.uwuaosp.compose.settingslib.PreferenceRow
import org.uwuaosp.compose.settingslib.SettingsCategory
import org.uwuaosp.compose.settingslib.SettingsScaffold
import org.uwuaosp.compose.settingslib.TextInputPreferenceDialog

@Composable
fun DialogPage(onBack: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    var savedValue by remember { mutableStateOf("") }

    SettingsScaffold(
        title = stringResource(R.string.category_dialog),
        showBackButton = true,
        onNavigateUp = onBack,
        contentTopPadding = 0.dp,
        content = {
            SettingsCategory(stringResource(R.string.dialog_value_title))
            val displayValue = if (savedValue.isEmpty()) {
                stringResource(R.string.dialog_default_value)
            } else {
                savedValue
            }
            Text(
                text = stringResource(R.string.dialog_current_value, displayValue),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            PreferenceRow(
                title = stringResource(R.string.dialog_open),
                summary = stringResource(R.string.dialog_label),
                onClick = { showDialog = true },
            )
            Spacer(modifier = Modifier.height(24.dp))
        },
    )

    if (showDialog) {
        TextInputPreferenceDialog(
            title = stringResource(R.string.dialog_title),
            value = savedValue,
            confirmText = stringResource(R.string.dialog_confirm),
            dismissText = stringResource(R.string.dialog_cancel),
            singleLine = true,
            onConfirm = { value ->
                savedValue = value
                showDialog = false
            },
            onDismissRequest = {
                showDialog = false
            },
        )
    }
}
