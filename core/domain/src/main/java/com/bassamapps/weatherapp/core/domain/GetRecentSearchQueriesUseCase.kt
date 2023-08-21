/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.core.domain

import com.bassamapps.weatherapp.core.data.model.RecentSearchQuery
import com.bassamapps.weatherapp.core.data.repository.RecentSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Get recent search queries use case
 *
 * @property recentSearchRepository
 * @constructor Create empty Get recent search queries use case
 *//*
* A use case which returns the recent search queries.
*/
class GetRecentSearchQueriesUseCase @Inject constructor(
    private val recentSearchRepository: RecentSearchRepository,
) {
    /**
     * Invoke
     *
     * @param limit
     * @return
     */
    operator fun invoke(limit: Int = 10): Flow<List<RecentSearchQuery>> =
        recentSearchRepository.getRecentSearchQueries(limit)
}