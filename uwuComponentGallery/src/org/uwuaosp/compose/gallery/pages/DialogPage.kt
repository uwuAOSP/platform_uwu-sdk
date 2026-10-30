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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.uwuaosp.compose.gallery.R
import org.uwuaosp.compose.widget.editor.TextInputPreferenceDialog
import org.uwuaosp.compose.widget.preference.PreferenceRow
import org.uwuaosp.compose.widget.preference.SettingsSectionHeader
import org.uwuaosp.compose.widget.scaffold.SettingsScaffold

@Composable
fun DialogPage(onBack: () -> Unit) {
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var savedValue by rememberSaveable { mutableStateOf("") }

    SettingsScaffold(
        title = stringResource(R.string.category_dialog),
        showBackButton = true,
        onNavigateUp = onBack,
        contentTopPadding = 0.dp,
        content = {
            SettingsSectionHeader(stringResource(R.string.dialog_value_title))
            val displayValue =
                if (savedValue.isEmpty()) {
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
            onDismissRequest = { showDialog = false },
        )
    }
}
