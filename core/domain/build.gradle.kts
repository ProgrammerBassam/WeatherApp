/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/17/23, 9:02 PM
 *
 */

plugins {
    id("weatherapp.android.library")
    id("weatherapp.android.library.jacoco")
    kotlin("kapt")
}

android {
    namespace = "com.bassamapps.weatherapp.core.domain"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(libs.hilt.android)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.google.code.gson)

    kapt(libs.hilt.compiler)

    testImplementation(project(":core:testing"))
}