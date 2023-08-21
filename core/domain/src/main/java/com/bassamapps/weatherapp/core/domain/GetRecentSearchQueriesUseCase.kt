/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/18/23, 5:16 PM
 *
 */

package com.bassamapps.weatherapp.core.domain

import com.bassamapps.weatherapp.core.data.model.RecentSearchQuery
import com.bassamapps.weatherapp.core.data.repository.RecentSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*
* A use case which returns the recent search queries.
*/
class GetRecentSearchQueriesUseCase @Inject constructor(
    private val recentSearchRepository: RecentSearchRepository,
) {
    operator fun invoke(limit: Int = 10): Flow<List<RecentSearchQuery>> =
        recentSearchRepository.getRecentSearchQueries(limit)
}