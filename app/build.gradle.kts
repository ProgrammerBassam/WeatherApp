/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/18/23, 10:50 PM
 *
 */
import com.bassamapps.weatherapp.WeAppBuildType

plugins {
    id("weatherapp.android.application")
    id("weatherapp.android.application.compose")
    id("weatherapp.android.application.flavors")
    id("weatherapp.android.application.jacoco")
    id("weatherapp.android.hilt")
    id("jacoco")
    id("weatherapp.android.application.firebase")
    id("com.google.android.gms.oss-licenses-plugin")
}

android {
    defaultConfig {
        applicationId = "com.bassamapps.weatherapp"
        versionCode = 1
        versionName = "0.0.1"

        // Custom test runner to set up Hilt dependency graph
        testInstrumentationRunner = "om.bassamapps.weatherapp.core.testing.WeAppTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = WeAppBuildType.DEBUG.applicationIdSuffix
        }
        val release by getting {
            isMinifyEnabled = true
            applicationIdSuffix = WeAppBuildType.RELEASE.applicationIdSuffix
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            // To publish on the Play store a private signing key is required, but to allow anyone
            // who clones the code to sign and run the release variant, use the debug signing key.
            // TODO: Abstract the signing configuration to a separate file to avoid hardcoding this.
            signingConfig = signingConfigs.getByName("debug")
        }
       /* create("benchmark") {
            // Enable all the optimizations from release build through initWith(release).
            initWith(release)
            matchingFallbacks.add("release")
            // Debug key signing is available to everyone.
            signingConfig = signingConfigs.getByName("debug")
            // Only use benchmark proguard rules
            proguardFiles("benchmark-rules.pro")
            isMinifyEnabled = true
            applicationIdSuffix = WeAppBuildType.BENCHMARK.applicationIdSuffix
        }*/
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    namespace = "com.bassamapps.weatherapp"
}

dependencies {
    implementation(project(":feature:home"))
    implementation(project(":feature:search"))
    implementation(project(":feature:searchresult"))
    implementation(project(":feature:settings"))

    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:analytics"))

    androidTestImplementation(project(":core:testing"))
   // androidTestImplementation(project(":core:datastore-test"))
   // androidTestImplementation(project(":core:data-test"))
    androidTestImplementation(project(":core:network"))
    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.accompanist.testharness)
    androidTestImplementation(kotlin("test"))
    debugImplementation(libs.androidx.compose.ui.testManifest)
    debugImplementation(project(":ui-test-hilt-manifest"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.compose.runtime.tracing)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.window.manager)
    implementation(libs.androidx.profileinstaller)
    implementation(libs.kotlinx.coroutines.guava)
    implementation(libs.coil.kt)
}
