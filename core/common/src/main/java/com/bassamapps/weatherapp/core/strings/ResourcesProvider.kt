/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.core.strings

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Resources provider
 *
 * @property context
 * @constructor Create empty Resources provider
 */
@Singleton
class ResourcesProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {
    /**
     * Get string
     *
     * @param stringResId
     * @return
     */
    fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }
}