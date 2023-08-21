/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/19/23, 9:59 PM
 *
 */

package com.bassamapps.weatherapp.core.model.data

/**
 * Class summarizing user interest data
 */
data class UserData(
    val themeBrand: ThemeBrand = ThemeBrand.DEFAULT,
    val darkThemeConfig: DarkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
    val useDynamicColor: Boolean = false,
    val useGps: Boolean = true,
    val isTempC: Boolean = true,
    val weatherCityName: String = "",
    val isFirstTime: Boolean = false
)