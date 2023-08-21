/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/18/23, 10:35 PM
 *
 */

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "weatherapp"
include(":app")
include(":core:analytics")
include(":core:ui")
include(":core:designsystem")
include(":core:data")
include(":core:model")
include(":core:common")
include(":core:domain")
include(":core:datastore")
include(":core:network")
include(":core:testing")
include(":feature:home")
include(":feature:search")
include(":feature:settings")
include(":lint")
include(":ui-test-hilt-manifest")
include(":core:database")
include(":feature:searchresult")
