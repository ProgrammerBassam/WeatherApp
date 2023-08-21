/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/18/23, 10:03 PM
 *
 */

package com.bassamapps.weatherapp.core.designsystem.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bassamapps.weatherapp.core.designsystem.icon.WeIcons

/**
 * Weather App suggestion chip with included leading checked icon as well as text content slot.
 *
 * @param modifier Modifier to be applied to the chip.
 * clickable and will appear disabled to accessibility services.
 * @param label The text label content.
 */
@Composable
fun WeSuggestionChip(
    modifier: Modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
    label: String,
) {
    SuggestionChip(
        onClick = { /*TODO*/ },
        label = {
            Text(text = label)
        },
        modifier = modifier,
    )

}

/**
 * Weather App chip default values.
 */
object WeChipDefaults {
    // TODO: File bug
    // FilterChip default values aren't exposed via FilterChipDefaults
    const val DisabledChipContainerAlpha = 0.12f
    const val DisabledChipContentAlpha = 0.38f
    val ChipBorderWidth = 1.dp
}
