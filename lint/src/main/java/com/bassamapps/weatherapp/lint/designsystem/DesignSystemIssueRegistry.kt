/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/14/23, 7:17 PM
 *
 */

package com.bassamapps.weatherapp.lint.designsystem

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API

/**
 * An issue registry that checks for incorrect usages of Compose Material APIs over equivalents in
 * the Weather App design system module.
 */
class DesignSystemIssueRegistry : IssueRegistry() {
    override val issues = listOf(DesignSystemDetector.ISSUE)

    override val api: Int = CURRENT_API

    override val minApi: Int = 12

    override val vendor: Vendor = Vendor(
        vendorName = "Weather App",
        feedbackUrl = "https://github.com/programmerBassam/weatherapp/issues",
        contact = "https://github.com/programmerBassam/weatherapp",
    )
}
