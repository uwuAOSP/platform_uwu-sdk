/*
 * SPDX-FileCopyrightText: UwUniverse
 * SPDX-License-Identifier: Apache-2.0
 */

@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package org.uwuaosp.compose.gallery

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.uwuaosp.compose.gallery.pages.AppListPage
import org.uwuaosp.compose.gallery.pages.CategoryPage
import org.uwuaosp.compose.gallery.pages.DialogPage
import org.uwuaosp.compose.gallery.pages.FooterPage
import org.uwuaosp.compose.gallery.pages.GlifPage
import org.uwuaosp.compose.gallery.pages.HomePage
import org.uwuaosp.compose.gallery.pages.IconPage
import org.uwuaosp.compose.gallery.pages.IllustrationPage
import org.uwuaosp.compose.gallery.pages.PreferencePage
import org.uwuaosp.compose.gallery.pages.ScaffoldPage
import org.uwuaosp.compose.gallery.pages.SearchPage
import org.uwuaosp.compose.gallery.pages.SwitchPage
import org.uwuaosp.compose.gallery.pages.TopIntroPage
import org.uwuaosp.compose.gallery.pages.TypographyPage

enum class GalleryScreen {
    Home,
    Scaffold,
    Preference,
    Switch,
    AppList,
    Dialog,
    Glif,
    Footer,
    Category,
    TopIntro,
    Typography,
    Icons,
    Illustration,
    Search,
    Expressive,
}

@Composable
fun GalleryApp() {
    var currentScreen by rememberSaveable { mutableStateOf(GalleryScreen.Home) }

    BackHandler(enabled = currentScreen != GalleryScreen.Home) {
        currentScreen = GalleryScreen.Home
    }

    val stateHolder = rememberSaveableStateHolder()
    val motion = MaterialTheme.motionScheme
    AnimatedContent(
        targetState = currentScreen,
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surfaceContainer),
        transitionSpec = {
            val isGoingBack = targetState == GalleryScreen.Home
            slideInHorizontally(
                motion.defaultSpatialSpec(),
                initialOffsetX = { if (isGoingBack) -it else it },
            ) + fadeIn(motion.defaultEffectsSpec()) togetherWith
                slideOutHorizontally(
                    motion.defaultSpatialSpec(),
                    targetOffsetX = { if (isGoingBack) it else -it },
                ) + fadeOut(motion.defaultEffectsSpec())
        },
        label = "screen_transition",
    ) { screen ->
        stateHolder.SaveableStateProvider(screen.name) {
            when (screen) {
                GalleryScreen.Expressive ->
                    org.uwuaosp.compose.gallery.pages.ExpressivePage(
                        onBack = { currentScreen = GalleryScreen.Home }
                    )
                GalleryScreen.Home -> HomePage(onNavigate = { currentScreen = it })
                GalleryScreen.Scaffold ->
                    ScaffoldPage(onBack = { currentScreen = GalleryScreen.Home })
                GalleryScreen.Preference ->
                    PreferencePage(onBack = { currentScreen = GalleryScreen.Home })
                GalleryScreen.Switch -> SwitchPage(onBack = { currentScreen = GalleryScreen.Home })
                GalleryScreen.AppList ->
                    AppListPage(onBack = { currentScreen = GalleryScreen.Home })
                GalleryScreen.Dialog -> DialogPage(onBack = { currentScreen = GalleryScreen.Home })
                GalleryScreen.Glif -> GlifPage(onBack = { currentScreen = GalleryScreen.Home })
                GalleryScreen.Footer -> FooterPage(onBack = { currentScreen = GalleryScreen.Home })
                GalleryScreen.Category ->
                    CategoryPage(onBack = { currentScreen = GalleryScreen.Home })
                GalleryScreen.TopIntro ->
                    TopIntroPage(onBack = { currentScreen = GalleryScreen.Home })
                GalleryScreen.Typography ->
                    TypographyPage(onBack = { currentScreen = GalleryScreen.Home })
                GalleryScreen.Icons -> IconPage(onBack = { currentScreen = GalleryScreen.Home })
                GalleryScreen.Illustration ->
                    IllustrationPage(onBack = { currentScreen = GalleryScreen.Home })
                GalleryScreen.Search -> SearchPage(onBack = { currentScreen = GalleryScreen.Home })
            }
        }
    }
}
