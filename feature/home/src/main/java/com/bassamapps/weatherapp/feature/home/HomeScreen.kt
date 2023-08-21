/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/19/23, 10:25 PM
 *
 */

package com.bassamapps.weatherapp.feature.home

import androidx.activity.compose.ReportDrawnWhen
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.bassamapps.weatherapp.core.designsystem.component.WeBackground
import com.bassamapps.weatherapp.core.designsystem.component.WeOverlayLoadingWheel
import com.bassamapps.weatherapp.core.designsystem.icon.WeIcons
import com.bassamapps.weatherapp.core.model.data.FutureWeatherData
import com.bassamapps.weatherapp.core.model.data.HourData
import com.bassamapps.weatherapp.core.model.data.WeatherData
import com.bassamapps.weatherapp.core.model.data.WeatherExtraData
import com.bassamapps.weatherapp.core.model.data.WeatherFooterData
import com.bassamapps.weatherapp.core.model.data.WeatherForecastHourData
import com.bassamapps.weatherapp.core.model.data.WeatherHeadData
import com.bassamapps.weatherapp.core.ui.ErrorScreen
import com.bassamapps.weatherapp.core.ui.TrackScreenViewEvent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.bassamapps.weatherapp.core.ui.R as uiR


@Composable
internal fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiAction = remember(viewModel){
        val action:(WeatherUiAction)->Unit = { viewModel.action(it) }
        action
    }

    HomeScreen(
        uiState = uiState,
        onReload = {
            uiAction(WeatherUiAction.FetchData)
        },
        onRefresh = {
            uiAction(WeatherUiAction.RefreshData)
        },
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun HomeScreen(
    uiState: WeatherUiState,
    onReload :()->Unit,
    onRefresh :()->Unit,
    modifier: Modifier = Modifier,
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
               HomeBody(
                   currentWeather = uiState.currentWeather,
                   state = state
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

    LocationPermissionEffect()
    TrackScreenViewEvent(screenName = "Home")
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoadingState(
    isLoading: Boolean,
    isRefreshing: Boolean,
    modifier: Modifier = Modifier,
    pullRefreshState: PullRefreshState
) {
    PullRefreshIndicator(
        refreshing = isRefreshing,
        state = pullRefreshState,
        modifier = modifier,
    )

    AnimatedVisibility(
        visible = isLoading,
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> -fullHeight },
        ) + fadeIn(),
        exit = slideOutVertically(
            targetOffsetY = { fullHeight -> -fullHeight },
        ) + fadeOut(),
    ) {
        val loadingContentDescription = stringResource(id = uiR.string.loading)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
        ) {
            WeOverlayLoadingWheel(
                modifier = Modifier
                    .align(Alignment.Center),
                contentDesc = loadingContentDescription,
            )
        }
    }
}

@Composable
fun HomeBody(
    currentWeather: WeatherData,
    state: LazyListState
) {
    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxSize()
            .testTag("home:head"),
    ) {
        headSection(
            headData = currentWeather.weatherHeadData,
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

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.headSection(
    headData: WeatherHeadData,
) {
    stickyHeader {
        HeadSectionBody(
            weatherHeadData = headData
        )
    }
}

@Composable
fun HeadSectionBody(
    weatherHeadData: WeatherHeadData
) {
    WeBackground {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text =  "${weatherHeadData.currentDeg}°",
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 75.sp)

            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
                    .padding(top = 4.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.fillMaxHeight() // Match the height of the parent Box
                ) {
                    Text(
                        text = "${weatherHeadData.highDeg}° / ${weatherHeadData.lowDeg}°",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
                    )
                    Text(
                        text = weatherHeadData.description,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
            AsyncImage(
                model = weatherHeadData.icon,
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.Top)
            )
        }
    }
}

fun LazyListScope.dayForecastWeatherCard(
    forecastData: WeatherForecastHourData,
) {
    item {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)

        ) {
            Column(
                modifier = Modifier.padding(16.dp)

            ) {
                DayForecastWeatherHead(
                    data = forecastData
                )

                DayForecastWeatherList(
                    items = forecastData.hours
                )
            }

        }
    }
}

@Composable
fun DayForecastWeatherHead(
    data: WeatherForecastHourData
) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(
            text = "${data.description}.${data.type} ${data.degree}${data.degreeType}.",
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Divider()
    }
}

@Composable
fun DayForecastWeatherList(
    items: List<HourData>
) {
    LazyRow(
        modifier = Modifier,
        state = rememberLazyListState(),
        contentPadding = PaddingValues(vertical = 4.dp)
    ) {
        items(items.size) { index ->
            DayForecastWeatherItem(
                item = items[index]
            )
        }
    }
}

@Composable
internal fun DayForecastWeatherItem(
    item: HourData
) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = item.hour,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp))

        Spacer(modifier = Modifier.height(12.dp))

        AsyncImage(
            model = item.icon,
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "${item.degree}°",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 15.sp, fontWeight = FontWeight.Bold),
        )
    }
}


fun LazyListScope.futureWeatherCard(
    futureData: List<FutureWeatherData>,
) {
    futureWeatherList(
        items = futureData
    )
}

fun LazyListScope.futureWeatherList(
    items: List<FutureWeatherData>
) {
    items(items.size) { index ->
        FutureWeatherItem(
            item = items[index]
        )
    }
}

@Composable
internal fun FutureWeatherItem(
    item: FutureWeatherData
) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = item.dayName,
            modifier = Modifier
                .weight(0.3f)
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge
                .copy(fontSize = 15.sp, fontWeight = FontWeight.Bold),
        )

        Text(
            text = "${item.humidity}%",
            modifier = Modifier
                .weight(0.2f)
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodySmall
        )

        AsyncImage(
            model = item.dayIcon,
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.CenterVertically)
        )

        AsyncImage(
            model = item.nightIcon,
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.CenterVertically)
        )

        Text(
            text = "${item.highDeg}°  ${item.lowDeg}°",
            modifier = Modifier
                .weight(0.2f)
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
                .copy(fontSize = 15.sp, fontWeight = FontWeight.Bold),
        )

    }
}

fun LazyListScope.extraWeatherList(
    extraData: WeatherExtraData,
) {
    item {
        ExtraWeatherListBody(
            extraData = extraData
        )
    }
}

@Composable
internal fun ExtraWeatherListBody(
    extraData: WeatherExtraData
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(horizontal = 12.dp)
    ) {
        item {
            ExtraInfo(
                icon = WeIcons.UvIndex,
                title = stringResource(id = R.string.uv_index),
                subTitle = extraData.uv,
                tint = Color.Yellow
            )
        }

        item {
            ExtraInfo(
                icon = WeIcons.Humidity,
                title = stringResource(id = R.string.humidity),
                subTitle = "${extraData.humidity}%",
                tint = Color.Blue
            )
        }

        item {
            ExtraInfo(
                icon = WeIcons.Wind,
                title = stringResource(id = R.string.wind),
                subTitle = extraData.wind,
                tint = Color.LightGray
            )
        }

        item {
            ExtraInfoForSun(
                icon = WeIcons.Sun,
                sunriseTitle = stringResource(id = R.string.sunrise),
                sunrise = extraData.sunrise,
                sunsetTitle = stringResource(id = R.string.sunset),
                sunset = extraData.sunset,
                tint = Color.Red
            )
        }
    }
}

@Composable
internal fun ExtraInfo(
    icon: ImageVector,
    tint: Color,
    title: String,
    subTitle: String,
) {
    Box(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = tint,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = subTitle,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
internal fun ExtraInfoForSun(
    icon: ImageVector,
    tint: Color,
    sunriseTitle: String,
    sunrise: String,
    sunsetTitle: String,
    sunset: String,
) {
    Box(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = tint,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(Modifier.fillMaxWidth()) {
                        Box(Modifier.weight(1f)) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = sunriseTitle,
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = sunrise,
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }

                        Box(Modifier.weight(1f)) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = sunsetTitle,
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = sunset,
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FooterSection(
    footerData: WeatherFooterData,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text =  footerData.poweredBy,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(1f)
        )

        Text(
            text =  "${stringResource(id = uiR.string.updated)} ${footerData.lastUpdateTime}",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
@OptIn(ExperimentalPermissionsApi::class)
private fun LocationPermissionEffect() {
    // Permission requests should only be made from an Activity Context, which is not present
    // in previews
    if (LocalInspectionMode.current) return
    val locationPermissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
        )
    )

    LaunchedEffect(locationPermissionsState) {
        locationPermissionsState.permissions.forEach {
            val status = it.status
            if (status is PermissionStatus.Denied && !status.shouldShowRationale) {
                it.launchPermissionRequest()
            }
        }
    }
}