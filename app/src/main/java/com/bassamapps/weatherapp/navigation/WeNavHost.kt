/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/18/23, 11:12 PM
 *
 */

package com.bassamapps.weatherapp.navigation

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.bassamapps.weatherapp.feature.home.navigation.homeNavigationRoute
import com.bassamapps.weatherapp.ui.WeAppState
import com.bassamapps.weatherapp.feature.home.navigation.homeScreen
import com.bassamapps.weatherapp.feature.search.navigation.searchScreen
import com.bassamapps.weatherapp.feature.searchresult.navigation.searchResultScreen

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
    onShowSnackbar: suspend (String, String?) -> Boolean,
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
