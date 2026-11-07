/*
 * SPDX-FileCopyrightText: The uwuAOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)

package org.uwuaosp.compose.gallery.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.carousel.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import java.text.DateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlinx.coroutines.launch
import org.uwuaosp.compose.gallery.R
import org.uwuaosp.compose.widget.editor.SettingsSearchLayout
import org.uwuaosp.compose.widget.expressive.SettingsActionButton
import org.uwuaosp.compose.widget.menu.SettingsDropdownMenu
import org.uwuaosp.compose.widget.menu.SettingsMenuGroup
import org.uwuaosp.compose.widget.menu.SettingsMenuItem
import org.uwuaosp.compose.widget.scaffold.SettingsScaffold
import org.uwuaosp.compose.widget.ui.SettingsAdaptiveGrid

private val LocalCatalogPanelWidth = compositionLocalOf { androidx.compose.ui.unit.Dp.Unspecified }

enum class CatalogSection {
    Inputs,
    Surfaces,
    Navigation,
}

@Composable
fun CatalogPage(section: CatalogSection, onBack: () -> Unit) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val snackbar = remember { SnackbarHostState() }
    var destination by rememberSaveable { mutableIntStateOf(0) }
    val destinations =
        listOf(
            stringResource(R.string.catalog_home),
            stringResource(R.string.catalog_search),
            stringResource(R.string.catalog_favorites),
        )
    val title =
        stringResource(
            when (section) {
                CatalogSection.Inputs -> R.string.catalog_inputs
                CatalogSection.Surfaces -> R.string.catalog_surfaces
                CatalogSection.Navigation -> R.string.catalog_navigation
            }
        )
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = section == CatalogSection.Navigation,
        drawerContent = {
            ModalDrawerSheet {
                Text(title, Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
                destinations.forEachIndexed { index, label ->
                    NavigationDrawerItem(
                        label = { Text(label) },
                        selected = destination == index,
                        onClick = {
                            destination = index
                            scope.launch { drawerState.close() }
                        },
                    )
                }
            }
        },
    ) {
        Box(Modifier.fillMaxSize()) {
            SettingsScaffold(
                title,
                showBackButton = true,
                onNavigateUp = onBack,
                startCollapsed = false,
            ) {
                SettingsAdaptiveGrid { panelWidth ->
                    CompositionLocalProvider(LocalCatalogPanelWidth provides panelWidth) {
                        when (section) {
                            CatalogSection.Inputs -> CatalogInputs()
                            CatalogSection.Surfaces -> CatalogSurfaces(snackbar)
                            CatalogSection.Navigation ->
                                CatalogNavigation { scope.launch { drawerState.open() } }
                        }
                    }
                }
                Spacer(Modifier.height(24.dp))
            }
            SnackbarHost(snackbar, Modifier.align(Alignment.BottomCenter).navigationBarsPadding())
        }
    }
}

@Composable
private fun CatalogPanel(title: String, content: @Composable ColumnScope.() -> Unit) {
    Card(Modifier.width(LocalCatalogPanelWidth.current).padding(vertical = 8.dp)) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            content()
        }
    }
}

@Composable
private fun CatalogInputs() {
    val verticalSlider = rememberSliderState(value = 0.5f)
    var text by rememberSaveable { mutableStateOf("") }
    var selected by rememberSaveable { mutableIntStateOf(0) }
    var expanded by rememberSaveable { mutableStateOf(false) }
    var checked by rememberSaveable { mutableStateOf(false) }
    var choice by rememberSaveable { mutableIntStateOf(0) }
    var rangeStart by rememberSaveable { mutableFloatStateOf(0.25f) }
    var rangeEnd by rememberSaveable { mutableFloatStateOf(0.75f) }
    var dateOpen by rememberSaveable { mutableStateOf(false) }
    var timeOpen by rememberSaveable { mutableStateOf(false) }
    var timeInput by rememberSaveable { mutableStateOf(false) }
    var savedDate by rememberSaveable { mutableStateOf<Long?>(null) }
    var savedTime by rememberSaveable { mutableStateOf("") }
    var savedHour by rememberSaveable { mutableIntStateOf(-1) }
    var savedMinute by rememberSaveable { mutableIntStateOf(0) }
    val dateState = rememberDatePickerState()
    val timeState = rememberTimePickerState()
    val options =
        listOf(
            stringResource(R.string.expressive_day),
            stringResource(R.string.expressive_week),
            stringResource(R.string.expressive_month),
        )
    val confirm = stringResource(R.string.catalog_confirm)
    val cancel = stringResource(R.string.catalog_cancel)
    val searchQuery = rememberTextFieldState()
    val searchState = rememberContainedSearchBarState()
    val searchScope = rememberCoroutineScope()
    CatalogPanel(stringResource(R.string.catalog_search)) {
        SettingsSearchLayout(
            textFieldState = searchQuery,
            placeholder = stringResource(R.string.catalog_search),
            clearContentDescription = stringResource(R.string.search_demo_clear),
            searchBarState = searchState,
            windowInsets = WindowInsets(0, 0, 0, 0),
        ) {
            val results = options.filter { it.contains(searchQuery.text, ignoreCase = true) }
            results.forEachIndexed { index, label ->
                SegmentedListItem(
                    onClick = {
                        searchQuery.setTextAndPlaceCursorAtEnd(label)
                        searchScope.launch { searchState.animateToCollapsed() }
                    },
                    shapes = ListItemDefaults.segmentedShapes(index, results.size),
                ) {
                    Text(label)
                }
            }
        }
    }
    CatalogPanel(stringResource(R.string.catalog_fields)) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text(stringResource(R.string.catalog_label)) },
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text(stringResource(R.string.catalog_label)) },
            modifier = Modifier.fillMaxWidth(),
        )
        ExposedDropdownMenuBox(expanded, { expanded = it }) {
            TextField(
                value = options[selected],
                onValueChange = {},
                readOnly = true,
                label = { Text(stringResource(R.string.catalog_select)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                modifier =
                    Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                        .fillMaxWidth(),
            )
            SettingsDropdownMenu(expanded, { expanded = false }) {
                SettingsMenuGroup {
                    options.forEachIndexed { index, label ->
                        SettingsMenuItem(
                            label,
                            selected == index,
                            {
                                selected = index
                                expanded = false
                            },
                            index,
                            options.size,
                        )
                    }
                }
            }
        }
    }
    CatalogPanel(stringResource(R.string.catalog_chips)) {
        var filter by rememberSaveable { mutableStateOf(false) }
        var input by rememberSaveable { mutableStateOf(true) }
        var message by rememberSaveable { mutableStateOf("") }
        val action = stringResource(R.string.catalog_action)
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AssistChip(onClick = { message = action }, label = { Text(action) })
            SuggestionChip(
                onClick = { text = action },
                label = { Text(stringResource(R.string.catalog_suggestion)) },
            )
            FilterChip(
                selected = filter,
                onClick = { filter = !filter },
                label = { Text(stringResource(R.string.catalog_filter)) },
            )
            InputChip(
                selected = input,
                onClick = { input = !input },
                label = { Text(stringResource(R.string.catalog_input_chip)) },
            )
        }
        if (message.isNotEmpty()) Text(message)
    }
    CatalogPanel(stringResource(R.string.catalog_selection)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked, { checked = it })
            Text(stringResource(R.string.expressive_checkbox))
        }
        Column(Modifier.selectableGroup()) {
            options.forEachIndexed { index, label ->
                Row(
                    Modifier.fillMaxWidth()
                        .selectable(
                            choice == index,
                            onClick = { choice = index },
                            role = Role.RadioButton,
                        )
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RadioButton(selected = choice == index, onClick = null)
                    Spacer(Modifier.width(8.dp))
                    Text(label)
                }
            }
        }
        VerticalSlider(state = verticalSlider, modifier = Modifier.height(160.dp))
        RangeSlider(
            value = rangeStart..rangeEnd,
            onValueChange = {
                rangeStart = it.start
                rangeEnd = it.endInclusive
            },
        )
    }
    CatalogPanel(stringResource(R.string.catalog_pickers)) {
        SettingsActionButton(
            stringResource(R.string.catalog_date),
            {
                dateState.selectedDateMillis = savedDate
                dateOpen = true
            },
        )
        savedDate?.let { millis ->
            val format =
                DateFormat.getDateInstance().apply { timeZone = TimeZone.getTimeZone("UTC") }
            Text(format.format(Date(millis)))
        }
        SettingsActionButton(
            stringResource(R.string.catalog_time),
            {
                if (savedHour >= 0) {
                    timeState.hour = savedHour
                    timeState.minute = savedMinute
                }
                timeOpen = true
            },
        )
        if (savedTime.isNotEmpty()) Text(savedTime)
    }
    if (dateOpen) {
        DatePickerDialog(
            onDismissRequest = { dateOpen = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        savedDate = dateState.selectedDateMillis
                        dateOpen = false
                    },
                    enabled = dateState.selectedDateMillis != null,
                    shapes = ButtonDefaults.shapes(),
                ) {
                    Text(confirm)
                }
            },
            dismissButton = {
                TextButton(onClick = { dateOpen = false }, shapes = ButtonDefaults.shapes()) {
                    Text(cancel)
                }
            },
        ) {
            DatePicker(dateState)
        }
    }
    if (timeOpen) {
        TimePickerDialog(
            title = { Text(stringResource(R.string.catalog_time)) },
            onDismissRequest = { timeOpen = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        savedHour = timeState.hour
                        savedMinute = timeState.minute
                        savedTime =
                            String.format(
                                Locale.getDefault(),
                                "%02d:%02d",
                                timeState.hour,
                                timeState.minute,
                            )
                        timeOpen = false
                    },
                    shapes = ButtonDefaults.shapes(),
                ) {
                    Text(confirm)
                }
            },
            dismissButton = {
                TextButton(onClick = { timeOpen = false }, shapes = ButtonDefaults.shapes()) {
                    Text(cancel)
                }
            },
            modeToggleButton = {
                IconButton(
                    onClick = { timeInput = !timeInput },
                    shapes = IconButtonDefaults.shapes(),
                ) {
                    Icon(
                        if (timeInput) Icons.Filled.Schedule else Icons.Filled.Keyboard,
                        stringResource(R.string.catalog_time_mode),
                    )
                }
            },
        ) {
            if (timeInput) TimeInput(timeState) else TimePicker(timeState)
        }
    }
}

@Composable
private fun CatalogSurfaces(snackbar: SnackbarHostState) {
    var showSheet by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var cardSelected by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val message = stringResource(R.string.catalog_message)
    CatalogPanel(stringResource(R.string.catalog_cards)) {
        Card(onClick = { cardSelected = !cardSelected }, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.catalog_filled_card), Modifier.padding(16.dp))
        }
        ElevatedCard(
            onClick = { cardSelected = !cardSelected },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(R.string.catalog_elevated_card), Modifier.padding(16.dp))
        }
        OutlinedCard(
            onClick = { cardSelected = !cardSelected },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(R.string.catalog_outlined_card), Modifier.padding(16.dp))
        }
        Text(
            stringResource(
                if (cardSelected) R.string.catalog_selected else R.string.catalog_unselected
            )
        )
        HorizontalDivider()
        BadgedBox(badge = { Badge { Text("3") } }) {
            Icon(Icons.Filled.Notifications, stringResource(R.string.catalog_badge))
        }
    }
    CatalogPanel(stringResource(R.string.catalog_overlays)) {
        SettingsActionButton(stringResource(R.string.catalog_sheet), { showSheet = true })
        SettingsActionButton(stringResource(R.string.catalog_dialog), { showDialog = true })
        SettingsActionButton(
            stringResource(R.string.catalog_snackbar),
            { scope.launch { snackbar.showSnackbar(message) } },
        )
    }
    CatalogPanel(stringResource(R.string.catalog_carousel)) {
        val colors =
            listOf(
                MaterialTheme.colorScheme.primaryContainer,
                MaterialTheme.colorScheme.secondaryContainer,
                MaterialTheme.colorScheme.tertiaryContainer,
            )
        HorizontalMultiBrowseCarousel(
            state = rememberCarouselState { 6 },
            preferredItemWidth = 186.dp,
            modifier = Modifier.fillMaxWidth().height(160.dp),
            itemSpacing = 8.dp,
        ) { index ->
            Surface(
                Modifier.fillMaxSize().maskClip(MaterialTheme.shapes.extraLarge),
                color = colors[index % colors.size],
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(stringResource(R.string.catalog_item, index + 1))
                }
            }
        }
        HorizontalUncontainedCarousel(
            state = rememberCarouselState { 6 },
            itemWidth = 186.dp,
            modifier = Modifier.fillMaxWidth().height(160.dp),
            itemSpacing = 8.dp,
        ) { index ->
            Surface(
                Modifier.fillMaxSize().maskClip(MaterialTheme.shapes.extraLarge),
                color = colors[index % colors.size],
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(stringResource(R.string.catalog_item, index + 1))
                }
            }
        }
        HorizontalCenteredHeroCarousel(
            state = rememberCarouselState { 6 },
            modifier = Modifier.fillMaxWidth().height(160.dp),
            itemSpacing = 8.dp,
        ) { index ->
            Surface(
                Modifier.fillMaxSize().maskClip(MaterialTheme.shapes.extraLarge),
                color = colors[index % colors.size],
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(stringResource(R.string.catalog_item, index + 1))
                }
            }
        }
    }
    if (showSheet) {
        ModalBottomSheet(sheetState = sheetState, onDismissRequest = { showSheet = false }) {
            Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(message, style = MaterialTheme.typography.titleLarge)
                SettingsActionButton(
                    stringResource(R.string.catalog_close),
                    {
                        scope.launch {
                            sheetState.hide()
                            if (!sheetState.isVisible) showSheet = false
                        }
                    },
                )
            }
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(stringResource(R.string.catalog_dialog)) },
            text = { Text(message) },
            confirmButton = {
                TextButton(onClick = { showDialog = false }, shapes = ButtonDefaults.shapes()) {
                    Text(stringResource(R.string.catalog_confirm))
                }
            },
        )
    }
}

@Composable
private fun CatalogNavigation(onOpenDrawer: () -> Unit) {
    var tab by rememberSaveable { mutableIntStateOf(0) }
    val tabs =
        listOf(
            stringResource(R.string.catalog_home),
            stringResource(R.string.catalog_search),
            stringResource(R.string.catalog_favorites),
        )
    CatalogPanel(stringResource(R.string.catalog_tabs)) {
        PrimaryTabRow(selectedTabIndex = tab) {
            tabs.forEachIndexed { index, label ->
                Tab(selected = tab == index, onClick = { tab = index }, text = { Text(label) })
            }
        }
        Text(tabs[tab], style = MaterialTheme.typography.titleLarge)
    }
    CatalogPanel(stringResource(R.string.catalog_drawer)) {
        SettingsActionButton(stringResource(R.string.catalog_drawer), onOpenDrawer)
    }
    CatalogPanel(stringResource(R.string.catalog_docked_toolbar)) {
        var actions by rememberSaveable { mutableIntStateOf(0) }
        FlexibleBottomAppBar(windowInsets = WindowInsets(0, 0, 0, 0)) {
            IconButton(onClick = { actions++ }, shapes = IconButtonDefaults.shapes()) {
                Icon(Icons.Filled.Edit, stringResource(R.string.expressive_create))
            }
            IconButton(onClick = { actions++ }, shapes = IconButtonDefaults.shapes()) {
                Icon(Icons.Filled.Share, stringResource(R.string.expressive_share))
            }
        }
        VerticalFloatingToolbar(expanded = true) {
            IconButton(onClick = { actions++ }, shapes = IconButtonDefaults.shapes()) {
                Icon(Icons.Filled.Edit, stringResource(R.string.expressive_create))
            }
            IconButton(onClick = { actions++ }, shapes = IconButtonDefaults.shapes()) {
                Icon(Icons.Filled.Share, stringResource(R.string.expressive_share))
            }
        }
        Text(stringResource(R.string.expressive_action_count, actions))
    }
}
