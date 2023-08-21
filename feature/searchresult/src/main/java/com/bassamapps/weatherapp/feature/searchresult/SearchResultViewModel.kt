/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/19/23, 7:00 PM
 *
 */

package com.bassamapps.weatherapp.feature.searchresult

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bassamapps.weatherapp.core.data.repository.UserDataRepository
import com.bassamapps.weatherapp.core.domain.GetWeatherUseCase
import com.bassamapps.weatherapp.core.domain.mapper.ResultWeatherMapper
import com.bassamapps.weatherapp.core.model.data.UserData
import com.bassamapps.weatherapp.core.result.Result
import com.bassamapps.weatherapp.feature.home.HomeUiState
import com.bassamapps.weatherapp.feature.home.WeatherUiAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("NAME_SHADOWING")
@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
    savedStateHandle: SavedStateHandle,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val resultWeatherMapper: ResultWeatherMapper,
) : ViewModel(){

    private val cityName: String = checkNotNull(savedStateHandle["cityName"])

    val action:(WeatherUiAction)->Unit

    private val viewModelState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState = viewModelState
        .map(HomeUiState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    private val userData = MutableStateFlow(UserData())

    init {
        userDataRepository.userData.map {
            userData.emit(it)
        }

        action = {
            when(it){
                WeatherUiAction.FetchData -> fetchWeatherData()

                WeatherUiAction.RefreshData -> refreshData()
            }
        }
        fetchWeatherData()
    }

    private fun fetchWeatherData(){
        viewModelScope.launch {

            getWeatherUseCase.invoke(
                isSearch = true, query = cityName, userData = userData)
                .collect{ response ->
                    when(response) {
                        is Result.Error -> viewModelState.update {
                            it.copy(error = response.message)
                        }
                        is Result.Loading -> viewModelState.update {
                            it.copy(error = "", isLoading = response.loading)
                        }
                        is Result.Success -> viewModelState.update {
                            it.copy(currentWeather = resultWeatherMapper
                                .mapFromApiResponse(response.data, us = userData))
                        }
                    }
                }
        }
    }

    private fun refreshData() {
        viewModelScope.launch {
            viewModelState.update {
                it.copy(isRefreshing = true)
            }
            fetchWeatherData()
            viewModelState.update {
                it.copy(isRefreshing = false)
            }
        }
    }

    /*
        // Exception Handler
        val exceptionHandler = CoroutineExceptionHandler { context, exception ->
            viewModelScope.launch {
                isError.emit(true)
                getError(errorType = ErrorType.EXCEPTION_ERROR, exception = exception.toString())
            }
        }

        // is it refreshing
        private val isRefreshing = MutableStateFlow(false)

        private val cityName: String = checkNotNull(savedStateHandle["cityName"])


        // error status and type
        private val isError = MutableStateFlow(false)
        private val error = MutableStateFlow(ErrorType.NON_ERROR)

        val userData = userDataRepository.userData
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = UserData(),
            )

        var uiState: StateFlow<SearchResultUiState> = MutableStateFlow(
            SearchResultUiState(
                CurrentWeatherUiState.Loading,
                isRefreshing = false,
                isError = false,
                error = ErrorType.NON_ERROR
            )
        )

        init {
            viewModelScope.launch {
                uiState = combine(
                    getWeatherUseCase
                        .invoke(isSearch = true, query = cityName, userData = userData)
                        .asResult(),
                    isRefreshing,
                    isError,
                    error,
                ) { currentWeather1, refreshing, errorOccurred, errorStringOccurred ->

                    val currentWeather: CurrentWeatherUiState = when (currentWeather1) {
                        is NetworkResponse.Success ->
                            when(currentWeather1.data) {
                                is DataResult.Success -> CurrentWeatherUiState.Success((currentWeather1.data as DataResult.Success<WeatherData>).data)
                                is DataResult.Error -> CurrentWeatherUiState.Exception((currentWeather1.data as DataResult.Error).exception)
                            }
                        is NetworkResponse.Loading -> CurrentWeatherUiState.Loading
                        is NetworkResponse.Error -> CurrentWeatherUiState.Error(currentWeather1.error.toErrorData())
                        is NetworkResponse.Exception  -> CurrentWeatherUiState.Exception(currentWeather1.exception)
                    }

                    if (currentWeather is CurrentWeatherUiState.Error) {
                        getError(ErrorType.API_ERROR, currentWeather.error.error.message)
                    } else if (currentWeather is CurrentWeatherUiState.Exception) {
                        getError(ErrorType.EXCEPTION_ERROR, currentWeather.exception?.message.toString())
                    }

                    SearchResultUiState(
                        currentWeather,
                        refreshing,
                        errorOccurred,
                        errorStringOccurred
                    )
                }.stateIn(
                    scope = viewModelScope,
                    started = WhileUiSubscribed,
                    initialValue = SearchResultUiState(
                        CurrentWeatherUiState.Loading,
                        isRefreshing = false,
                        isError = false,
                        error = ErrorType.NON_ERROR
                    )
                )
            }
        }

        fun  onRefresh() {
            onErrorConsumed()
            viewModelScope.launch(exceptionHandler) {
                val refreshCurrentWeather = async {
                    getWeatherUseCase
                        .invoke(isSearch = true, query = cityName, userData = userData)
                        .asResult()
                }
                isRefreshing.emit(true)
                try {
                    awaitAll(
                        refreshCurrentWeather,
                    )
                } finally {
                    isRefreshing.emit(false)
                }
            }
        }


        private fun onErrorConsumed() {
            viewModelScope.launch {
                isError.emit(false)
                error.emit(ErrorType.NON_ERROR)
            }
        }

        private fun getError(errorType: ErrorType, exception: String) {
            viewModelScope.launch {
                isError.emit(true)

                errorType.errorMessage = exception
                errorType.onReloadClick = {
                    onRefresh()
                }
                error.emit(errorType)
            }
        }

     */
}