/*
 * SPDX-FileCopyrightText: UwUniverse
 * SPDX-License-Identifier: Apache-2.0
 */

package org.uwuaosp.compose.wizard

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun WizardBrandPage(
    title: String,
    illustration: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    animateContent: Boolean = false,
    bottomContentPadding: Dp = 0.dp,
    assistContent: (@Composable () -> Unit)? = null,
    actionButton: BottomAppBarButton? = null,
    dismissButton: BottomAppBarButton? = null,
) {
    var visible by remember { mutableStateOf(!animateContent) }
    LaunchedEffect(animateContent) { visible = true }
    val contentAlpha by
        animateFloatAsState(
            targetValue = if (visible) 1f else 0f,
            animationSpec = MaterialTheme.motionScheme.defaultEffectsSpec(),
            label = "wizardBrandAlpha",
        )
    val contentScale by
        animateFloatAsState(
            targetValue = if (visible) 1f else 0.96f,
            animationSpec = MaterialTheme.motionScheme.defaultSpatialSpec(),
            label = "wizardBrandScale",
        )
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        bottomBar = { WizardBottomBar(actionButton, dismissButton) },
    ) { innerPadding ->
        BoxWithConstraints(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            val columns =
                if (maxWidth >= 840.dp * LocalDensity.current.fontScale.coerceAtLeast(1f)) 2 else 1
            val gap = BrandPageHorizontalPadding
            val itemWidth = (maxWidth - gap * (columns - 1)) / columns
            val illustrationHeight =
                (maxHeight * if (columns == 2) 0.8f else 0.45f).coerceAtLeast(120.dp)
            FlowRow(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                maxItemsInEachRow = columns,
                horizontalArrangement = Arrangement.spacedBy(gap),
            ) {
                Box(
                    Modifier.width(itemWidth)
                        .height(illustrationHeight)
                        .padding(
                            horizontal = BrandPageHorizontalPadding,
                            vertical = BrandPageVerticalPadding,
                        )
                        .graphicsLayer {
                            alpha = contentAlpha
                            scaleX = contentScale
                            scaleY = contentScale
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    illustration()
                }
                Column(Modifier.width(itemWidth).padding(horizontal = BrandPageHorizontalPadding)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.displayLargeEmphasized,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier =
                            Modifier.padding(bottom = if (subtitle == null) 30.dp else 10.dp)
                                .graphicsLayer { alpha = contentAlpha },
                    )
                    if (subtitle != null) {
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier =
                                Modifier.padding(bottom = 30.dp).graphicsLayer {
                                    alpha = contentAlpha
                                },
                        )
                    }
                    Box(
                        Modifier.fillMaxWidth().heightIn(min = 56.dp),
                        contentAlignment = Alignment.TopCenter,
                    ) {
                        assistContent?.invoke()
                    }
                    Spacer(Modifier.height(bottomContentPadding))
                }
            }
        }
    }
}

private val BrandPageHorizontalPadding = 28.dp
private val BrandPageVerticalPadding = 24.dp
