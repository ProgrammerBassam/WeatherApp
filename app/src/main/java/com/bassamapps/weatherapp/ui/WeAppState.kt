/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.bassamapps.weatherapp.core.data.util.NetworkMonitor
import com.bassamapps.weatherapp.core.data.util.location.GpsMonitor
import com.bassamapps.weatherapp.core.ui.TrackDisposableJank
import com.bassamapps.weatherapp.feature.home.navigation.homeNavigationRoute
import com.bassamapps.weatherapp.feature.home.navigation.navigateToHome
import com.bassamapps.weatherapp.feature.search.navigation.navigateToSearch
import com.bassamapps.weatherapp.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import androidx.tracing.trace
import com.bassamapps.weatherapp.feature.searchresult.navigation.navigateToSearchResult

/**
 * Remember we app state
 *
 * @param windowSizeClass
 * @param networkMonitor
 * @param gpsMonitor
 * @param coroutineScope
 * @param navController
 * @return
 */
@Composable
fun rememberWeAppState(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    gpsMonitor: GpsMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): WeAppState {
    NavigationTrackingSideEffect(navController)
    return remember(
        navController,
        coroutineScope,
        windowSizeClass,
        networkMonitor,
        gpsMonitor
    ) {
        WeAppState(
            navController,
            coroutineScope,
            windowSizeClass,
            networkMonitor,
            gpsMonitor
        )
    }
}

/**
 * We app state
 *
 * @property navController
 * @property coroutineScope
 * @property windowSizeClass
 * @constructor
 *
 * @param networkMonitor
 * @param gpsMonitor
 */
@Stable
class WeAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    gpsMonitor: GpsMonitor,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            homeNavigationRoute -> TopLevelDestination.HOME
            else -> null
        }

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )


    /**
     * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
     * route.
     */
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    /**
     * Navigate to top level destination
     *
     * @param topLevelDestination
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

            when (topLevelDestination) {
                TopLevelDestination.HOME -> navController.navigateToHome(topLevelNavOptions)
                TopLevelDestination.SEARCH_RESULT -> navController.navigateToSearchResult(topLevelNavOptions)
            }
        }
    }


    /**
     * Navigate to search
     *
     */
    fun navigateToSearch() {
        navController.navigateToSearch()
    }
}

/**
 * Stores information about navigation events to be used with JankStats
 */
@Composable
private fun NavigationTrackingSideEffect(navController: NavHostController) {
    TrackDisposableJank(navController) { metricsHolder ->
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            metricsHolder.state?.putState("Navigation", destination.route.toString())
        }

        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
}
