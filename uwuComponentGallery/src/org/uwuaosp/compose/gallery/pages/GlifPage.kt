/*
 * SPDX-FileCopyrightText: UwUniverse
 * SPDX-License-Identifier: Apache-2.0
 */

package org.uwuaosp.compose.gallery.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
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
import org.uwuaosp.compose.wizard.BottomAppBarButton
import org.uwuaosp.compose.wizard.GlifScaffold

@Composable
fun GlifPage(onBack: () -> Unit) {
    var completed by rememberSaveable { mutableStateOf(false) }

    GlifScaffold(
        imageVector = Icons.Outlined.AutoAwesome,
        title = stringResource(R.string.glif_title),
        description = stringResource(R.string.glif_description),
        actionButton =
            BottomAppBarButton(
                text =
                    stringResource(if (completed) R.string.glif_done else R.string.glif_continue),
                onClick = { completed = true },
            ),
        dismissButton =
            BottomAppBarButton(text = stringResource(R.string.glif_not_now), onClick = onBack),
        onNavigateBack = onBack,
        navigateBackContentDescription = stringResource(R.string.back),
        content = {
            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)) {
                Text(
                    text = stringResource(R.string.glif_content_title),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.glif_content_body),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                if (completed) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.glif_ready),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        },
    )
}
