/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/14/23, 7:17 PM
 *
 */

plugins {
    id("weatherapp.android.feature")
    id("weatherapp.android.library.compose")
    id("weatherapp.android.library.jacoco")
}

android {
    namespace = "com.bassamapps.weatherapp.feature.settings"
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.google.oss.licenses) {
        exclude(group = "androidx.appcompat")
    }
}
