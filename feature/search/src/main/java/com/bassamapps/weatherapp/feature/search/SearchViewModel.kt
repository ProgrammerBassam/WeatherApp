/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/18/23, 11:16 PM
 *
 */

package com.bassamapps.weatherapp.feature.search

/*
import com.bassamapps.weatherapp.core.domain.GetSearchContentsCountUseCase
import com.bassamapps.weatherapp.core.domain.GetSearchContentsUseCase
import com.bassamapps.weatherapp.core.result.Result
import com.bassamapps.weatherapp.core.result.asResult */
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bassamapps.weatherapp.core.analytics.AnalyticsEvent
import com.bassamapps.weatherapp.core.analytics.AnalyticsHelper
import com.bassamapps.weatherapp.core.data.repository.RecentSearchRepository
import com.bassamapps.weatherapp.core.data.repository.WeatherRepository
import com.bassamapps.weatherapp.core.domain.GetRecentSearchQueriesUseCase
import com.bassamapps.weatherapp.core.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    recentSearchQueriesUseCase: GetRecentSearchQueriesUseCase,
    private val recentSearchRepository: RecentSearchRepository,
    private val savedStateHandle: SavedStateHandle,
    private val analyticsHelper: AnalyticsHelper,
) : ViewModel() {

    val searchQuery = savedStateHandle.getStateFlow(SEARCH_QUERY, "")


    private val viewModelStateOfSearch = MutableStateFlow(SearchCompleteUiState(isLoading = false))
    val searchCompleteUiState = viewModelStateOfSearch
        .map(SearchCompleteUiState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelStateOfSearch.value.toUiState()
        )

    val recentSearchQueriesUiState: StateFlow<RecentSearchQueriesUiState> =
        recentSearchQueriesUseCase().map(RecentSearchQueriesUiState::Success)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = RecentSearchQueriesUiState.Loading,
            )



    private fun getSearchAutoCompleteData(){
        viewModelScope.launch {

            val query = searchQuery.value

            if (searchQuery.value.length >= SEARCH_QUERY_MIN_LENGTH) {
                weatherRepository.getAutoSearchComplete(query).collect { response ->
                    when(response) {
                        is Result.Error -> viewModelStateOfSearch.update {
                            it.copy(error = response.message)
                        }
                        is Result.Loading -> viewModelStateOfSearch.update {
                            it.copy(error = "", isLoading = response.loading)
                        }
                        is Result.Success -> viewModelStateOfSearch.update {
                            it.copy(searchList = response.data)
                        }
                    }
                }
            }
        }
    }


    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
        getSearchAutoCompleteData()
    }

    /**
     * Called when the search action is explicitly triggered by the user. For example, when the
     * search icon is tapped in the IME or when the enter key is pressed in the search text field.
     *
     * The search results are displayed on the fly as the user types, but to explicitly save the
     * search query in the search text field, defining this method.
     */
    fun onSearchTriggered(query: String) {
        viewModelScope.launch {
            recentSearchRepository.insertOrReplaceRecentSearch(query)
        }
        analyticsHelper.logEvent(
            AnalyticsEvent(
                type = SEARCH_QUERY,
                extras = listOf(
                    AnalyticsEvent.Param(SEARCH_QUERY, query),
                ),
            ),
        )
    }

    fun clearRecentSearches() {
        viewModelScope.launch {
            recentSearchRepository.clearRecentSearches()
        }
    }
}

/** Minimum length where search query is considered  */
private const val SEARCH_QUERY_MIN_LENGTH = 4

private const val SEARCH_QUERY = "searchQuery"