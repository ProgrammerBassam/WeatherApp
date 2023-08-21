/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/16/23, 10:55 PM
 *
 */

plugins {
    id("weatherapp.android.feature")
    id("weatherapp.android.library.compose")
    id("weatherapp.android.library.jacoco")
}

android {
    namespace = "com.bassamapps.weatherapp.feature.home"
}

dependencies {
    implementation(libs.kotlinx.datetime)
    implementation(libs.androidx.activity.compose)
    implementation(libs.accompanist.permissions)
    implementation(libs.androidx.compose.material)
}