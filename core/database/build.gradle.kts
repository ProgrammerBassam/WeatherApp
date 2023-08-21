/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/18/23, 4:21 PM
 *
 */

plugins {
    id("weatherapp.android.library")
    id("weatherapp.android.library.jacoco")
    id("weatherapp.android.hilt")
    id("weatherapp.android.room")
}

android {
    defaultConfig {
        testInstrumentationRunner =
            "com.bassamapps.weatherapp.core.testing.WeTestRunner"
    }
    namespace = "com.bassamapps.weatherapp.core.database"
}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)

    androidTestImplementation(project(":core:testing"))
}