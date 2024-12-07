package com.redred.tomorrowweatherapp.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.redred.tomorrowweatherapp.R

object WindDirectionUtils {
    @Composable
    fun getWindDirection(degrees: Double): String {
        return when {
            degrees < 0 || degrees >= 360 -> stringResource(R.string.invalid_direction)
            degrees < 22.5 -> stringResource(R.string.north)
            degrees < 67.5 -> stringResource(R.string.northeast)
            degrees < 112.5 -> stringResource(R.string.east)
            degrees < 157.5 -> stringResource(R.string.southeast)
            degrees < 202.5 -> stringResource(R.string.south)
            degrees < 247.5 -> stringResource(R.string.southwest)
            degrees < 292.5 -> stringResource(R.string.west)
            degrees < 337.5 -> stringResource(R.string.northwest)
            else -> stringResource(R.string.north)
        }
    }
}
