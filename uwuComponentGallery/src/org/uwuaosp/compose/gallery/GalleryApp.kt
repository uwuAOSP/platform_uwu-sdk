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

package org.uwuaosp.compose.gallery

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.uwuaosp.compose.gallery.pages.AppListPage
import org.uwuaosp.compose.gallery.pages.CategoryPage
import org.uwuaosp.compose.gallery.pages.DialogPage
import org.uwuaosp.compose.gallery.pages.FooterPage
import org.uwuaosp.compose.gallery.pages.HomePage
import org.uwuaosp.compose.gallery.pages.IconPage
import org.uwuaosp.compose.gallery.pages.IllustrationPage
import org.uwuaosp.compose.gallery.pages.PreferencePage
import org.uwuaosp.compose.gallery.pages.ScaffoldPage
import org.uwuaosp.compose.gallery.pages.SwitchPage
import org.uwuaosp.compose.gallery.pages.TopIntroPage
import org.uwuaosp.compose.gallery.pages.TypographyPage

private const val TRANSITION_DURATION = 300

sealed class GalleryScreen {
    data object Home : GalleryScreen()
    data object Scaffold : GalleryScreen()
    data object Preference : GalleryScreen()
    data object Switch : GalleryScreen()
    data object AppList : GalleryScreen()
    data object Dialog : GalleryScreen()
    data object Footer : GalleryScreen()
    data object Category : GalleryScreen()
    data object TopIntro : GalleryScreen()
    data object Typography : GalleryScreen()
    data object Icons : GalleryScreen()
    data object Illustration : GalleryScreen()
}

@Composable
fun GalleryApp() {
    var currentScreen by remember { mutableStateOf<GalleryScreen>(GalleryScreen.Home) }

    BackHandler(enabled = currentScreen != GalleryScreen.Home) {
        currentScreen = GalleryScreen.Home
    }

    AnimatedContent(
        targetState = currentScreen,
        transitionSpec = {
            val isGoingBack = targetState == GalleryScreen.Home
            slideInHorizontally(tween(TRANSITION_DURATION), initialOffsetX = { if (isGoingBack) -it else it }) +
                fadeIn(tween(TRANSITION_DURATION)) togetherWith
                slideOutHorizontally(tween(TRANSITION_DURATION), targetOffsetX = { if (isGoingBack) it else -it }) +
                fadeOut(tween(TRANSITION_DURATION))
        },
        label = "screen_transition",
    ) { screen ->
        when (screen) {
            GalleryScreen.Home -> HomePage(
                onNavigate = { currentScreen = it },
            )
            GalleryScreen.Scaffold -> ScaffoldPage(
                onBack = { currentScreen = GalleryScreen.Home },
            )
            GalleryScreen.Preference -> PreferencePage(
                onBack = { currentScreen = GalleryScreen.Home },
            )
            GalleryScreen.Switch -> SwitchPage(
                onBack = { currentScreen = GalleryScreen.Home },
            )
            GalleryScreen.AppList -> AppListPage(
                onBack = { currentScreen = GalleryScreen.Home },
            )
            GalleryScreen.Dialog -> DialogPage(
                onBack = { currentScreen = GalleryScreen.Home },
            )
            GalleryScreen.Footer -> FooterPage(
                onBack = { currentScreen = GalleryScreen.Home },
            )
            GalleryScreen.Category -> CategoryPage(
                onBack = { currentScreen = GalleryScreen.Home },
            )
            GalleryScreen.TopIntro -> TopIntroPage(
                onBack = { currentScreen = GalleryScreen.Home },
            )
            GalleryScreen.Typography -> TypographyPage(
                onBack = { currentScreen = GalleryScreen.Home },
            )
            GalleryScreen.Icons -> IconPage(
                onBack = { currentScreen = GalleryScreen.Home },
            )
            GalleryScreen.Illustration -> IllustrationPage(
                onBack = { currentScreen = GalleryScreen.Home },
            )
        }
    }
}
