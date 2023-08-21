/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 7:27 PM
 *
 */

package com.bassamapps.weatherapp.core.network

import com.bassamapps.weatherapp.core.network.model.NetworkSearchComplete
import com.bassamapps.weatherapp.core.network.model.NetworkWeather


/**
 * We demo network data source
 *
 * @constructor Create empty We demo network data source
 */
interface WeDemoNetworkDataSource {

    /**
     * Get weather
     *
     * @return
     */
    suspend fun getWeather() : NetworkWeather

    /**
     * Get auto search complete
     *
     * @return
     */
    suspend fun getAutoSearchComplete() : List<NetworkSearchComplete>

}