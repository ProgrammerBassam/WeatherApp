/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/18/23, 4:11 PM
 *
 */

plugins {
    id("weatherapp.android.feature")
    id("weatherapp.android.library.compose")
    id("weatherapp.android.library.jacoco")
}

android {
    namespace = "com.bassamapps.weatherapp.feature.search"
}

dependencies {
    implementation(project(":feature:home"))
    implementation(libs.kotlinx.datetime)
}