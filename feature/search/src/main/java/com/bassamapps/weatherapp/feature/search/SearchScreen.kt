/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 9:05 PM
 *
 */

package com.bassamapps.weatherapp.feature.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bassamapps.weatherapp.core.designsystem.component.SmallLoading
import com.bassamapps.weatherapp.core.designsystem.component.WeErrorText
import com.bassamapps.weatherapp.core.designsystem.component.WeTopicTag
import com.bassamapps.weatherapp.core.designsystem.icon.WeIcons
import com.bassamapps.weatherapp.core.ui.TrackScreenViewEvent

/**
 * Search route
 *
 * @param modifier
 * @param onBackClick
 * @param onNavigateToSearchResult
 * @param viewModel
 * @receiver
 * @receiver
 */
@Composable
internal fun SearchRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onNavigateToSearchResult: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val searchCompleteUiState by viewModel.searchCompleteUiState.collectAsStateWithLifecycle()
    val recentSearchesUiState by viewModel.recentSearchQueriesUiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

    SearchScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onClearRecentSearches = viewModel::clearRecentSearches,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
        onSearchTriggered = {
            viewModel.onSearchTriggered(it)
            onNavigateToSearchResult(it)
        },
        recentSearchesUiState = recentSearchesUiState,
        searchQuery = searchQuery,
        searchCompleteUiState = searchCompleteUiState,
    )
}

/**
 * Search screen
 *
 * @param modifier
 * @param onBackClick
 * @param onClearRecentSearches
 * @param onSearchQueryChanged
 * @param onSearchTriggered
 * @param recentSearchesUiState
 * @param searchQuery
 * @param searchCompleteUiState
 * @receiver
 * @receiver
 * @receiver
 * @receiver
 */
@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onClearRecentSearches: () -> Unit = {},
    onSearchQueryChanged: (String) -> Unit = {},
    onSearchTriggered: (String) -> Unit = {},
    recentSearchesUiState: RecentSearchQueriesUiState = RecentSearchQueriesUiState.Loading,
    searchQuery: String = "",
    searchCompleteUiState: SearchListUiState,
) {
    TrackScreenViewEvent(screenName = "Search")

    Column(modifier = modifier) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
        SearchToolbar(
            onBackClick = onBackClick,
            onSearchQueryChanged = onSearchQueryChanged,
            onSearchTriggered = onSearchTriggered,
            searchQuery = searchQuery,
        )

        if (searchCompleteUiState.isLoading) {
            SmallLoading()
        } else {
            when(searchCompleteUiState){

                is SearchListUiState.HasData -> {
                    SearchAutoComplete(
                        onCompleteWordClicked = {
                            onSearchQueryChanged(it)
                            onSearchTriggered(it)
                        },
                        completeStrings = searchCompleteUiState.searchList.map { it.name },
                    )
                }

                is SearchListUiState.SearchListEmpty -> {
                    if (searchCompleteUiState.error.isNotEmpty()) {
                        WeErrorText(errorMessage = searchCompleteUiState.error)
                    }
                }
            }
        }

        when(recentSearchesUiState) {
            RecentSearchQueriesUiState.Loading -> SmallLoading()
            is RecentSearchQueriesUiState.Success -> {
                RecentSearchesBody(
                    onClearRecentSearches = onClearRecentSearches,
                    onRecentSearchClicked = {
                        onSearchQueryChanged(it)
                        onSearchTriggered(it)
                    },
                    recentSearchQueries = recentSearchesUiState.recentQueries.map { it.query },
                )
            }
        }

        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
    }
}

@Composable
private fun SearchToolbar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    searchQuery: String = "",
    onSearchTriggered: (String) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = WeIcons.ArrowBack,
                contentDescription = stringResource(
                    id = R.string.back,
                ),
            )
        }
        SearchTextField(
            onSearchQueryChanged = onSearchQueryChanged,
            onSearchTriggered = onSearchTriggered,
            searchQuery = searchQuery,
        )
    }
}

@Composable
private fun SearchTextField(
    onSearchQueryChanged: (String) -> Unit,
    searchQuery: String,
    onSearchTriggered: (String) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    val onSearchExplicitlyTriggered = {
        keyboardController?.hide()
        onSearchTriggered(searchQuery)
    }

    TextField(
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        leadingIcon = {
            Icon(
                imageVector = WeIcons.Search,
                contentDescription = stringResource(
                    id = R.string.search,
                ),
                tint = MaterialTheme.colorScheme.onSurface,
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onSearchQueryChanged("")
                    },
                ) {
                    Icon(
                        imageVector = WeIcons.Close,
                        contentDescription = stringResource(
                            id = R.string.clear_search_text_content_desc,
                        ),
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },
        onValueChange = {
            if (!it.contains("\n")) {
                onSearchQueryChanged(it)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .focusRequester(focusRequester)
            .onKeyEvent {
                if (it.key == Key.Enter) {
                    onSearchExplicitlyTriggered()
                    true
                } else {
                    false
                }
            }
            .testTag("searchTextField"),
        shape = RoundedCornerShape(32.dp),
        value = searchQuery,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchExplicitlyTriggered()
            },
        ),
        maxLines = 1,
        singleLine = true,
    )
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}


@Composable
private fun SearchAutoComplete(
    onCompleteWordClicked: (String) -> Unit,
    completeStrings: List<String>,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 64.dp),
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),

        ) {
        items(completeStrings.size) { index ->
            WeTopicTag(
                onClick = {
                    onCompleteWordClicked(completeStrings[index])
                },
                text = { Text(text = completeStrings[index].uppercase()) },
            )
        }
    }
}

@Composable
private fun RecentSearchesBody(
    onClearRecentSearches: () -> Unit,
    onRecentSearchClicked: (String) -> Unit,
    recentSearchQueries: List<String>,
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(id = R.string.recent_searches))
                    }
                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            )
            if (recentSearchQueries.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onClearRecentSearches()
                    },
                    modifier = Modifier.padding(horizontal = 16.dp),
                ) {
                    Icon(
                        imageVector = WeIcons.Close,
                        contentDescription = stringResource(
                            id = R.string.clear_recent_searches_content_desc,
                        ),
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        }
        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
            items(recentSearchQueries) { recentSearch ->
                Text(
                    text = recentSearch,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .clickable {
                            onRecentSearchClicked(recentSearch)
                        }
                        .fillMaxWidth(),
                )
            }
        }
    }
}

