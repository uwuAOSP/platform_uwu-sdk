/*
 * SPDX-FileCopyrightText: The uwuAOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package org.uwuaosp.compose.widget.preference

import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.ListItemShapes
import androidx.compose.runtime.Composable

@Composable
internal fun preferenceShapes(position: PreferenceGroupPosition): ListItemShapes =
    when (position) {
        PreferenceGroupPosition.Single -> ListItemDefaults.segmentedShapes(0, 1)
        PreferenceGroupPosition.Top -> ListItemDefaults.segmentedShapes(0, 3)
        PreferenceGroupPosition.Middle -> ListItemDefaults.segmentedShapes(1, 3)
        PreferenceGroupPosition.Bottom -> ListItemDefaults.segmentedShapes(2, 3)
    }
