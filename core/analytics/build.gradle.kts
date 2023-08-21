/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *  
 */
plugins {
    id("weatherapp.android.library")
    id("weatherapp.android.library.compose")
    id("weatherapp.android.hilt")
    id("org.jetbrains.dokka")
}

android {
    namespace = "com.bassamapps.weatherappcore.core.analytics"
}

dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.core.ktx)
    implementation(libs.firebase.analytics)
    implementation(libs.kotlinx.coroutines.android)
}
