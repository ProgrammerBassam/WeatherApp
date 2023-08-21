/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 8:55 PM
 *
 */

package com.bassamapps.weatherapp.core.data.mapper

import com.bassamapps.weatherapp.core.data.model.SearchCompleteResult
import com.bassamapps.weatherapp.core.network.model.NetworkSearchComplete
import com.bassamapps.weatherapp.core.network.utils.Mapper
import javax.inject.Inject

/**
 * Network search complete mapper
 *
 * @constructor Create empty Network search complete mapper
 */
class NetworkSearchCompleteMapper @Inject constructor() : Mapper<List<NetworkSearchComplete>, List<SearchCompleteResult>> {
    override fun mapFromApiResponse(data: List<NetworkSearchComplete>): List<SearchCompleteResult> {
        return data.map {
            SearchCompleteResult(
                 id = it.id,
             name = it.name,
             region = it.region,
             country = it.country,
             lat = it.lat,
             lon = it.lon,
             url = it.url,
            )
        }

    }
}