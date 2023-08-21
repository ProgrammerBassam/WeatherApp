/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 9:19 PM
 *
 */

package com.bassamapps.weatherapp.feature.searchresult

import androidx.activity.compose.ReportDrawnWhen
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bassamapps.weatherapp.core.designsystem.component.WeBackground
import com.bassamapps.weatherapp.core.designsystem.component.WeTopAppBar
import com.bassamapps.weatherapp.core.designsystem.icon.WeIcons
import com.bassamapps.weatherapp.core.model.data.WeatherData
import com.bassamapps.weatherapp.core.ui.ErrorScreen
import com.bassamapps.weatherapp.core.ui.TrackScreenViewEvent
import com.bassamapps.weatherapp.feature.home.FooterSection
import com.bassamapps.weatherapp.feature.home.HeadSectionBody
import com.bassamapps.weatherapp.feature.home.LoadingState
import com.bassamapps.weatherapp.feature.home.WeatherUiAction
import com.bassamapps.weatherapp.feature.home.WeatherUiState
import com.bassamapps.weatherapp.feature.home.dayForecastWeatherCard
import com.bassamapps.weatherapp.feature.home.extraWeatherList
import com.bassamapps.weatherapp.feature.home.futureWeatherCard

/**
 * Search result route
 *
 * @param onBackClick
 * @param viewModel
 * @receiver
 */
@Composable
internal fun SearchResultRoute(
    onBackClick: () -> Unit,
    viewModel: SearchResultViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiAction = remember(viewModel){
        val action:(WeatherUiAction)->Unit = { viewModel.action(it) }
        action
    }


    SearchResultScreen(
        uiState = uiState,
        onReload = {
            uiAction(WeatherUiAction.FetchData)
        },
        onRefresh = {
            uiAction(WeatherUiAction.RefreshData)
        },
        onBackClick = onBackClick
    )
}

/**
 * Search result screen
 *
 * @param modifier
 * @param uiState
 * @param onReload
 * @param onRefresh
 * @param onBackClick
 * @receiver
 * @receiver
 * @receiver
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun SearchResultScreen(
    modifier: Modifier = Modifier,
    uiState: WeatherUiState,
    onReload :()->Unit,
    onRefresh :()->Unit,
    onBackClick: () -> Unit = {},
) {
    // This code should be called when the UI is ready for use and relates to Time To Full Display.
    ReportDrawnWhen { !uiState.isLoading }

    val state = rememberLazyListState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isRefreshing,
        onRefresh = onRefresh
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
    ) {

        when(uiState) {
            is WeatherUiState.HasData -> {
               SearchResultBody(
                    currentWeather = uiState.currentWeather,
                    state = state,
                    onBackClick  = onBackClick,
                )
            }
            is WeatherUiState.WeatherEmpty -> {
                if (uiState.error.isNotEmpty()) {
                    ErrorScreen(
                        message = uiState.error,
                        onClickRefresh = onReload
                    )
                }
            }
        }


        LoadingState(
            isLoading = uiState.isLoading,
            isRefreshing = uiState.isRefreshing,
            modifier = Modifier.align(Alignment.TopCenter),
            pullRefreshState = pullRefreshState
        )
    }


    TrackScreenViewEvent(screenName = "SearchResult")
}

/**
 * Search result body
 *
 * @param currentWeather
 * @param state
 * @param onBackClick
 * @receiver
 */
@Composable
fun SearchResultBody(
    currentWeather: WeatherData,
    state: LazyListState,
    onBackClick: () -> Unit,
) {
    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxSize()
            .testTag("searchresult:head"),
    ) {
        searchResultSection(
            currentWeather = currentWeather,
            onBackClick = onBackClick,
        )

        dayForecastWeatherCard(
            forecastData = currentWeather.weatherForecastHourData,
        )

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        futureWeatherCard(
            futureData = currentWeather.futureData,
        )

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        extraWeatherList(
            extraData = currentWeather.extra,
        )

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            FooterSection(
                footerData = currentWeather.weatherFooterData,
            )
        }

        item {
            Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
        }
    }
}

/**
 * Search result section
 *
 * @param currentWeather
 * @param onBackClick
 * @receiver
 */
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
fun LazyListScope.searchResultSection(
    currentWeather: WeatherData,
    onBackClick: () -> Unit,
) {
    stickyHeader {
        WeBackground {
            Column {

                WeTopAppBar(
                    navigationIcon = WeIcons.ArrowBack,
                    navigationIconContentDescription = "",
                    onNavigationClick= onBackClick,
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Transparent,
                    ),
                )

                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = currentWeather.locationName,
                    style = MaterialTheme.typography.headlineMedium
                )

                HeadSectionBody(
                    weatherHeadData = currentWeather.weatherHeadData
                )
            }
        }
    }

}

/*




@Composable
private fun SearchResultToolbar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
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
    }
}*/