/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/18/23, 11:09 PM
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

fun NavController.navigateToSearchResult(navOptions: NavOptions? = null) {
    this.navigate(searchResultNavigationRoute, navOptions)
}

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