/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

@file:OptIn(ExperimentalMaterial3Api::class)

package com.bassamapps.weatherapp.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bassamapps.weatherapp.core.designsystem.icon.WeIcons

/**
 * We top app bar
 *
 * @param modifier
 * @param titleRes
 * @param titleString
 * @param navigationIcon
 * @param navigationIconContentDescription
 * @param actionIcon
 * @param actionIconContentDescription
 * @param colors
 * @param onNavigationClick
 * @param onActionClick
 * @receiver
 * @receiver
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeTopAppBar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    titleString: String? = null,
    navigationIcon: ImageVector,
    navigationIconContentDescription: String?,
    actionIcon: ImageVector,
    actionIconContentDescription: String?,
    colors: TopAppBarColors = TopAppBarDefaults.mediumTopAppBarColors(),
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
) {
    val titleText = titleString ?: stringResource(id = titleRes)
    TopAppBar(
        title = { Text(text = titleText) },

        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = navigationIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        actions = {
            IconButton(onClick = onActionClick) {
                Icon(
                    imageVector = actionIcon,
                    contentDescription = actionIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        colors = colors,
        modifier = modifier.testTag("WeTopAppBar"),
    )
}

/**
 * We top app bar
 *
 * @param navigationIcon
 * @param navigationIconContentDescription
 * @param colors
 * @param onNavigationClick
 * @receiver
 */
@Composable
fun WeTopAppBar(
    navigationIcon: ImageVector,
    navigationIconContentDescription: String?,
    colors: TopAppBarColors = TopAppBarDefaults.mediumTopAppBarColors(),
    onNavigationClick: () -> Unit = {},
) {

    TopAppBar(
        title = { Text(text = "") },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = navigationIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        colors = colors,
    )
}

/**
 * Top app bar with action, displayed on the right
 */


@OptIn(ExperimentalMaterial3Api::class)
@Preview("Top App Bar")
@Composable
private fun WeTopAppBarPreview() {
    WeTopAppBar(
        titleRes = android.R.string.untitled,
        navigationIcon = WeIcons.Search,
        navigationIconContentDescription = "Navigation icon",
        actionIcon = WeIcons.MoreVert,
        actionIconContentDescription = "Action icon",
    )
}
