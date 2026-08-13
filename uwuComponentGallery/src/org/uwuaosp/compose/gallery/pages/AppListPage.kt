/*
 * Copyright (C) 2026 UwUniverse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.uwuaosp.compose.gallery.pages

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import org.uwuaosp.compose.gallery.R
import org.uwuaosp.compose.settingslib.AppListItem
import org.uwuaosp.compose.settingslib.AppListScaffold
import org.uwuaosp.compose.settingslib.preferencePosition

private data class SampleApp(
    val label: String,
    val packageName: String,
)

private class AppIconGenerator {
    private val colors = listOf(
        android.graphics.Color.parseColor("#4285F4"),
        android.graphics.Color.parseColor("#34A853"),
        android.graphics.Color.parseColor("#FBBC05"),
        android.graphics.Color.parseColor("#EA4335"),
        android.graphics.Color.parseColor("#46BDC6"),
        android.graphics.Color.parseColor("#7B1FA2"),
    )
    private val paint = Paint().apply {
        isAntiAlias = true
        textSize = 48f
        textAlign = Paint.Align.CENTER
    }
    private var index = 0

    fun generate(label: String): ImageBitmap {
        val bitmap = Bitmap.createBitmap(80, 80, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val color = colors[index % colors.size]
        paint.color = color
        canvas.drawRoundRect(0f, 0f, 80f, 80f, 16f, 16f, paint)
        paint.color = android.graphics.Color.WHITE
        val initial = label.firstOrNull()?.uppercase() ?: "?"
        canvas.drawText(initial, 40f, 56f, paint)
        index++
        return bitmap.asImageBitmap()
    }
}

@Composable
fun AppListPage(onBack: () -> Unit) {
    val context = LocalContext.current
    val sampleApps = remember {
        val labels = context.resources.getStringArray(R.array.sample_app_labels)
        val packages = context.resources.getStringArray(R.array.sample_app_packages)
        labels.zip(packages) { label, pkg -> SampleApp(label, pkg) }
    }

    var searchQuery by remember { mutableStateOf("") }
    val iconGenerator = remember { AppIconGenerator() }

    val filteredApps = remember(searchQuery) {
        if (searchQuery.isEmpty()) {
            sampleApps
        } else {
            sampleApps.filter {
                it.label.contains(searchQuery, ignoreCase = true) ||
                    it.packageName.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    AppListScaffold(
        title = stringResource(R.string.applist_title),
        searchQuery = searchQuery,
        searchPlaceholder = stringResource(R.string.applist_search_placeholder),
        clearSearchContentDescription = stringResource(R.string.applist_clear_search),
        onSearchQueryChange = { searchQuery = it },
        onNavigateUp = onBack,
        content = {
            items(filteredApps.size) { index ->
                val app = filteredApps[index]
                AppListItem(
                    label = app.label,
                    packageName = app.packageName,
                    icon = iconGenerator.generate(app.label),
                    position = preferencePosition(index, filteredApps.lastIndex),
                    onClick = {},
                )
            }
        },
    )
}
