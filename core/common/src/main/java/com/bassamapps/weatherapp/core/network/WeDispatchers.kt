/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 1:16 AM
 *
 */

package com.bassamapps.weatherapp.core.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

/**
 * Dispatcher
 *
 * @property weDispatcher
 * @constructor Create empty Dispatcher
 */
@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val weDispatcher: WeDispatchers)

/**
 * We dispatchers
 *
 * @constructor Create empty We dispatchers
 */
enum class WeDispatchers {
    /**
     * Default
     *
     * @constructor Create empty Default
     */
    Default,

    /**
     * Io
     *
     * @constructor Create empty Io
     */
    IO,
}
