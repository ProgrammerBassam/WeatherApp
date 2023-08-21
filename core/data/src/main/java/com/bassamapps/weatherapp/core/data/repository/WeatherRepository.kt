/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/19/23, 2:08 AM
 *
 */

package com.bassamapps.weatherapp.core.data.repository

import com.bassamapps.weatherapp.core.data.mapper.NetworkSearchCompleteMapper
import com.bassamapps.weatherapp.core.data.mapper.NetworkWeatherMapper
import com.bassamapps.weatherapp.core.data.model.AstroResult
import com.bassamapps.weatherapp.core.data.model.ConditionResult
import com.bassamapps.weatherapp.core.data.model.CurrentResult
import com.bassamapps.weatherapp.core.data.model.DayResult
import com.bassamapps.weatherapp.core.data.model.ForecastDayResult
import com.bassamapps.weatherapp.core.data.model.ForecastResult
import com.bassamapps.weatherapp.core.data.model.HourResult
import com.bassamapps.weatherapp.core.data.model.LocationResult
import com.bassamapps.weatherapp.core.data.model.SearchCompleteResult
import com.bassamapps.weatherapp.core.data.model.WeatherResult
import com.bassamapps.weatherapp.core.data.repository.impl.ImplWeatherRepository
import com.bassamapps.weatherapp.core.network.Dispatcher
import com.bassamapps.weatherapp.core.network.WeDispatchers
import com.bassamapps.weatherapp.core.network.WeNetworkDataSource
import com.bassamapps.weatherapp.core.network.model.NetworkSearchComplete
import com.bassamapps.weatherapp.core.network.model.NetworkWeather
import com.bassamapps.weatherapp.core.network.utils.NetworkBoundResource
import com.bassamapps.weatherapp.core.network.utils.mapFromApiResponse
import com.bassamapps.weatherapp.core.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject



class WeatherRepository @Inject constructor (
    @Dispatcher(WeDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkBoundResources: NetworkBoundResource,
    private val weatherMapper: NetworkWeatherMapper,
    private val searchCompleteMapper: NetworkSearchCompleteMapper,
    private val weNetworkDataSource: WeNetworkDataSource
) : ImplWeatherRepository {

    override suspend fun getCurrentWeather(q: String): Flow<Result<WeatherResult>>  {
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                weNetworkDataSource.getWeather(q)
            },weatherMapper
        )
    }

    override suspend fun getAutoSearchComplete(q: String): Flow<Result<List<SearchCompleteResult>>> {
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                weNetworkDataSource.getAutoSearchComplete(q)
            },searchCompleteMapper
        )
    }
      /*  flow {
            val result = weNetworkDataSource.getAutoSearchComplete(q).toSearchCompleteResult()
            emit(result)
        }.flowOn(ioDispatcher)*/

    private fun List<NetworkSearchComplete>.toSearchCompleteResult(): List<SearchCompleteResult> =
        this.map {
            SearchCompleteResult(
                id = it.id,
                name = it.name,
                region = it.region,
                country = it.country,
                lat = it.lat,
                lon = it.lon,
                url = it.url
            )
        }


    private fun NetworkWeather.toWeatherResult(): WeatherResult =
         WeatherResult(
            location = LocationResult(
                name = this.location.name,
                localtimeEpoch = this.location.localtimeEpoch,
                localtime = this.location.localtime
            ),
            current = CurrentResult(
                lastUpdatedEpoch = this.current.lastUpdatedEpoch,
                lastUpdated = this.current.lastUpdated,
                tempC = this.current.tempC,
                tempF = this.current.tempF,
                isDay = this.current.isDay,
                condition = ConditionResult(
                    text = this.current.condition.text,
                    icon = this.current.condition.icon,
                    code = this.current.condition.code,
                ),
                uv = this.current.uv,
                humidity = this.current.humidity,
                windKph = this.current.windKph

            ),
            forecast = ForecastResult(
                forecastday = this.forecast.forecastday.map {
                    ForecastDayResult(
                        date = it.date,
                        dateEpoch = it.dateEpoch,
                        day = DayResult(
                            maxTempC = it.day.maxTempC,
                            maxTempF = it.day.maxTempF,
                            minTempC = it.day.minTempC,
                            minTempF = it.day.minTempF,
                            condition = ConditionResult(
                                text = it.day.condition.text,
                                icon = it.day.condition.icon,
                                code = it.day.condition.code,
                            ),
                            avgHumidity = it.day.avgHumidity
                        ),
                        astro = AstroResult(
                            sunrise = it.astro.sunrise,
                            sunset = it.astro.sunset,
                            moonrise = it.astro.moonrise,
                            moonset = it.astro.moonset,
                            moonPhase = it.astro.moonPhase,
                            moonIllumination = it.astro.moonIllumination,
                            isMoonUp = it.astro.isMoonUp,
                            isSunUp = it.astro.isSunUp
                        ),
                        hour = it.hour.map { currentH ->
                            HourResult(
                                timeEpoch = currentH.timeEpoch,
                                time = currentH.time,
                                tempC = currentH.tempC,
                                tempF = currentH.tempF,
                                isDay = currentH.isDay,
                                condition = ConditionResult(
                                    text = currentH.condition.text,
                                    icon = currentH.condition.icon,
                                    code = currentH.condition.code,
                                ),
                            )
                        }
                    )
                }
            )
        )
}