/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/19/23, 10:24 PM
 *
 */

package com.bassamapps.weatherapp.feature.home

import com.bassamapps.weatherapp.core.model.data.WeatherData


data class HomeUiState(
    val isLoading:Boolean = false,
    val isRefreshing:Boolean = false,
    val error:String = "",
    val currentWeather: WeatherData? = null,

) {
    fun toUiState(): WeatherUiState =
        if (currentWeather == null) WeatherUiState.WeatherEmpty(isLoading = isLoading, isRefreshing = isRefreshing, error = error)
        else WeatherUiState.HasData(isLoading = isLoading, isRefreshing = isRefreshing, error = error, currentWeather = currentWeather)
}

sealed interface WeatherUiState{
    val isLoading:Boolean
    val isRefreshing:Boolean
    val error:String

    data class HasData(
        val currentWeather: WeatherData,
        override val isLoading: Boolean,
        override val isRefreshing: Boolean,
        override val error: String
    ): WeatherUiState

    data class WeatherEmpty(
        override val isLoading: Boolean,
        override val isRefreshing: Boolean,
        override val error: String
    ): WeatherUiState
}

sealed class WeatherUiAction{
    data object FetchData: WeatherUiAction()
    data object RefreshData: WeatherUiAction()
}