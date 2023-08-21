/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 1:18 AM
 *
 */

package com.bassamapps.weatherapp.feature.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bassamapps.weatherapp.core.data.repository.UserDataRepository
import com.bassamapps.weatherapp.core.domain.GetWeatherUseCase
import com.bassamapps.weatherapp.core.domain.mapper.ResultWeatherMapper
import com.bassamapps.weatherapp.core.location.isPermissionGranted
import com.bassamapps.weatherapp.core.model.data.UserData
import com.bassamapps.weatherapp.core.result.Result
import com.bassamapps.weatherapp.core.strings.ResourcesProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@Suppress("NAME_SHADOWING")
@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val userDataRepository: UserDataRepository,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val resultWeatherMapper: ResultWeatherMapper,
    private val resourcesProvider: ResourcesProvider,
) : ViewModel(){

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

            if (!isPermissionGranted(context = context)) {
                viewModelState.update {
                    it.copy(error = resourcesProvider.getString(R.string.location_permission_not_granted), isLoading = false, isRefreshing = false)
                }
            } else {

                getWeatherUseCase.invoke(userData = userData)
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
}


