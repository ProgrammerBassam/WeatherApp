/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 12:22 AM
 *
 */

package com.bassamapps.weatherapp.core.data.repository

import com.bassamapps.weatherapp.core.data.repository.impl.ImplUserDataRepository
import com.bassamapps.weatherapp.core.datastore.WePreferencesDataSource
import com.bassamapps.weatherapp.core.model.data.DarkThemeConfig
import com.bassamapps.weatherapp.core.model.data.ThemeBrand
import com.bassamapps.weatherapp.core.model.data.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataRepository @Inject constructor(
    private val wePreferencesDataSource: WePreferencesDataSource,
) : ImplUserDataRepository {

    override val userData: Flow<UserData> =
        wePreferencesDataSource.userData

    override suspend fun setThemeBrand(themeBrand: ThemeBrand) {
        wePreferencesDataSource.setThemeBrand(themeBrand)
    }

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        wePreferencesDataSource.setDarkThemeConfig(darkThemeConfig)
    }

    override suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
        wePreferencesDataSource.setDynamicColorPreference(useDynamicColor)
    }

    override suspend fun setUseGps(newValue: Boolean) {
        wePreferencesDataSource.setUseGps(newValue)
    }

    override suspend fun setIsTempC(newValue: Boolean) {
        wePreferencesDataSource.setIsTempC(newValue)
    }

    override suspend fun setIsFirstTime(newValue: Boolean) {
        wePreferencesDataSource.setIsFirstTime(newValue)
    }

    override suspend fun setWeatherCityName(newValue: String) {
        wePreferencesDataSource.setWeatherCityName(newValue)
    }
}

