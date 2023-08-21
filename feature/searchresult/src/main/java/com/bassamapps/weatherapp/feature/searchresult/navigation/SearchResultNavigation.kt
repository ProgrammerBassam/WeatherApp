/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.feature.searchresult.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bassamapps.weatherapp.feature.searchresult.SearchResultRoute

const val searchResultNavigationRoute = "search_result/{cityName}"

/**
 * Navigate to search result
 *
 * @param navOptions
 */
fun NavController.navigateToSearchResult(navOptions: NavOptions? = null) {
    this.navigate(searchResultNavigationRoute, navOptions)
}

/**
 * Search result screen
 *
 * @param onBackClick
 * @receiver
 */
fun NavGraphBuilder.searchResultScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = searchResultNavigationRoute,
        arguments = listOf(navArgument("cityName") { type = NavType.StringType })
    ) {
        SearchResultRoute(
            onBackClick = onBackClick,
        )
    }
}