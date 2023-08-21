/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 8:29 PM
 *
 */

package com.bassamapps.weatherapp.feature.search

import com.bassamapps.weatherapp.core.data.model.SearchCompleteResult

/**
 * Search complete ui state
 *
 * @property isLoading
 * @property error
 * @property searchList
 * @constructor Create empty Search complete ui state
 */
data class SearchCompleteUiState(
    val isLoading:Boolean = false,
    val error:String = "",
    val searchList: List<SearchCompleteResult> = emptyList()
) {
    /**
     * To ui state
     *
     * @return
     */
    fun toUiState(): SearchListUiState =
        if (searchList.isEmpty()) SearchListUiState.SearchListEmpty(isLoading = isLoading, error = error)
        else SearchListUiState.HasData(isLoading = isLoading, error = error, searchList = searchList)
}

/**
 * Search list ui state
 *
 * @constructor Create empty Search list ui state
 */
sealed interface SearchListUiState{
    val isLoading:Boolean
    val error:String

    /**
     * Has data
     *
     * @property searchList
     * @property isLoading
     * @property error
     * @constructor Create empty Has data
     */
    data class HasData(
        val searchList: List<SearchCompleteResult>,
        override val isLoading: Boolean,
        override val error: String
    ): SearchListUiState

    /**
     * Search list empty
     *
     * @property isLoading
     * @property error
     * @constructor Create empty Search list empty
     */
    data class SearchListEmpty(
        override val isLoading: Boolean,
        override val error: String
    ): SearchListUiState
}

/*sealed interface SearchCompleteUiState {
    data object Loading : SearchCompleteUiState


    data object Empty : SearchCompleteUiState

    data class Success(
        val completeStrings: List<SearchCompleteResult> = emptyList(),
    ) : SearchCompleteUiState

    data class Exception(val exception: Throwable? = null) : SearchCompleteUiState
    data class Error(val error: ErrorData) : SearchCompleteUiState
}*/