/*
 * SPDX-FileCopyrightText: The uwuAOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.uwuaosp.compose.widget.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** Gives card content its available width without moving state when the column count changes. */
@Composable
fun SettingsAdaptiveGrid(
    modifier: Modifier = Modifier,
    minimumItemWidth: Dp = 320.dp,
    spacing: Dp = 16.dp,
    content: @Composable (itemWidth: Dp) -> Unit,
) {
    require(minimumItemWidth > 0.dp && spacing >= 0.dp)
    val readableWidth = minimumItemWidth * LocalDensity.current.fontScale.coerceAtLeast(1f)
    BoxWithConstraints(modifier.fillMaxWidth()) {
        val columns = if (maxWidth >= readableWidth * 2 + spacing) 2 else 1
        val itemWidth = (maxWidth - spacing * (columns - 1)) / columns
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalArrangement = Arrangement.spacedBy(spacing),
            maxItemsInEachRow = columns,
        ) {
            content(itemWidth)
        }
    }
}
