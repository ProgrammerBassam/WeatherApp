/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 5:50 PM
 *
 */

plugins {
    id("weatherapp.android.library")
    id("weatherapp.android.library.jacoco")
    kotlin("kapt")
    id("org.jetbrains.dokka")
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