/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 8:51 PM
 *
 */

package com.bassamapps.weatherapp.core.network

import com.bassamapps.weatherapp.core.network.model.NetworkSearchComplete
import com.bassamapps.weatherapp.core.network.model.NetworkWeather
import retrofit2.Response

/**
 * We network data source
 *
 * @constructor Create empty We network data source
 */
interface WeNetworkDataSource {

      /**
       * Get weather
       *
       * @param q
       * @return
       */
      suspend fun getWeather(q: String) : Response<NetworkWeather>

      /**
       * Get auto search complete
       *
       * @param q
       * @return
       */
      suspend fun getAutoSearchComplete(q: String) : Response<List<NetworkSearchComplete>>

}