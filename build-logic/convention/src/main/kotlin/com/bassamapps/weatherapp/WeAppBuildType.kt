/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp

/**
 * This is shared between :app and :benchmarks module to provide configurations type safety.
 */
enum class WeAppBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE,
}
