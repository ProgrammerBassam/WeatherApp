/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/19/23, 6:54 PM
 *
 */

package com.bassamapps.weatherapp.feature.searchresult

import com.bassamapps.weatherapp.core.ui.ErrorType

data class SearchResultUiState(
    val isRefreshing: Boolean,
    val isError: Boolean,
    val error: ErrorType
)

