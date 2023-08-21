/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 9:53 PM
 *
 */

package com.bassamapps.weatherapp.feature.home

import com.bassamapps.weatherapp.core.model.data.WeatherData


/**
 * Home ui state
 *
 * @property isLoading
 * @property isRefreshing
 * @property error
 * @property currentWeather
 * @constructor Create empty Home ui state
 */
data class HomeUiState(
    val isLoading:Boolean = false,
    val isRefreshing:Boolean = false,
    val error:String = "",
    val currentWeather: WeatherData? = null,

) {
    /**
     * To ui state
     *
     * @return
     */
    fun toUiState(): WeatherUiState =
        if (currentWeather == null) WeatherUiState.WeatherEmpty(isLoading = isLoading, isRefreshing = isRefreshing, error = error)
        else WeatherUiState.HasData(isLoading = isLoading, isRefreshing = isRefreshing, error = error, currentWeather = currentWeather)
}

/**
 * Weather ui state
 *
 * @constructor Create empty Weather ui state
 */
sealed interface WeatherUiState{
    val isLoading:Boolean
    val isRefreshing:Boolean
    val error:String

    /**
     * Has data
     *
     * @property currentWeather
     * @property isLoading
     * @property isRefreshing
     * @property error
     * @constructor Create empty Has data
     */
    data class HasData(
        val currentWeather: WeatherData,
        override val isLoading: Boolean,
        override val isRefreshing: Boolean,
        override val error: String
    ): WeatherUiState

    /**
     * Weather empty
     *
     * @property isLoading
     * @property isRefreshing
     * @property error
     * @constructor Create empty Weather empty
     */
    data class WeatherEmpty(
        override val isLoading: Boolean,
        override val isRefreshing: Boolean,
        override val error: String
    ): WeatherUiState
}

/**
 * Weather ui action
 *
 * @constructor Create empty Weather ui action
 */
sealed class WeatherUiAction{
    data object FetchData: WeatherUiAction()
    data object RefreshData: WeatherUiAction()
}