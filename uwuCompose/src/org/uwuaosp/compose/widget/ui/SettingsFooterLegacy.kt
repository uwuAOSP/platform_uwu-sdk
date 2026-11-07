/*
 * SPDX-FileCopyrightText: The uwuAOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.uwuaosp.compose.widget.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun SettingsFooterLegacy(
    text: String,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Outlined.Info,
) {
    if (text.isEmpty()) return
    SettingsFooterLegacy(modifier = modifier, icon = icon) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun SettingsFooterLegacy(
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Outlined.Info,
    content: @Composable RowScope.() -> Unit,
) {
    SettingsFooter(modifier = modifier, icon = icon) {
        Row(Modifier.fillMaxWidth(), content = content)
    }
}
