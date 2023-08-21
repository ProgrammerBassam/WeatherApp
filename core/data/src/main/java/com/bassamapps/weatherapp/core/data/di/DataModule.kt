/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/18/23, 5:15 PM
 *
 */

package com.bassamapps.weatherapp.core.data.di


import com.bassamapps.weatherapp.core.data.repository.RecentSearchRepository
import com.bassamapps.weatherapp.core.data.repository.WeatherRepository
import com.bassamapps.weatherapp.core.data.repository.UserDataRepository
import com.bassamapps.weatherapp.core.data.repository.impl.ImplRecentSearchRepository
import com.bassamapps.weatherapp.core.data.repository.impl.ImplWeatherRepository
import com.bassamapps.weatherapp.core.data.repository.impl.ImplUserDataRepository
import com.bassamapps.weatherapp.core.data.util.ConnectivityManagerNetworkMonitor
import com.bassamapps.weatherapp.core.data.util.NetworkMonitor
import com.bassamapps.weatherapp.core.data.util.location.LocationManagerGpsMonitor
import com.bassamapps.weatherapp.core.data.util.location.GpsMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsRecentSearchRepository(
        recentSearchRepository: RecentSearchRepository,
    ): ImplRecentSearchRepository

    @Binds
    fun bindsCurrentWeatherRepository(
        currentWeatherRepository: WeatherRepository,
    ): ImplWeatherRepository

    @Binds
    fun bindsUserDataRepository(
        userDataRepository: UserDataRepository,
    ): ImplUserDataRepository

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor

    @Binds
    fun bindsGpsMonitor(
        gpsMonitor: LocationManagerGpsMonitor,
    ): GpsMonitor
}
