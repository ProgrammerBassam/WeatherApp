/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.core.data.di


import com.bassamapps.weatherapp.core.data.repository.RecentSearchRepository
import com.bassamapps.weatherapp.core.data.repository.UserDataRepository
import com.bassamapps.weatherapp.core.data.repository.WeatherRepository
import com.bassamapps.weatherapp.core.data.repository.impl.ImplRecentSearchRepository
import com.bassamapps.weatherapp.core.data.repository.impl.ImplUserDataRepository
import com.bassamapps.weatherapp.core.data.repository.impl.ImplWeatherRepository
import com.bassamapps.weatherapp.core.data.util.ConnectivityManagerNetworkMonitor
import com.bassamapps.weatherapp.core.data.util.NetworkMonitor
import com.bassamapps.weatherapp.core.data.util.location.GpsMonitor
import com.bassamapps.weatherapp.core.data.util.location.LocationManagerGpsMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Data module
 *
 * @constructor Create empty Data module
 */
@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    /**
     * Binds recent search repository
     *
     * @param recentSearchRepository
     * @return
     */
    @Binds
    fun bindsRecentSearchRepository(
        recentSearchRepository: RecentSearchRepository,
    ): ImplRecentSearchRepository

    /**
     * Binds current weather repository
     *
     * @param currentWeatherRepository
     * @return
     */
    @Binds
    fun bindsCurrentWeatherRepository(
        currentWeatherRepository: WeatherRepository,
    ): ImplWeatherRepository

    /**
     * Binds user data repository
     *
     * @param userDataRepository
     * @return
     */
    @Binds
    fun bindsUserDataRepository(
        userDataRepository: UserDataRepository,
    ): ImplUserDataRepository

    /**
     * Binds network monitor
     *
     * @param networkMonitor
     * @return
     */
    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor

    /**
     * Binds gps monitor
     *
     * @param gpsMonitor
     * @return
     */
    @Binds
    fun bindsGpsMonitor(
        gpsMonitor: LocationManagerGpsMonitor,
    ): GpsMonitor
}
