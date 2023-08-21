/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/18/23, 5:12 PM
 *
 */

package com.bassamapps.weatherapp.core.data.repository

import com.bassamapps.weatherapp.core.data.repository.impl.ImplRecentSearchRepository
import com.bassamapps.weatherapp.core.database.dao.RecentSearchQueryDao
import com.bassamapps.weatherapp.core.data.model.RecentSearchQuery
import com.bassamapps.weatherapp.core.data.model.asExternalModel
import com.bassamapps.weatherapp.core.database.model.RecentSearchQueryEntity
import com.bassamapps.weatherapp.core.network.Dispatcher
import com.bassamapps.weatherapp.core.network.WeDispatchers.IO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import javax.inject.Inject

class RecentSearchRepository @Inject constructor(
    private val recentSearchQueryDao: RecentSearchQueryDao,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : ImplRecentSearchRepository {
    override suspend fun insertOrReplaceRecentSearch(searchQuery: String) {
        withContext(ioDispatcher) {
            recentSearchQueryDao.insertOrReplaceRecentSearchQuery(
                RecentSearchQueryEntity(
                    query = searchQuery,
                    queriedDate = Clock.System.now(),
                ),
            )
        }
    }

    override fun getRecentSearchQueries(limit: Int): Flow<List<RecentSearchQuery>> =
        recentSearchQueryDao.getRecentSearchQueryEntities(limit).map { searchQueries ->
            searchQueries.map {
                it.asExternalModel()
            }
        }

    override suspend fun clearRecentSearches() = recentSearchQueryDao.clearRecentSearchQueries()
}