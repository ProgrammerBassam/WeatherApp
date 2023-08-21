/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 12:13 AM
 *
 */

package com.bassamapps.weatherapp.core.network.retrofit

import com.bassamapps.weatherapp.core.network.BuildConfig
import com.bassamapps.weatherapp.core.network.R
import com.bassamapps.weatherapp.core.network.WeNetworkDataSource
import com.bassamapps.weatherapp.core.network.model.NetworkSearchComplete
import com.bassamapps.weatherapp.core.network.model.NetworkWeather
import com.bassamapps.weatherapp.core.strings.ResourcesProvider
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Retrofit API declaration for Weather App Network API
 */
private interface RetrofitWeNetworkApi {

    @GET(value = "forecast.json")
    suspend fun getWeather(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("lang") lang: String,
        @Query("forecastday") forecastday: String,
        @Query("days") days: String,
    ): Response<NetworkWeather>

    @GET(value = "search.json")
    suspend fun getAutoSearchComplete(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("lang") lang: String,
    ): Response<List<NetworkSearchComplete>>
}

private const val WE_BASE_URL = BuildConfig.BACKEND_URL


/**
 * [Retrofit] backed [WeNetworkDataSource]
 */
@Singleton
class RetrofitWeNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
    private val resourcesProvider: ResourcesProvider

) : WeNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(WE_BASE_URL)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(RetrofitWeNetworkApi::class.java)

    override suspend fun getWeather(q: String): Response<NetworkWeather> =
        networkApi.getWeather(key = BuildConfig.WEATHER_API_KEY, q = q, lang = resourcesProvider.getString(
            R.string.lang), forecastday = "hour", days = "7")

    override suspend fun getAutoSearchComplete(q: String): Response<List<NetworkSearchComplete>> =
        networkApi.getAutoSearchComplete(key = BuildConfig.WEATHER_API_KEY, q = q, lang = resourcesProvider.getString(
            R.string.lang))
}