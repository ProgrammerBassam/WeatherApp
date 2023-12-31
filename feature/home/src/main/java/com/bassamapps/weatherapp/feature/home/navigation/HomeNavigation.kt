/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.bassamapps.weatherapp.feature.home.HomeRoute

const val homeNavigationRoute = "home/"

/**
 * Navigate to home
 *
 * @param navOptions
 */
fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeNavigationRoute, navOptions)
}

/**
 * Home screen
 *
 */
fun NavGraphBuilder.homeScreen() {
    composable(
        route = homeNavigationRoute,
    ) {
        HomeRoute()
    }
}