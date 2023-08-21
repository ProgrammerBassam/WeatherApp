/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.core.network.fake

import java.io.InputStream

/**
 * Fake asset manager
 *
 * @constructor Create empty Fake asset manager
 */
fun interface FakeAssetManager {
    /**
     * Open
     *
     * @param fileName
     * @return
     */
    fun open(fileName: String): InputStream
}