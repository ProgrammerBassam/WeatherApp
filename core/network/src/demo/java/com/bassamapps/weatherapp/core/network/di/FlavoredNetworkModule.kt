/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/16/23, 1:20 AM
 *
 */

package com.bassamapps.weatherapp.core.network.di

import com.bassamapps.weatherapp.core.network.WeNetworkDataSource
import com.bassamapps.weatherapp.core.network.fake.FakeWeNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FlavoredNetworkModule {

    @Binds
    fun FakeWeNetworkDataSource.binds(): WeNetworkDataSource
}