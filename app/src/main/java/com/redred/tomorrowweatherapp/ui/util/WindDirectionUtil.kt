package com.redred.tomorrowweatherapp.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.redred.tomorrowweatherapp.R

object WindDirectionUtils {

    fun getWindDirectionResId(degrees: Double): Int {
        return when {
            degrees < 0 || degrees >= 360 -> R.string.invalid_direction
            degrees < 22.5 -> R.string.north
            degrees < 67.5 -> R.string.northeast
            degrees < 112.5 -> R.string.east
            degrees < 157.5 -> R.string.southeast
            degrees < 202.5 -> R.string.south
            degrees < 247.5 -> R.string.southwest
            degrees < 292.5 -> R.string.west
            degrees < 337.5 -> R.string.northwest
            else -> R.string.north
        }
    }

    @Composable
    fun getWindDirection(degrees: Double): String {
        val resId = getWindDirectionResId(degrees)
        return stringResource(id = resId)
    }
}
