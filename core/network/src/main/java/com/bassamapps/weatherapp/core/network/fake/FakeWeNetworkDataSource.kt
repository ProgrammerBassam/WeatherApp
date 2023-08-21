/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 7:27 PM
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
import kotlinx.serialization.json.Json
import javax.inject.Inject

/**
 * Fake we network data source
 *
 * @property ioDispatcher
 * @property networkJson
 * @property assets
 * @constructor Create empty Fake we network data source
 */
class FakeWeNetworkDataSource @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: FakeAssetManager = JvmUnitTestFakeAssetManager,
) : WeDemoNetworkDataSource {


   /* @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getWeather(q: String): NetworkWeather =
        withContext(ioDispatcher) {
            assets.open(FORECAST_WEATHER_ASSET).use(networkJson::decodeFromStream)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getAutoSearchComplete(q: String): List<NetworkSearchComplete> =
        withContext(ioDispatcher) {
            assets.open(AUTO_COMPLETE_ASSET).use(networkJson::decodeFromStream)
        }*/


    companion object {
        private const val AUTO_COMPLETE_ASSET = "search.json"
        private const val FORECAST_WEATHER_ASSET = "forecast.json"

    }

    override suspend fun getWeather(): NetworkWeather {
        TODO("Not yet implemented")
    }

    override suspend fun getAutoSearchComplete(): List<NetworkSearchComplete> {
        TODO("Not yet implemented")
    }


}