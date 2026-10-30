/*
 * SPDX-FileCopyrightText: UwUniverse
 * SPDX-License-Identifier: Apache-2.0
 */

@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package org.uwuaosp.compose.gallery.pages

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.uwuaosp.compose.framework.theme.rememberSettingsTypography
import org.uwuaosp.compose.gallery.R
import org.uwuaosp.compose.widget.preference.SettingsSectionHeader
import org.uwuaosp.compose.widget.preference.SwitchPreferenceRow
import org.uwuaosp.compose.widget.scaffold.SettingsScaffold

@Composable
fun TypographyPage(onBack: () -> Unit) {
    val typography = rememberSettingsTypography()
    var emphasized by rememberSaveable { mutableStateOf(false) }

    SettingsScaffold(
        title = stringResource(R.string.subtitle_typography),
        showBackButton = true,
        onNavigateUp = onBack,
        contentTopPadding = 0.dp,
        content = {
            SwitchPreferenceRow(
                title = stringResource(R.string.typography_emphasized),
                summary = stringResource(R.string.typography_emphasized_summary),
                checked = emphasized,
                onCheckedChange = { emphasized = it },
            )
            Spacer(Modifier.height(16.dp))
            SettingsSectionHeader(stringResource(R.string.typography_display_small))
            Text(
                stringResource(R.string.typography_display_small),
                style =
                    if (emphasized) typography.displaySmallEmphasized else typography.displaySmall,
            )
            Spacer(modifier = Modifier.height(4.dp))
            SettingsSectionHeader(stringResource(R.string.typography_headline_small))
            Text(
                stringResource(R.string.typography_headline_small),
                style =
                    if (emphasized) typography.headlineSmallEmphasized else typography.headlineSmall,
            )
            Spacer(modifier = Modifier.height(4.dp))
            SettingsSectionHeader(stringResource(R.string.typography_title_large))
            Text(
                stringResource(R.string.typography_title_large),
                style = if (emphasized) typography.titleLargeEmphasized else typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(4.dp))
            SettingsSectionHeader(stringResource(R.string.typography_title_medium))
            Text(
                stringResource(R.string.typography_title_medium),
                style = if (emphasized) typography.titleMediumEmphasized else typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            SettingsSectionHeader(stringResource(R.string.typography_title_small))
            Text(
                stringResource(R.string.typography_title_small),
                style = if (emphasized) typography.titleSmallEmphasized else typography.titleSmall,
            )
            Spacer(modifier = Modifier.height(4.dp))
            SettingsSectionHeader(stringResource(R.string.typography_body_large))
            Text(
                stringResource(R.string.typography_body_large),
                style = if (emphasized) typography.bodyLargeEmphasized else typography.bodyLarge,
            )
            Spacer(modifier = Modifier.height(4.dp))
            SettingsSectionHeader(stringResource(R.string.typography_body_medium))
            Text(
                stringResource(R.string.typography_body_medium),
                style = if (emphasized) typography.bodyMediumEmphasized else typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            SettingsSectionHeader(stringResource(R.string.typography_body_small))
            Text(
                stringResource(R.string.typography_body_small),
                style = if (emphasized) typography.bodySmallEmphasized else typography.bodySmall,
            )
            Spacer(modifier = Modifier.height(4.dp))
            SettingsSectionHeader(stringResource(R.string.typography_label_large))
            Text(
                stringResource(R.string.typography_label_large),
                style = if (emphasized) typography.labelLargeEmphasized else typography.labelLarge,
            )
            Spacer(modifier = Modifier.height(4.dp))
            SettingsSectionHeader(stringResource(R.string.typography_label_medium))
            Text(
                stringResource(R.string.typography_label_medium),
                style = if (emphasized) typography.labelMediumEmphasized else typography.labelMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            SettingsSectionHeader(stringResource(R.string.typography_label_small))
            Text(
                stringResource(R.string.typography_label_small),
                style = if (emphasized) typography.labelSmallEmphasized else typography.labelSmall,
            )
            Spacer(modifier = Modifier.height(24.dp))
        },
    )
}
