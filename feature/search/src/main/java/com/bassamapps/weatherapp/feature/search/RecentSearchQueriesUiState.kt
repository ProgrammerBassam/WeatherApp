/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 8:29 PM
 *
 */

package com.bassamapps.weatherapp.feature.search

import com.bassamapps.weatherapp.core.data.model.RecentSearchQuery

/**
 * Recent search queries ui state
 *
 * @constructor Create empty Recent search queries ui state
 */
sealed interface RecentSearchQueriesUiState {
    data object Loading : RecentSearchQueriesUiState

    /**
     * Success
     *
     * @property recentQueries
     * @constructor Create empty Success
     */
    data class Success(
        val recentQueries: List<RecentSearchQuery> = emptyList(),
    ) : RecentSearchQueriesUiState
}