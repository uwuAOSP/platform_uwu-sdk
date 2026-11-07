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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
    Menus,
    CatalogInputs,
    CatalogSurfaces,
    CatalogNavigation,
}

@Composable
fun GalleryApp() {
    var currentScreen by rememberSaveable { mutableStateOf(GalleryScreen.Home) }

    BackHandler(enabled = currentScreen != GalleryScreen.Home) {
        currentScreen = GalleryScreen.Home
    }

    val stateHolder = rememberSaveableStateHolder()
    val motion = MaterialTheme.motionScheme
    val destinations =
        listOf(
            GalleryDestination(GalleryScreen.Home, R.string.nav_gallery, Icons.Filled.Home),
            GalleryDestination(
                GalleryScreen.Expressive,
                R.string.nav_actions,
                Icons.Filled.AutoAwesome,
            ),
            GalleryDestination(GalleryScreen.CatalogInputs, R.string.nav_inputs, Icons.Filled.Tune),
            GalleryDestination(
                GalleryScreen.CatalogSurfaces,
                R.string.nav_surfaces,
                Icons.Filled.Layers,
            ),
            GalleryDestination(
                GalleryScreen.CatalogNavigation,
                R.string.nav_navigation,
                Icons.Filled.Explore,
            ),
        )
    val selectedDestination =
        destinations.firstOrNull { it.screen == currentScreen }?.screen ?: GalleryScreen.Home
    BoxWithConstraints(Modifier.fillMaxSize()) {
        val useRail = maxWidth >= 600.dp
        val expandedRail = maxWidth >= 1200.dp
        val railState = rememberWideNavigationRailState()
        LaunchedEffect(expandedRail) {
            railState.snapTo(
                if (expandedRail) WideNavigationRailValue.Expanded
                else WideNavigationRailValue.Collapsed
            )
        }
        Row(Modifier.fillMaxSize()) {
            if (useRail) {
                WideNavigationRail(state = railState, modifier = Modifier.fillMaxHeight()) {
                    Column(Modifier.fillMaxHeight().verticalScroll(rememberScrollState())) {
                        destinations.forEach { destination ->
                            WideNavigationRailItem(
                                selected = selectedDestination == destination.screen,
                                onClick = { currentScreen = destination.screen },
                                icon = { Icon(destination.icon, null) },
                                label = { Text(stringResource(destination.label)) },
                                railExpanded = expandedRail,
                            )
                        }
                    }
                }
            }
            Scaffold(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                bottomBar = {
                    if (!useRail) {
                        ShortNavigationBar {
                            destinations.forEach { destination ->
                                ShortNavigationBarItem(
                                    selected = selectedDestination == destination.screen,
                                    onClick = { currentScreen = destination.screen },
                                    icon = { Icon(destination.icon, null) },
                                    label = { Text(stringResource(destination.label)) },
                                )
                            }
                        }
                    }
                },
            ) { navigationPadding ->
                Box(
                    Modifier.fillMaxSize()
                        .padding(navigationPadding)
                        .consumeWindowInsets(navigationPadding)
                ) {
                    AnimatedContent(
                        targetState = currentScreen,
                        modifier =
                            Modifier.fillMaxSize()
                                .background(MaterialTheme.colorScheme.surfaceContainer),
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
                                GalleryScreen.CatalogInputs ->
                                    org.uwuaosp.compose.gallery.pages.CatalogPage(
                                        org.uwuaosp.compose.gallery.pages.CatalogSection.Inputs,
                                        onBack = { currentScreen = GalleryScreen.Home },
                                    )
                                GalleryScreen.CatalogSurfaces ->
                                    org.uwuaosp.compose.gallery.pages.CatalogPage(
                                        org.uwuaosp.compose.gallery.pages.CatalogSection.Surfaces,
                                        onBack = { currentScreen = GalleryScreen.Home },
                                    )
                                GalleryScreen.CatalogNavigation ->
                                    org.uwuaosp.compose.gallery.pages.CatalogPage(
                                        org.uwuaosp.compose.gallery.pages.CatalogSection.Navigation,
                                        onBack = { currentScreen = GalleryScreen.Home },
                                    )

                                GalleryScreen.Menus ->
                                    org.uwuaosp.compose.gallery.pages.MenuPage(
                                        onBack = { currentScreen = GalleryScreen.Home }
                                    )
                                GalleryScreen.Expressive ->
                                    org.uwuaosp.compose.gallery.pages.ExpressivePage(
                                        onBack = { currentScreen = GalleryScreen.Home }
                                    )
                                GalleryScreen.Home -> HomePage(onNavigate = { currentScreen = it })
                                GalleryScreen.Scaffold ->
                                    ScaffoldPage(onBack = { currentScreen = GalleryScreen.Home })
                                GalleryScreen.Preference ->
                                    PreferencePage(onBack = { currentScreen = GalleryScreen.Home })
                                GalleryScreen.Switch ->
                                    SwitchPage(onBack = { currentScreen = GalleryScreen.Home })
                                GalleryScreen.AppList ->
                                    AppListPage(onBack = { currentScreen = GalleryScreen.Home })
                                GalleryScreen.Dialog ->
                                    DialogPage(onBack = { currentScreen = GalleryScreen.Home })
                                GalleryScreen.Glif ->
                                    GlifPage(onBack = { currentScreen = GalleryScreen.Home })
                                GalleryScreen.Footer ->
                                    FooterPage(onBack = { currentScreen = GalleryScreen.Home })
                                GalleryScreen.Category ->
                                    CategoryPage(onBack = { currentScreen = GalleryScreen.Home })
                                GalleryScreen.TopIntro ->
                                    TopIntroPage(onBack = { currentScreen = GalleryScreen.Home })
                                GalleryScreen.Typography ->
                                    TypographyPage(onBack = { currentScreen = GalleryScreen.Home })
                                GalleryScreen.Icons ->
                                    IconPage(onBack = { currentScreen = GalleryScreen.Home })
                                GalleryScreen.Illustration ->
                                    IllustrationPage(
                                        onBack = { currentScreen = GalleryScreen.Home }
                                    )
                                GalleryScreen.Search ->
                                    SearchPage(onBack = { currentScreen = GalleryScreen.Home })
                            }
                        }
                    }
                }
            }
        }
    }
}

private data class GalleryDestination(
    val screen: GalleryScreen,
    val label: Int,
    val icon: ImageVector,
)
