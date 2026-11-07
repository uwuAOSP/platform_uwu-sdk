/*
 * SPDX-FileCopyrightText: The uwuAOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package org.uwuaosp.compose.widget.preference

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.SegmentedListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import org.uwuaosp.compose.framework.theme.SettingsDimension

@Composable
fun PreferenceRow(
    title: String,
    summary: String? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector? = null,
    iconContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable RowScope.() -> Unit)? = null,
    index: Int = 0,
    itemCount: Int = 1,
    showSummary: Boolean = true,
    onClick: (() -> Unit)? = null,
) {
    PreferenceItem(
        modifier = modifier,
        enabled = enabled,
        position =
            if (itemCount > 1) preferenceGroupPosition(index, itemCount)
            else LocalPreferenceGroupPosition.current,
        onClick = onClick,
        leadingContent =
            iconContent
                ?: if (icon != null) {
                    { PreferenceIcon(icon) }
                } else null,
        supportingContent =
            if (showSummary && summary != null) {
                { PreferenceSummary(summary) }
            } else null,
        trailingContent = trailingContent?.let { { Row(content = it) } },
        content = { PreferenceTitle(title) },
    )
}

/** Uses Material slots so line count, disabled colors, and selection are owned by the list item. */
@Composable
internal fun PreferenceItem(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    position: PreferenceGroupPosition = PreferenceGroupPosition.Single,
    onClick: (() -> Unit)? = null,
    checked: Boolean? = null,
    onCheckedChange: ((Boolean) -> Unit)? = null,
    selected: Boolean? = null,
    role: Role? = null,
    leadingContent: (@Composable () -> Unit)? = null,
    supportingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val shapes = preferenceShapes(position)
    val colors = ListItemDefaults.segmentedColors()
    val semanticsModifier =
        modifier
            .fillMaxWidth()
            .then(if (role != null) Modifier.semantics { this.role = role } else Modifier)
    when {
        checked != null && onCheckedChange != null ->
            SegmentedListItem(
                checked = checked,
                onCheckedChange = onCheckedChange,
                shapes = shapes,
                enabled = enabled,
                modifier = semanticsModifier,
                leadingContent = leadingContent,
                supportingContent = supportingContent,
                trailingContent = trailingContent,
                content = content,
            )
        selected != null && onClick != null ->
            SegmentedListItem(
                selected = selected,
                onClick = onClick,
                shapes = shapes,
                enabled = enabled,
                modifier = semanticsModifier,
                leadingContent = leadingContent,
                supportingContent = supportingContent,
                trailingContent = trailingContent,
                content = content,
            )
        onClick != null ->
            SegmentedListItem(
                onClick = onClick,
                shapes = shapes,
                enabled = enabled,
                modifier = semanticsModifier,
                leadingContent = leadingContent,
                supportingContent = supportingContent,
                trailingContent = trailingContent,
                content = content,
            )
        else ->
            ListItem(
                headlineContent = content,
                modifier = semanticsModifier.clip(shapes.shape),
                leadingContent = leadingContent,
                supportingContent = supportingContent,
                trailingContent = trailingContent,
                colors =
                    if (enabled) colors
                    else
                        colors.copy(
                            containerColor = colors.disabledContainerColor,
                            contentColor = colors.disabledContentColor,
                            leadingContentColor = colors.disabledLeadingContentColor,
                            supportingContentColor = colors.disabledSupportingContentColor,
                            trailingContentColor = colors.disabledTrailingContentColor,
                        ),
            )
    }
}

@Composable
internal fun PreferenceSurface(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    position: PreferenceGroupPosition = PreferenceGroupPosition.Single,
    onClick: (() -> Unit)? = null,
    content: @Composable RowScope.() -> Unit,
) {
    PreferenceItem(modifier = modifier, enabled = enabled, position = position, onClick = onClick) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            content = content,
        )
    }
}

@Composable
internal fun PreferenceIcon(icon: ImageVector) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        modifier = Modifier.size(SettingsDimension.itemIconSize),
    )
}

@Composable
internal fun PreferenceTitle(title: String) {
    Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis)
}

@Composable
internal fun PreferenceSummary(summary: String) {
    Text(text = summary, maxLines = 3, overflow = TextOverflow.Ellipsis)
}
