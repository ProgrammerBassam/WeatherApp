/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/14/23, 6:21 PM
 *
 */

package com.bassamapps.weatherapp.core.analytics

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AnalyticsModule {
    @Binds
    abstract fun bindsAnalyticsHelper(analyticsHelperImpl: FirebaseAnalyticsHelper): AnalyticsHelper

    companion object {
        @Provides
        @Singleton
        fun provideFirebaseAnalytics(): FirebaseAnalytics { return Firebase.analytics }
    }
}
