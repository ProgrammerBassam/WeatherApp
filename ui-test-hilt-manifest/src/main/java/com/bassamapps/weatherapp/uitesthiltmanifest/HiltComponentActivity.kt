/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.uitesthiltmanifest

import androidx.activity.ComponentActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * A [ComponentActivity] annotated with [AndroidEntryPoint] for use in tests, as a workaround
 * for https://github.com/google/dagger/issues/3394
 */
@AndroidEntryPoint
class HiltComponentActivity : ComponentActivity()