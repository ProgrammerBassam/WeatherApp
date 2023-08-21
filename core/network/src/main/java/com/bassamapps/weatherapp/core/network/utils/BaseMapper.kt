/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 4:31 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 4:31 PM
 *
 */

package com.bassamapps.weatherapp.core.network.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.bassamapps.weatherapp.core.result.Result

interface Mapper<R,E>{
    fun mapFromApiResponse(type:R):E
}

fun<R,E> mapFromApiResponse(result: Flow<Result<R>>, mapper: Mapper<R, E>): Flow<Result<E>> {
    return result.map {
        when(it){
            is Result.Success-> Result.Success(mapper.mapFromApiResponse(it.data))
            is Result.Error->Result.Error(it.message,it.code)
            is Result.Loading -> Result.Loading(it.loading)
        }
    }
}