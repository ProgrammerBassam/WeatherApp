/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 4:44 PM
 *
 */

package com.bassamapps.weatherapp.core.network.utils

import com.bassamapps.weatherapp.core.network.Dispatcher
import com.bassamapps.weatherapp.core.network.WeDispatchers
import com.bassamapps.weatherapp.core.result.Result
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

/**
 * Network bound resource
 *
 * @property ioDispatcher
 * @constructor Create empty Network bound resource
 */
class NetworkBoundResource @Inject constructor(
    @Dispatcher(WeDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
){

    /**
     * Download data
     *
     * @param ResultType
     * @param api
     * @receiver
     * @return
     */
    suspend fun<ResultType> downloadData(api : suspend () -> Response<ResultType>): Flow<Result<ResultType>> {
        return withContext(ioDispatcher) {
            flow {
                emit(Result.Loading(true))
                val response:Response<ResultType> = api()
                emit(Result.Loading(false))
                if (response.isSuccessful){
                    response.body()?.let {
                        emit(Result.Success(data = it))
                    }?: emit(Result.Error(message = "Unknown error occurred", code = 0))
                }else{
                    emit(Result.Error(message = parserErrorBody(response.errorBody()), code = response.code()))
                }

            }.catch { error->
                emit(Result.Loading(false))
                delay(5)
                emit(Result.Error(message = message(error), code = code(error)))
            }
        }
    }

    private fun parserErrorBody(response: ResponseBody?):String{
        return response?.let {
            val errorMessage = JsonParser.parseString(it.string()).asJsonObject["message"].asString
            errorMessage.ifEmpty { "Whoops! Something went wrong. Please try again." }
            errorMessage
        }?:"Whoops! Unknown error occurred. Please try again"
    }
    private fun message(throwable: Throwable?):String{
        when (throwable) {
            is SocketTimeoutException -> return "Whoops! Connection time out. Please try again"
            is IOException -> return "Whoops! No Internet Connection. Please try again"
            is HttpException -> return try {
                val errorJsonString = throwable.response()?.errorBody()?.string()
                val errorMessage = JsonParser.parseString(errorJsonString).asJsonObject["message"].asString
                errorMessage.ifEmpty { "Whoops! Something went wrong. Please try again." }
            }catch (e:Exception){
                "Whoops! Unknown error occurred. Please try again"
            }
        }
        return "Whoops! Unknown error occurred. Please try again"
    }
    private fun code(throwable: Throwable?):Int{
        return if (throwable is HttpException) (throwable).code()
        else  0
    }
}