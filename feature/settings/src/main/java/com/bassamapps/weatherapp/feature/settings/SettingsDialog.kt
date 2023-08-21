/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/14/23, 7:17 PM
 *
 */

package com.bassamapps.weatherapp.feature.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bassamapps.weatherapp.core.designsystem.theme.WeTheme
import com.bassamapps.weatherapp.core.designsystem.theme.supportsDynamicTheming
import com.bassamapps.weatherapp.core.model.data.DarkThemeConfig
import com.bassamapps.weatherapp.core.model.data.DarkThemeConfig.DARK
import com.bassamapps.weatherapp.core.model.data.DarkThemeConfig.FOLLOW_SYSTEM
import com.bassamapps.weatherapp.core.model.data.DarkThemeConfig.LIGHT
import com.bassamapps.weatherapp.core.model.data.ThemeBrand
import com.bassamapps.weatherapp.core.model.data.ThemeBrand.ANDROID
import com.bassamapps.weatherapp.core.model.data.ThemeBrand.DEFAULT
import com.bassamapps.weatherapp.core.ui.TrackScreenViewEvent
import com.bassamapps.weatherapp.feature.settings.R.string
import com.bassamapps.weatherapp.feature.settings.SettingsUiState.Loading
import com.bassamapps.weatherapp.feature.settings.SettingsUiState.Success

@Composable
fun SettingsDialog(
    onDismiss: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val settingsUiState by viewModel.settingsUiState.collectAsStateWithLifecycle()
    SettingsDialog(
        onDismiss = onDismiss,
        settingsUiState = settingsUiState,
        onChangeThemeBrand = viewModel::updateThemeBrand,
        onChangeDynamicColorPreference = viewModel::updateDynamicColorPreference,
        onChangeDarkThemeConfig = viewModel::updateDarkThemeConfig,
        onChangeTempType = viewModel::updateTempType
    )
}

@Composable
fun SettingsDialog(
    settingsUiState: SettingsUiState,
    supportDynamicColor: Boolean = supportsDynamicTheming(),
    onDismiss: () -> Unit,
    onChangeThemeBrand: (themeBrand: ThemeBrand) -> Unit,
    onChangeDynamicColorPreference: (useDynamicColor: Boolean) -> Unit,
    onChangeDarkThemeConfig: (darkThemeConfig: DarkThemeConfig) -> Unit,
    onChangeTempType: (newValue: Boolean) -> Unit,
) {
    val configuration = LocalConfiguration.current

    /**
     * usePlatformDefaultWidth = false is use as a temporary fix to allow
     * height recalculation during recomposition. This, however, causes
     * Dialog's to occupy full width in Compact mode. Therefore max width
     * is configured below. This should be removed when there's fix to
     * https://issuetracker.google.com/issues/221643630
     */
    AlertDialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier.widthIn(max = configuration.screenWidthDp.dp - 80.dp),
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = stringResource(string.settings_title),
                style = MaterialTheme.typography.titleLarge,
            )
        },
        text = {
            Divider()
            Column(Modifier.verticalScroll(rememberScrollState())) {
                when (settingsUiState) {
                    Loading -> {
                        Text(
                            text = stringResource(string.loading),
                            modifier = Modifier.padding(vertical = 16.dp),
                        )
                    }

                    is Success -> {
                        SettingsPanel(
                            settings = settingsUiState.settings,
                            supportDynamicColor = supportDynamicColor,
                            onChangeThemeBrand = onChangeThemeBrand,
                            onChangeDynamicColorPreference = onChangeDynamicColorPreference,
                            onChangeDarkThemeConfig = onChangeDarkThemeConfig,
                            onChangeTempType = onChangeTempType,
                        )
                    }
                }
            }
            TrackScreenViewEvent(screenName = "Settings")
        },
        confirmButton = {
            Text(
                text = stringResource(string.dismiss_dialog_button_text),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable { onDismiss() },
            )
        },
    )
}

// [ColumnScope] is used for using the [ColumnScope.AnimatedVisibility] extension overload composable.
@Composable
private fun ColumnScope.SettingsPanel(
    settings: UserEditableSettings,
    supportDynamicColor: Boolean,
    onChangeThemeBrand: (themeBrand: ThemeBrand) -> Unit,
    onChangeDynamicColorPreference: (useDynamicColor: Boolean) -> Unit,
    onChangeDarkThemeConfig: (darkThemeConfig: DarkThemeConfig) -> Unit,
    onChangeTempType: (newValue: Boolean) -> Unit,
) {
    SettingsDialogSectionTitle(text = stringResource(string.theme))
    Column(Modifier.selectableGroup()) {
        SettingsDialogThemeChooserRow(
            text = stringResource(string.brand_default),
            selected = settings.brand == DEFAULT,
            onClick = { onChangeThemeBrand(DEFAULT) },
        )
        SettingsDialogThemeChooserRow(
            text = stringResource(string.brand_android),
            selected = settings.brand == ANDROID,
            onClick = { onChangeThemeBrand(ANDROID) },
        )
    }
    AnimatedVisibility(visible = settings.brand == DEFAULT && supportDynamicColor) {
        Column {
            SettingsDialogSectionTitle(text = stringResource(string.dynamic_color_preference))
            Column(Modifier.selectableGroup()) {
                SettingsDialogThemeChooserRow(
                    text = stringResource(string.dynamic_color_yes),
                    selected = settings.useDynamicColor,
                    onClick = { onChangeDynamicColorPreference(true) },
                )
                SettingsDialogThemeChooserRow(
                    text = stringResource(string.dynamic_color_no),
                    selected = !settings.useDynamicColor,
                    onClick = { onChangeDynamicColorPreference(false) },
                )
            }
        }
    }
    SettingsDialogSectionTitle(text = stringResource(string.dark_mode_preference))
    Column(Modifier.selectableGroup()) {
        SettingsDialogThemeChooserRow(
            text = stringResource(string.dark_mode_config_system_default),
            selected = settings.darkThemeConfig == FOLLOW_SYSTEM,
            onClick = { onChangeDarkThemeConfig(FOLLOW_SYSTEM) },
        )
        SettingsDialogThemeChooserRow(
            text = stringResource(string.dark_mode_config_light),
            selected = settings.darkThemeConfig == LIGHT,
            onClick = { onChangeDarkThemeConfig(LIGHT) },
        )
        SettingsDialogThemeChooserRow(
            text = stringResource(string.dark_mode_config_dark),
            selected = settings.darkThemeConfig == DARK,
            onClick = { onChangeDarkThemeConfig(DARK) },
        )
    }
    SettingsDialogSectionTitle(text = stringResource(string.temp_type))
    Column(Modifier.selectableGroup()) {
        SettingsDialogThemeChooserRow(
            text = stringResource(string.temp_celsius),
            selected = settings.isTempC,
            onClick = { onChangeTempType(true) },
        )
        SettingsDialogThemeChooserRow(
            text = stringResource(string.temp_fahrenheit),
            selected = !settings.isTempC,
            onClick = { onChangeTempType(false) },
        )
    }
}

@Composable
private fun SettingsDialogSectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
    )
}

@Composable
fun SettingsDialogThemeChooserRow(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                role = Role.RadioButton,
                onClick = onClick,
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selected,
            onClick = null,
        )
        Spacer(Modifier.width(8.dp))
        Text(text)
    }
}

@Preview
@Composable
private fun PreviewSettingsDialog() {
    WeTheme {
        SettingsDialog(
            onDismiss = {},
            settingsUiState = Success(
                UserEditableSettings(
                    brand = DEFAULT,
                    darkThemeConfig = FOLLOW_SYSTEM,
                    useDynamicColor = false,
                    isTempC = true,
                ),
            ),
            onChangeThemeBrand = {},
            onChangeDynamicColorPreference = {},
            onChangeDarkThemeConfig = {},
            onChangeTempType = {}
        )
    }
}

@Preview
@Composable
private fun PreviewSettingsDialogLoading() {
    WeTheme {
        SettingsDialog(
            onDismiss = {},
            settingsUiState = Loading,
            onChangeThemeBrand = {},
            onChangeDynamicColorPreference = {},
            onChangeDarkThemeConfig = {},
            onChangeTempType = {}
        )
    }
}
