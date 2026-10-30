/*
 * SPDX-FileCopyrightText: The uwuAOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package org.uwuaosp.compose.gallery.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.WavyProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.uwuaosp.compose.gallery.R
import org.uwuaosp.compose.widget.preference.CheckboxPreferenceRow
import org.uwuaosp.compose.widget.preference.CustomPreferenceRow
import org.uwuaosp.compose.widget.preference.RadioPreferenceRow
import org.uwuaosp.compose.widget.preference.SettingsSection
import org.uwuaosp.compose.widget.preference.SettingsSectionSpacer
import org.uwuaosp.compose.widget.preference.SwitchPreferenceRow
import org.uwuaosp.compose.widget.scaffold.SettingsScaffold

/** Interactive examples use Material's own components and motion defaults. */
@Composable
fun ExpressivePage(onBack: () -> Unit) {
    var enabled by rememberSaveable { mutableStateOf(true) }
    var selected by rememberSaveable { mutableIntStateOf(0) }
    var checked by rememberSaveable { mutableStateOf(false) }
    var progress by rememberSaveable { mutableFloatStateOf(0.5f) }
    var moving by rememberSaveable { mutableStateOf(true) }

    SettingsScaffold(
        title = stringResource(R.string.category_expressive),
        showBackButton = true,
        onNavigateUp = onBack,
        startCollapsed = false,
    ) {
        SettingsSection(stringResource(R.string.expressive_controls)) {
            item {
                SwitchPreferenceRow(
                    title = stringResource(R.string.expressive_enabled),
                    summary = stringResource(R.string.expressive_enabled_summary),
                    checked = enabled,
                    onCheckedChange = { enabled = it },
                )
            }
            item {
                CheckboxPreferenceRow(
                    title = stringResource(R.string.expressive_checkbox),
                    checked = checked,
                    onCheckedChange = { checked = it },
                    enabled = enabled,
                )
            }
        }
        SettingsSectionSpacer()
        SettingsSection(
            title = stringResource(R.string.expressive_selection),
            modifier = Modifier.selectableGroup(),
        ) {
            item {
                RadioPreferenceRow(
                    title = stringResource(R.string.expressive_option_one),
                    selected = selected == 0,
                    onSelect = { selected = 0 },
                    enabled = enabled,
                )
            }
            item {
                RadioPreferenceRow(
                    title = stringResource(R.string.expressive_option_two),
                    selected = selected == 1,
                    onSelect = { selected = 1 },
                    enabled = enabled,
                )
            }
        }
        SettingsSectionSpacer()
        SettingsSection(stringResource(R.string.expressive_progress)) {
            item {
                CustomPreferenceRow {
                    Slider(
                        value = progress,
                        onValueChange = { progress = it },
                        enabled = enabled,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
            item {
                CustomPreferenceRow {
                    LinearWavyProgressIndicator(
                        progress = { progress },
                        modifier = Modifier.fillMaxWidth(),
                        waveSpeed =
                            if (moving) {
                                WavyProgressIndicatorDefaults.LinearDeterminateWavelength
                            } else 0.dp,
                    )
                }
            }
            item {
                SwitchPreferenceRow(
                    title = stringResource(R.string.expressive_motion),
                    summary = stringResource(R.string.expressive_motion_summary),
                    checked = moving,
                    onCheckedChange = { moving = it },
                )
            }
            item {
                CustomPreferenceRow {
                    if (moving) LoadingIndicator() else LoadingIndicator(progress = { progress })
                    Text(
                        text = stringResource(R.string.expressive_loading),
                        modifier = Modifier.padding(start = 16.dp),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
        SettingsSectionSpacer()
        SettingsSection(stringResource(R.string.expressive_buttons)) {
            item {
                CustomPreferenceRow {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(vertical = 12.dp),
                    ) {
                        Button(onClick = { checked = !checked }, enabled = enabled) {
                            Text(stringResource(R.string.expressive_filled))
                        }
                        FilledTonalButton(onClick = { checked = !checked }, enabled = enabled) {
                            Text(stringResource(R.string.expressive_tonal))
                        }
                        OutlinedButton(onClick = { checked = !checked }, enabled = enabled) {
                            Text(stringResource(R.string.expressive_outlined))
                        }
                        TextButton(onClick = { checked = !checked }, enabled = enabled) {
                            Text(stringResource(R.string.expressive_text))
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(24.dp))
    }
}
