/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
 *
 */

package com.bassamapps.weatherapp.core.ui

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.DisposableEffectResult
import androidx.compose.runtime.DisposableEffectScope
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.platform.LocalView
import androidx.metrics.performance.PerformanceMetricsState
import androidx.metrics.performance.PerformanceMetricsState.Holder
import kotlinx.coroutines.CoroutineScope

/**
 * Remember metrics state holder
 *
 * @return
 */
@Composable
fun rememberMetricsStateHolder(): Holder {
    val localView = LocalView.current

    return remember(localView) {
        PerformanceMetricsState.getHolderForHierarchy(localView)
    }
}

/**
 * Track jank
 *
 * @param keys
 * @param reportMetric
 * @receiver
 */
@Composable
fun TrackJank(
    vararg keys: Any?,
    reportMetric: suspend CoroutineScope.(state: Holder) -> Unit,
) {
    val metrics = rememberMetricsStateHolder()
    LaunchedEffect(metrics, *keys) {
        reportMetric(metrics)
    }
}

/**
 * Track disposable jank
 *
 * @param keys
 * @param reportMetric
 * @receiver
 */
@Composable
fun TrackDisposableJank(
    vararg keys: Any?,
    reportMetric: DisposableEffectScope.(state: Holder) -> DisposableEffectResult,
) {
    val metrics = rememberMetricsStateHolder()
    DisposableEffect(metrics, *keys) {
        reportMetric(this, metrics)
    }
}

/**
 * Track scroll jank
 *
 * @param scrollableState
 * @param stateName
 */
@Composable
fun TrackScrollJank(scrollableState: ScrollableState, stateName: String) {
    TrackJank(scrollableState) { metricsHolder ->
        snapshotFlow { scrollableState.isScrollInProgress }.collect { isScrollInProgress ->
            metricsHolder.state?.apply {
                if (isScrollInProgress) {
                    putState(stateName, "Scrolling=true")
                } else {
                    removeState(stateName)
                }
            }
        }
    }
}
