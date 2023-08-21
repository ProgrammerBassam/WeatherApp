/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bassamapps.weatherapp.MainActivityUiState.Loading
import com.bassamapps.weatherapp.MainActivityUiState.Success
import com.bassamapps.weatherapp.core.data.repository.impl.ImplUserDataRepository
import com.bassamapps.weatherapp.core.model.data.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Main activity view model
 *
 * @constructor
 *
 * @param userDataRepository
 */
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    userDataRepository: ImplUserDataRepository,
) : ViewModel() {
    val uiState: StateFlow<MainActivityUiState> = userDataRepository.userData.map {

        if (!it.isFirstTime) {
            viewModelScope.launch {
                userDataRepository.setUseGps(true)
                userDataRepository.setIsTempC(true)
                userDataRepository.setIsFirstTime(true)
            }
        }

        Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )


}

/**
 * Main activity ui state
 *
 * @constructor Create empty Main activity ui state
 */
sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState

    /**
     * Success
     *
     * @property userData
     * @constructor Create empty Success
     */
    data class Success(val userData: UserData) : MainActivityUiState
}