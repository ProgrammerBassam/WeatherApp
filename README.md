# WeatherApp
Weather App Task that load weather data accroding to the user current location and has the ability to search about weather in different cities and states.

# APK Link
[Download APK](https://developer.android.com/kotlin/flow)

# Build Versions
  * Min SDK is 21
  * Compile/Target SDK is 34
  * Kotlin is 1.9.0
  * Gradle 8.2

# Features
  * Jetpack Compose
  * Room
  * Dao
  * Androidx Splash Screen
  * Firebase
  * Datastore
  * Hilt
  * Navigation Compose
  * Startup
  * Coil
  * Google Oss
  * Coroutines
  * Coroutines PlayServices
  * Lint
  * Retrofit
  * Secrets
  * Google Fonts
  * Google Location Service
  * Google Code Gson

# How To Run
  * create firebase project and then insert the id app it to projects make sure to download google-services.json and put it inside app folder.

  * feautrs we've using with firebase are analystics, performance and crashlytics

# Architecture overview
The app architecture has three layers: a [data layer](https://developer.android.com/jetpack/guide/data-layer), a [domain layer](https://developer.android.com/jetpack/guide/domain-layer) and a [UI layer](https://developer.android.com/jetpack/guide/ui-layer).

The architecture follows a reactive programming model with [unidirectional data flow](https://developer.android.com/jetpack/guide/ui-layer#udf). With the data layer at the bottom, the key concepts are:


*   Higher layers react to changes in lower layers.
*   Events flow down.
*   Data flows up.

The data flow is achieved using streams, implemented using [Kotlin Flows](https://developer.android.com/kotlin/flow).

### Example: Displaying weather data on the Home screen
When the app is first run it will attempt to load weather data from server if location not granted it will ask for the location permission . Once loaded, these are shown to the user based on the current location.


# UI
The app was designed using [Material 3 guidelines](https://m3.material.io/).

The Screens and UI elements are built entirely using [Jetpack Compose](https://developer.android.com/jetpack/compose). 

The app has two themes: 

- Dynamic color - uses colors based on the [user's current color theme](https://material.io/blog/announcing-material-you) (if supported)
- Default theme - uses predefined colors when dynamic color is not supported

Each theme also supports dark mode. 
  

