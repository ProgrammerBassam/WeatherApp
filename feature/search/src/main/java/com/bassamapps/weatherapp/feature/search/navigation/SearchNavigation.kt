/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.bassamapps.weatherapp.feature.search.SearchRoute

const val searchRoute = "search_route"

/**
 * Navigate to search
 *
 * @param navOptions
 */
fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    this.navigate(searchRoute, navOptions)
}

/**
 * Search screen
 *
 * @param onBackClick
 * @param onNavigateToSearchResult
 * @receiver
 * @receiver
 */
fun NavGraphBuilder.searchScreen(
    onBackClick: () -> Unit,
    onNavigateToSearchResult: (String) -> Unit,
) {
    // TODO: Handle back stack for each top-level destination. At the moment each top-level
    // destination may have own search screen's back stack.
    composable(route = searchRoute) {
        SearchRoute(
            onBackClick = onBackClick,
            onNavigateToSearchResult = onNavigateToSearchResult
        )
    }
}