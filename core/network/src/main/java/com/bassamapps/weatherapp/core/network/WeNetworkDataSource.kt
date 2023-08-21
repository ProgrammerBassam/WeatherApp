/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 12:12 AM
 *
 */

package com.bassamapps.weatherapp.core.network

import com.bassamapps.weatherapp.core.network.model.NetworkSearchComplete
import com.bassamapps.weatherapp.core.network.model.NetworkWeather
import retrofit2.Response

interface WeNetworkDataSource {

      suspend fun getWeather(q: String) : Response<NetworkWeather>

      suspend fun getAutoSearchComplete(q: String) : Response<List<NetworkSearchComplete>>

}