/*
 * SPDX-FileCopyrightText: The uwuAOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.uwuaosp.compose.widget.editor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.uwuaosp.compose.widget.expressive.SettingsActionButton
import org.uwuaosp.compose.widget.expressive.SettingsButtonStyle
import org.uwuaosp.compose.widget.preference.RadioPreferenceRow
import org.uwuaosp.compose.widget.preference.SettingsPreferenceGroup

/** The caller owns selection and persistence; each row has one accessible selection target. */
@Composable
fun SettingsChoiceList(
    options: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
    summaries: List<String?> = emptyList(),
    enabled: Boolean = true,
) {
    SettingsPreferenceGroup(modifier = modifier.selectableGroup()) {
        options.forEachIndexed { index, label ->
            item {
                RadioPreferenceRow(
                    title = label,
                    summary = summaries.getOrNull(index),
                    selected = selectedIndex == index,
                    enabled = enabled,
                    onSelect = { onSelect(index) },
                )
            }
        }
    }
}

/** Keeps selection dialogs on the same preference and action components as settings pages. */
@Composable
fun SettingsChoiceDialog(
    title: String,
    options: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    onDismissRequest: () -> Unit,
    summaries: List<String?> = emptyList(),
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(title) },
        text = {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                SettingsChoiceList(options, selectedIndex, onSelect, summaries = summaries)
            }
        },
        confirmButton = {},
        dismissButton = {
            SettingsActionButton(
                label = stringResource(android.R.string.cancel),
                onClick = onDismissRequest,
                style = SettingsButtonStyle.Text,
            )
        },
    )
}
