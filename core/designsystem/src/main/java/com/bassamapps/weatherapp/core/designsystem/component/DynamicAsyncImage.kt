/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/14/23, 7:17 PM
 *
 */

package com.bassamapps.weatherapp.core.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import coil.compose.AsyncImage
import com.bassamapps.weatherapp.core.designsystem.theme.LocalTintTheme

/**
 * A wrapper around [AsyncImage] which determines the colorFilter based on the theme
 */
@Composable
fun DynamicAsyncImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: Painter? = null,
) {
    val iconTint = LocalTintTheme.current.iconTint
    AsyncImage(
        placeholder = placeholder,
        model = imageUrl,
        contentDescription = contentDescription,
        colorFilter = if (iconTint != null) ColorFilter.tint(iconTint) else null,
        modifier = modifier,
    )
}
