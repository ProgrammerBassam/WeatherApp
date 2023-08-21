/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/19/23, 10:16 PM
 *
 */

package com.bassamapps.weatherapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarDuration.Short
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult.ActionPerformed
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.bassamapps.weatherapp.R
import com.bassamapps.weatherapp.core.data.util.NetworkMonitor
import com.bassamapps.weatherapp.core.data.util.location.GpsMonitor
import com.bassamapps.weatherapp.core.designsystem.component.WeBackground
import com.bassamapps.weatherapp.core.designsystem.component.WeGradientBackground
import com.bassamapps.weatherapp.core.designsystem.component.WeTopAppBar
import com.bassamapps.weatherapp.core.designsystem.icon.WeIcons
import com.bassamapps.weatherapp.core.designsystem.theme.GradientColors
import com.bassamapps.weatherapp.core.designsystem.theme.LocalGradientColors
import com.bassamapps.weatherapp.feature.settings.SettingsDialog
import com.bassamapps.weatherapp.navigation.WeNavHost
import com.bassamapps.weatherapp.feature.settings.R as settingsR

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class,
    ExperimentalComposeUiApi::class,
)
@Composable
fun WeApp(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    gpsMonitor: GpsMonitor,
    appState: WeAppState = rememberWeAppState(
        networkMonitor = networkMonitor,
        gpsMonitor = gpsMonitor,
        windowSizeClass = windowSizeClass,
    ),
) {
    val shouldShowGradientBackground = false

    var showSettingsDialog by rememberSaveable {
        mutableStateOf(false)
    }

    WeBackground {
        WeGradientBackground(
            gradientColors = if (shouldShowGradientBackground) {
                LocalGradientColors.current
            } else {
                GradientColors()
            },
        ) {
            val snackbarHostState = remember { SnackbarHostState() }

            if (showSettingsDialog) {
                SettingsDialog(
                    onDismiss = { showSettingsDialog = false },
                )
            }

            Scaffold(
                modifier = Modifier.semantics {
                    testTagsAsResourceId = true
                },
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                snackbarHost = { SnackbarHost(snackbarHostState) },
            ) { padding ->
                Row(
                    Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .consumeWindowInsets(padding)
                        .windowInsetsPadding(
                            WindowInsets.safeDrawing.only(
                                WindowInsetsSides.Horizontal,
                            ),
                        ),
                ) {
                    Column(Modifier.fillMaxSize()) {
                        // Show the top app bar on top level destinations.
                        val destination = appState.currentTopLevelDestination
                        if (destination != null) {
                            WeTopAppBar(
                                titleRes = R.string.app_name,
                                navigationIcon = WeIcons.Search,
                                navigationIconContentDescription = stringResource(
                                    id = settingsR.string.top_app_bar_navigation_icon_description,
                                ),
                                actionIcon = WeIcons.Settings,
                                actionIconContentDescription = stringResource(
                                    id = settingsR.string.top_app_bar_action_icon_description,
                                ),
                                colors = TopAppBarDefaults.mediumTopAppBarColors(
                                    containerColor = Color.Transparent,
                                ),
                                onActionClick = {
                                    showSettingsDialog = true
                                },
                                onNavigationClick = { appState.navigateToSearch() },
                            )
                        }

                        WeNavHost(appState = appState, onShowSnackbar = { message, action ->
                            snackbarHostState.showSnackbar(
                                message = message,
                                actionLabel = action,
                                duration = Short,
                            ) == ActionPerformed
                        })
                    }
                }
            }
        }
    }
}

/*private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false*/
