/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/18/23, 10:13 PM
 *
 */

package com.bassamapps.weatherapp.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.TextButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WeTopicTag(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
) {
    Box(modifier = modifier) {
        val containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(
            alpha = WeTagDefaults.UnfollowedTopicTagContainerAlpha,
        )

        TextButton(
            onClick = onClick,
            enabled = enabled,
            colors = ButtonDefaults.textButtonColors(
                containerColor = containerColor,
                contentColor = contentColorFor(backgroundColor = containerColor),
                disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = WeTagDefaults.DisabledTopicTagContainerAlpha,
                ),
            ),
        ) {
            ProvideTextStyle(value = MaterialTheme.typography.labelSmall) {
                text()
            }
        }
    }
}

/**
 * Weather App tag default values.
 */
object WeTagDefaults {
    const val UnfollowedTopicTagContainerAlpha = 0.5f

    // TODO: File bug
    // Button disabled container alpha value not exposed by ButtonDefaults
    const val DisabledTopicTagContainerAlpha = 0.12f
}
