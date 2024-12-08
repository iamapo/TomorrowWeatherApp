package com.redred.tomorrowweatherapp.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.redred.tomorrowweatherapp.R

object WeatherIconUtils {

    fun getWeatherIconResId(weatherCode: Int, isDay: Int): Int {
        val isDaytime = isDay == 1
        return when (weatherCode) {
            0 -> if (isDaytime) R.drawable.clear_day else R.drawable.clear_night
            1, 2, 3 -> if (isDaytime) R.drawable.cloudy else R.drawable.cloudy_night
            45, 48 -> R.drawable.fog
            51, 53, 55, 61, 63, 65, 80, 81, 82 -> R.drawable.rain
            71, 73, 75, 77, 85, 86 -> R.drawable.snow
            95 -> R.drawable.thunderstorm
            96, 99 -> R.drawable.thunderstorm_hail
            else -> if (isDaytime) R.drawable.clear_day else R.drawable.clear_night
        }
    }

    @Composable
    fun getWeatherIcon(weatherCode: Int, isDay: Int): Painter {
        val resId = getWeatherIconResId(weatherCode, isDay)
        return painterResource(id = resId)
    }
}