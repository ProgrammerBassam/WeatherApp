/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.core.datastore

import androidx.datastore.core.DataStore
import com.bassamapps.weatherapp.core.model.data.DarkThemeConfig
import com.bassamapps.weatherapp.core.model.data.ThemeBrand
import com.bassamapps.weatherapp.core.model.data.UserData
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * We preferences data source
 *
 * @property userPreferences
 * @constructor Create empty We preferences data source
 */
class WePreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>,
) {
    val userData = userPreferences.data
        .map {
            UserData(
                themeBrand = when (it.themeBrand) {
                    null,
                    ThemeBrandProto.THEME_BRAND_UNSPECIFIED,
                    ThemeBrandProto.UNRECOGNIZED,
                    ThemeBrandProto.THEME_BRAND_DEFAULT,
                    -> ThemeBrand.DEFAULT
                    ThemeBrandProto.THEME_BRAND_ANDROID -> ThemeBrand.ANDROID
                },
                darkThemeConfig = when (it.darkThemeConfig) {
                    null,
                    DarkThemeConfigProto.DARK_THEME_CONFIG_UNSPECIFIED,
                    DarkThemeConfigProto.UNRECOGNIZED,
                    DarkThemeConfigProto.DARK_THEME_CONFIG_FOLLOW_SYSTEM,
                    ->
                        DarkThemeConfig.FOLLOW_SYSTEM
                    DarkThemeConfigProto.DARK_THEME_CONFIG_LIGHT ->
                        DarkThemeConfig.LIGHT
                    DarkThemeConfigProto.DARK_THEME_CONFIG_DARK -> DarkThemeConfig.DARK
                },
                useDynamicColor = it.useDynamicColor,
                useGps = it.isTempC,
                isTempC = it.isTempC,
                weatherCityName = it.weatherCityName,
                isFirstTime = it.isFirstTime
            )
        }

    /**
     * Set use gps
     *
     * @param newValue
     */
    suspend fun setUseGps(newValue: Boolean) {
        userPreferences.updateData {
            it.copy {
                this.useGps = newValue
            }
        }
    }

    /**
     * Set is temp c
     *
     * @param newValue
     */
    suspend fun setIsTempC(newValue: Boolean) {
        userPreferences.updateData {
            it.copy {
                this.isTempC = newValue
            }
        }
    }

    /**
     * Set is first time
     *
     * @param newValue
     */
    suspend fun setIsFirstTime(newValue: Boolean) {
        userPreferences.updateData {
            it.copy {
                this.isFirstTime = newValue
            }
        }
    }

    /**
     * Set weather city name
     *
     * @param newValue
     */
    suspend fun setWeatherCityName(newValue: String) {
        userPreferences.updateData {
            it.copy {
                this.weatherCityName = newValue
            }
        }
    }


    /**
     * Set theme brand
     *
     * @param themeBrand
     */
    suspend fun setThemeBrand(themeBrand: ThemeBrand) {
        userPreferences.updateData {
            it.copy {
                this.themeBrand = when (themeBrand) {
                    ThemeBrand.DEFAULT -> ThemeBrandProto.THEME_BRAND_DEFAULT
                    ThemeBrand.ANDROID -> ThemeBrandProto.THEME_BRAND_ANDROID
                }
            }
        }
    }

    /**
     * Set dynamic color preference
     *
     * @param useDynamicColor
     */
    suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
        userPreferences.updateData {
            it.copy {
                this.useDynamicColor = useDynamicColor
            }
        }
    }

    /**
     * Set dark theme config
     *
     * @param darkThemeConfig
     */
    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        userPreferences.updateData {
            it.copy {
                this.darkThemeConfig = when (darkThemeConfig) {
                    DarkThemeConfig.FOLLOW_SYSTEM ->
                        DarkThemeConfigProto.DARK_THEME_CONFIG_FOLLOW_SYSTEM
                    DarkThemeConfig.LIGHT -> DarkThemeConfigProto.DARK_THEME_CONFIG_LIGHT
                    DarkThemeConfig.DARK -> DarkThemeConfigProto.DARK_THEME_CONFIG_DARK
                }
            }
        }
    }
}


