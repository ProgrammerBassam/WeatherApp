/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 9:57 PM
 *
 */

package com.bassamapps.weatherapp.core.domain

import android.content.Context
import com.bassamapps.weatherapp.core.data.model.WeatherResult
import com.bassamapps.weatherapp.core.data.repository.WeatherRepository
import com.bassamapps.weatherapp.core.location.getCurrentLocation
import com.bassamapps.weatherapp.core.model.data.UserData
import com.bassamapps.weatherapp.core.network.Dispatcher
import com.bassamapps.weatherapp.core.network.WeDispatchers
import com.bassamapps.weatherapp.core.result.Result
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Get weather use case
 *
 * @property context
 * @property defaultDispatcher
 * @property weatherRepository
 * @constructor Create empty Get weather use case
 */
class GetWeatherUseCase @Inject constructor (
    @ApplicationContext private val context: Context,
    @Dispatcher(WeDispatchers.Default) private val defaultDispatcher: CoroutineDispatcher,
    private val weatherRepository: WeatherRepository,
){
    /**
     * Invoke
     *
     * @param isSearch
     * @param query
     * @param userData
     * @return
     */
    suspend operator fun invoke(
        isSearch: Boolean = false,
        query: String = "",
        userData: StateFlow<UserData>, ) : Flow<Result<WeatherResult>> {

        val q = if (isSearch) {
            query
        } else if (userData.value.useGps) {
            getCurrentLocation(context = context)
        } else {
            userData.value.weatherCityName
        }


        return  weatherRepository.getCurrentWeather(q)
    }
}