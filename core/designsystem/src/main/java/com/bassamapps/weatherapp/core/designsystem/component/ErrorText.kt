/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.core.designsystem.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * We error text
 *
 * @param errorMessage
 * @param modifier
 */
@Composable
fun WeErrorText(
     errorMessage: String,
     modifier: Modifier = Modifier
) {
    Text(
        text = errorMessage,
        modifier = modifier.padding(16.dp)
    )
}