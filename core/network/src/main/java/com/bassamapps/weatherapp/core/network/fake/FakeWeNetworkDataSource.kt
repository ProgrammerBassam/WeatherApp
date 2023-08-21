/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 12:15 AM
 *
 */

package com.bassamapps.weatherapp.core.network.fake

import JvmUnitTestFakeAssetManager
import com.bassamapps.weatherapp.core.network.Dispatcher
import com.bassamapps.weatherapp.core.network.WeDemoNetworkDataSource
import com.bassamapps.weatherapp.core.network.WeDispatchers.IO
import com.bassamapps.weatherapp.core.network.WeNetworkDataSource
import com.bassamapps.weatherapp.core.network.model.NetworkSearchComplete
import com.bassamapps.weatherapp.core.network.model.NetworkWeather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

/**
 * [WeNetworkDataSource] implementation that provides static weather to aid development
 */
class FakeWeNetworkDataSource @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: FakeAssetManager = JvmUnitTestFakeAssetManager,
) : WeDemoNetworkDataSource {


    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getWeather(q: String): NetworkWeather =
        withContext(ioDispatcher) {
            assets.open(FORECAST_WEATHER_ASSET).use(networkJson::decodeFromStream)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getAutoSearchComplete(q: String): List<NetworkSearchComplete> =
        withContext(ioDispatcher) {
            assets.open(AUTO_COMPLETE_ASSET).use(networkJson::decodeFromStream)
        }


    companion object {
        private const val AUTO_COMPLETE_ASSET = "search.json"
        private const val FORECAST_WEATHER_ASSET = "forecast.json"

    }


}