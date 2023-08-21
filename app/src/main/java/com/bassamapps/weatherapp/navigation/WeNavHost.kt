/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.bassamapps.weatherapp.feature.home.navigation.homeNavigationRoute
import com.bassamapps.weatherapp.feature.home.navigation.homeScreen
import com.bassamapps.weatherapp.feature.search.navigation.searchScreen
import com.bassamapps.weatherapp.feature.searchresult.navigation.searchResultScreen
import com.bassamapps.weatherapp.ui.WeAppState

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun WeNavHost(
    appState: WeAppState,
    modifier: Modifier = Modifier,
    startDestination: String = homeNavigationRoute,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen()

        searchScreen(
            onBackClick = navController::popBackStack,
            onNavigateToSearchResult = {
                navController.navigate("search_result/$it")
            }
        )

        searchResultScreen(
            onBackClick = navController::popBackStack,
        )
    }
}
