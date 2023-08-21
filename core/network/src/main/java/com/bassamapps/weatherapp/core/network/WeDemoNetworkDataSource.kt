/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 7:26 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 7:26 PM
 *
 */

package com.bassamapps.weatherapp.core.network

import com.bassamapps.weatherapp.core.network.model.NetworkSearchComplete
import com.bassamapps.weatherapp.core.network.model.NetworkWeather


interface WeDemoNetworkDataSource {

    suspend fun getWeather(q: String) : NetworkWeather

    suspend fun getAutoSearchComplete(q: String) : List<NetworkSearchComplete>

}