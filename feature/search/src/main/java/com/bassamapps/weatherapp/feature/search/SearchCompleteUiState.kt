/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/18/23, 9:41 PM
 *
 */

package com.bassamapps.weatherapp.feature.search

import com.bassamapps.weatherapp.core.data.model.SearchCompleteResult

data class SearchCompleteUiState(
    val isLoading:Boolean = false,
    val error:String = "",
    val searchList: List<SearchCompleteResult> = emptyList()
) {
    fun toUiState(): SearchListUiState =
        if (searchList.isEmpty()) SearchListUiState.SearchListEmpty(isLoading = isLoading, error = error)
        else SearchListUiState.HasData(isLoading = isLoading, error = error, searchList = searchList)
}

sealed interface SearchListUiState{
    val isLoading:Boolean
    val error:String

    data class HasData(
        val searchList: List<SearchCompleteResult>,
        override val isLoading: Boolean,
        override val error: String
    ): SearchListUiState

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