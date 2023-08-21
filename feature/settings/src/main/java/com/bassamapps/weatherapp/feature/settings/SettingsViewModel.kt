/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 9:27 PM
 *
 */

package com.bassamapps.weatherapp.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bassamapps.weatherapp.core.data.repository.UserDataRepository
import com.bassamapps.weatherapp.core.model.data.DarkThemeConfig
import com.bassamapps.weatherapp.core.model.data.ThemeBrand
import com.bassamapps.weatherapp.feature.settings.SettingsUiState.Loading
import com.bassamapps.weatherapp.feature.settings.SettingsUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Settings view model
 *
 * @property userDataRepository
 * @constructor Create empty Settings view model
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
) : ViewModel() {
    val settingsUiState: StateFlow<SettingsUiState> =
        userDataRepository.userData
            .map { userData ->
                Success(
                    settings = UserEditableSettings(
                        brand = userData.themeBrand,
                        useDynamicColor = userData.useDynamicColor,
                        darkThemeConfig = userData.darkThemeConfig,
                        isTempC = userData.isTempC
                    ),
                )
            }
            .stateIn(
                scope = viewModelScope,
                // Starting eagerly means the user data is ready when the SettingsDialog is laid out
                // for the first time. Without this, due to b/221643630 the layout is done using the
                // "Loading" text, then replaced with the user editable fields once loaded, however,
                // the layout height doesn't change meaning all the fields are squashed into a small
                // scrollable column.
                // TODO: Change to SharingStarted.WhileSubscribed(5_000) when b/221643630 is fixed
                started = SharingStarted.Eagerly,
                initialValue = Loading,
            )

    /**
     * Update theme brand
     *
     * @param themeBrand
     */
    fun updateThemeBrand(themeBrand: ThemeBrand) {
        viewModelScope.launch {
            userDataRepository.setThemeBrand(themeBrand)
        }
    }

    /**
     * Update dark theme config
     *
     * @param darkThemeConfig
     */
    fun updateDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        viewModelScope.launch {
            userDataRepository.setDarkThemeConfig(darkThemeConfig)
        }
    }

    /**
     * Update dynamic color preference
     *
     * @param useDynamicColor
     */
    fun updateDynamicColorPreference(useDynamicColor: Boolean) {
        viewModelScope.launch {
            userDataRepository.setDynamicColorPreference(useDynamicColor)
        }
    }

    /**
     * Update temp type
     *
     * @param isTempC
     */
    fun updateTempType(isTempC: Boolean) {
        viewModelScope.launch {
            userDataRepository.setIsTempC(isTempC)
        }
    }
}

/**
 * User editable settings
 *
 * @property brand
 * @property useDynamicColor
 * @property darkThemeConfig
 * @property isTempC
 * @constructor Create empty User editable settings
 */
data class UserEditableSettings(
    val brand: ThemeBrand,
    val useDynamicColor: Boolean,
    val darkThemeConfig: DarkThemeConfig,
    val isTempC: Boolean
)

/**
 * Settings ui state
 *
 * @constructor Create empty Settings ui state
 */
sealed interface SettingsUiState {
    data object Loading : SettingsUiState

    /**
     * Success
     *
     * @property settings
     * @constructor Create empty Success
     */
    data class Success(val settings: UserEditableSettings) : SettingsUiState
}
