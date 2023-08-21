/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 5:47 PM
 *
 */

plugins {
    id("weatherapp.android.library")
    id("weatherapp.android.library.jacoco")
    id("weatherapp.android.hilt")
    id("org.jetbrains.dokka")
}

android {
    namespace = "com.bassamapps.weatherapp.core.common"
}

dependencies {
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.play.services)
    implementation(libs.google.service.location)
    implementation(libs.retrofit.kotlin.serialization)
    testImplementation(project(":core:testing"))
}
