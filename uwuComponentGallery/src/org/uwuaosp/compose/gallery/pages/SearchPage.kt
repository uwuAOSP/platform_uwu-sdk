/*
 * SPDX-FileCopyrightText: The uwuAOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.uwuaosp.compose.gallery.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.uwuaosp.compose.gallery.R
import org.uwuaosp.compose.widget.app.AppListSearchBar
import org.uwuaosp.compose.widget.editor.SettingsSearchField
import org.uwuaosp.compose.widget.preference.SettingsSectionHeader
import org.uwuaosp.compose.widget.preference.SettingsSectionSpacer
import org.uwuaosp.compose.widget.scaffold.SettingsScaffold

@Composable
fun SearchPage(onBack: () -> Unit) {
    var appListQuery by rememberSaveable { mutableStateOf("") }
    var settingsQuery by rememberSaveable { mutableStateOf("") }

    SettingsScaffold(
        title = stringResource(R.string.search_demo_title),
        showBackButton = true,
        onNavigateUp = onBack,
        contentTopPadding = 0.dp,
    ) {
        SettingsSectionHeader(
            title = stringResource(R.string.search_demo_app_list_title),
            bottomPadding = 0.dp,
        )
        AppListSearchBar(
            query = appListQuery,
            searchPlaceholder = stringResource(R.string.search_demo_app_list_placeholder),
            clearSearchContentDescription = stringResource(R.string.search_demo_clear),
            onQueryChange = { appListQuery = it },
        )
        SettingsSectionSpacer()
        SettingsSectionSpacer()
        SettingsSectionHeader(stringResource(R.string.search_demo_settings_title))
        SettingsSearchField(
            query = settingsQuery,
            onQueryChange = { settingsQuery = it },
            placeholder = stringResource(R.string.search_demo_settings_placeholder),
            clearContentDescription = stringResource(R.string.search_demo_clear),
        )
    }
}
