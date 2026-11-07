/*
 * SPDX-FileCopyrightText: The uwuAOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package org.uwuaosp.compose.widget.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SettingsToolbarActionButton(
    imageVector: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconSize: Dp = 24.dp,
    buttonSize: Dp = Dp.Unspecified,
    containerSize: Dp = Dp.Unspecified,
) {
    Box(
        modifier = if (buttonSize != Dp.Unspecified) modifier.size(buttonSize) else modifier,
        contentAlignment = Alignment.Center,
    ) {
        FilledTonalIconButton(
            onClick = onClick,
            shapes = IconButtonDefaults.shapes(),
            modifier =
                if (containerSize != Dp.Unspecified) Modifier.size(containerSize) else Modifier,
        ) {
            Icon(imageVector, contentDescription, modifier = Modifier.size(iconSize))
        }
    }
}
