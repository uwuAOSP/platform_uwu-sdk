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
import org.uwuaosp.compose.widget.ui.SettingsTopIntro

@Composable
fun TopIntroPage(onBack: () -> Unit) {
    SettingsScaffold(
        title = stringResource(R.string.subtitle_topintro),
        showBackButton = true,
        onNavigateUp = onBack,
        contentTopPadding = 0.dp,
        content = {
            SettingsSectionHeader(stringResource(R.string.subtitle_topintro))
            SettingsTopIntro(text = stringResource(R.string.top_intro_sample))
            Spacer(modifier = Modifier.height(24.dp))
        },
    )
}
