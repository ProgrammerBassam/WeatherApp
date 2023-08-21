/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/21/23, 6:50 PM
 *
 */

package com.bassamapps.weatherapp.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bassamapps.weatherapp.core.designsystem.component.WeButton
import com.bassamapps.weatherapp.core.designsystem.icon.WeIcons

/**
 * Error type
 *
 * @property icon
 * @property errorMessageResource
 * @property errorMessage
 * @property buttonText
 * @property onReloadClick
 * @constructor Create empty Error type
 */
enum class ErrorType(
    val icon: ImageVector,
    val errorMessageResource: Int?,
    var errorMessage: String?,
    val buttonText: Int,
    var onReloadClick: () -> Unit
) {

    /**
     * Non Error
     *
     * @constructor Create empty Non Error
     */
    NON_ERROR(
        icon = WeIcons.NoInternet,
        errorMessageResource = R.string.not_connected,
        errorMessage = null,
        buttonText = R.string.reload,
        onReloadClick = { /* Handle network error reload */ }
    ),

    /**
     * Exception Error
     *
     * @constructor Create empty Exception Error
     */
    EXCEPTION_ERROR(
        icon = WeIcons.Error,
        errorMessageResource = null,
        errorMessage = "",
        buttonText = R.string.reload,
        onReloadClick = { /* Handle network error reload */ }
    ),

    /**
     * Api Error
     *
     * @constructor Create empty Api Error
     */
    API_ERROR(
        icon = WeIcons.Error,
        errorMessageResource = null,
        errorMessage = "",
        buttonText = R.string.reload,
        onReloadClick = { /* Handle network error reload */ }
    ),
}

/**
 * Error screen
 *
 * @param message
 * @param modifier
 * @param onClickRefresh
 * @receiver
 */
@Composable
fun ErrorScreen(
    message:String,
    modifier: Modifier = Modifier,
    onClickRefresh:()->Unit
) {
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = WeIcons.Error,
            contentDescription = null, // Provide appropriate content description
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        WeButton(
            onClick =onClickRefresh,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.reload))
        }
    }
}
