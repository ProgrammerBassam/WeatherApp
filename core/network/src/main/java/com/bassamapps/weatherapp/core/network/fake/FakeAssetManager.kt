/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/16/23, 1:13 AM
 *
 */

package com.bassamapps.weatherapp.core.network.fake

import java.io.InputStream

fun interface FakeAssetManager {
    fun open(fileName: String): InputStream
}