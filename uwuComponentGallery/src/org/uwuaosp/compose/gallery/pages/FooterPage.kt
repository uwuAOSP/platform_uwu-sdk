/*
 * SPDX-FileCopyrightText: UwUniverse
 * SPDX-License-Identifier: Apache-2.0
 */

package org.uwuaosp.compose.gallery.pages

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.uwuaosp.compose.gallery.R
import org.uwuaosp.compose.widget.preference.SettingsSectionHeader
import org.uwuaosp.compose.widget.scaffold.SettingsScaffold
import org.uwuaosp.compose.widget.ui.SettingsFooter
import org.uwuaosp.compose.widget.ui.SettingsFooterLegacy

@Composable
fun FooterPage(onBack: () -> Unit) {
    SettingsScaffold(
        title = stringResource(R.string.subtitle_footer),
        showBackButton = true,
        onNavigateUp = onBack,
        contentTopPadding = 0.dp,
        content = {
            SettingsSectionHeader(stringResource(R.string.footer_text_title))
            SettingsFooter(text = stringResource(R.string.footer_text_content))
            Spacer(modifier = Modifier.height(8.dp))
            SettingsSectionHeader(stringResource(R.string.footer_legacy_title))
            SettingsFooterLegacy(text = stringResource(R.string.footer_legacy_content))
            Spacer(modifier = Modifier.height(24.dp))
        },
    )
}
