/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/18/23, 11:34 PM
 *
 */

@file:OptIn(ExperimentalMaterial3Api::class)

package com.bassamapps.weatherapp.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.SmallTopAppBar
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeTopAppBar(
    @StringRes titleRes: Int,
    titleString: String? = null,
    navigationIcon: ImageVector,
    navigationIconContentDescription: String?,
    actionIcon: ImageVector,
    actionIconContentDescription: String?,
    modifier: Modifier = Modifier,
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
