/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/18/23, 7:31 PM
 *
 */

package com.bassamapps.weatherapp.core.data.repository.impl

import com.bassamapps.weatherapp.core.data.model.SearchCompleteResult
import com.bassamapps.weatherapp.core.data.model.WeatherResult
import com.bassamapps.weatherapp.core.result.Result

import kotlinx.coroutines.flow.Flow

interface ImplWeatherRepository {

        suspend fun getCurrentWeather(q: String) : Flow<Result<WeatherResult>>

        suspend fun getAutoSearchComplete(q: String) : Flow<Result<List<SearchCompleteResult>>>
}