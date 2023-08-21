/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 7:58 PM
 *
 */

package com.bassamapps.weatherapp.feature.searchresult

import com.bassamapps.weatherapp.core.ui.ErrorType

/**
 * Search result ui state
 *
 * @property isRefreshing
 * @property isError
 * @property error
 * @constructor Create empty Search result ui state
 */
data class SearchResultUiState(
    val isRefreshing: Boolean,
    val isError: Boolean,
    val error: ErrorType
)

