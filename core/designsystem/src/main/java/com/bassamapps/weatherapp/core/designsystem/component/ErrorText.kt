/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/18/23, 9:39 PM
 *
 */

package com.bassamapps.weatherapp.core.designsystem.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WeErrorText(
     errorMessage: String,
     modifier: Modifier = Modifier.padding(16.dp)
) {
    Text(
        text = errorMessage,
        modifier = modifier
    )
}