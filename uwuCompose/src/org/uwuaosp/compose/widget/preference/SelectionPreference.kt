/*
 * SPDX-FileCopyrightText: The uwuAOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.uwuaosp.compose.widget.preference

import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role

/** The list item owns the action; the checkbox only displays its state. */
@Composable
fun CheckboxPreferenceRow(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    summary: String? = null,
    enabled: Boolean = true,
) {
    PreferenceItem(
        modifier = modifier,
        enabled = enabled,
        position = LocalPreferenceGroupPosition.current,
        checked = checked,
        onCheckedChange = onCheckedChange,
        role = Role.Checkbox,
        supportingContent = summary?.let { { PreferenceSummary(it) } },
        trailingContent = {
            Checkbox(checked = checked, onCheckedChange = null, enabled = enabled)
        },
        content = { PreferenceTitle(title) },
    )
}

/** Place sibling rows in a selectableGroup owned by the caller. */
@Composable
fun RadioPreferenceRow(
    title: String,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
    summary: String? = null,
    enabled: Boolean = true,
) {
    PreferenceItem(
        modifier = modifier,
        enabled = enabled,
        position = LocalPreferenceGroupPosition.current,
        selected = selected,
        onClick = onSelect,
        role = Role.RadioButton,
        supportingContent = summary?.let { { PreferenceSummary(it) } },
        trailingContent = { RadioButton(selected = selected, onClick = null, enabled = enabled) },
        content = { PreferenceTitle(title) },
    )
}
