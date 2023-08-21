/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/17/23, 12:30 AM
 *
 */

package com.bassamapps.weatherapp

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.imageLoader
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import javax.inject.Provider

/**
 * [Application] class for Weather App
 */
@HiltAndroidApp
class WeApplication : Application(), ImageLoaderFactory {

    @Inject
    lateinit var imageLoader: Provider<ImageLoader>

    override fun onCreate() {
        super.onCreate()

    }

    override fun newImageLoader(): ImageLoader = imageLoader.get()
}
