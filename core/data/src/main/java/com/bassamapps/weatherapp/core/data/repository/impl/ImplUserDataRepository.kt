/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.core.data.repository.impl

import com.bassamapps.weatherapp.core.model.data.DarkThemeConfig
import com.bassamapps.weatherapp.core.model.data.ThemeBrand
import com.bassamapps.weatherapp.core.model.data.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ImplUserDataRepository {

    /**
     * Stream of [UserData]
     */
    val userData: Flow<UserData>

    /**
     * Sets the desired theme brand.
     */
    suspend fun setThemeBrand(themeBrand: ThemeBrand)

    /**
     * Sets the desired dark theme config.
     */
    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)

    /**
     * Sets the preferred dynamic color config.
     */
    suspend fun setDynamicColorPreference(useDynamicColor: Boolean)

    /**
     * Sets whether the user has completed the onboarding process.
     */
    suspend fun setUseGps(newValue: Boolean)

    suspend fun setIsTempC(newValue: Boolean)

    suspend fun setIsFirstTime(newValue: Boolean)

    suspend fun setWeatherCityName(newValue: String)
}

