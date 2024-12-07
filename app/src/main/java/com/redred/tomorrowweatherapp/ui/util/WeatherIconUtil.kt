package com.redred.tomorrowweatherapp.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.redred.tomorrowweatherapp.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object WeatherIconUtils {
    @Composable
    fun getWeatherIcon(weatherCode: Int, sunrise: String, sunset: String): Painter {
        val isNight = isNightTime(sunrise, sunset)
        return when (weatherCode) {
            0 -> if (isNight) painterResource(R.drawable.clear_night) else painterResource(R.drawable.clear_day)
            1, 2, 3 -> if (isNight) painterResource(R.drawable.cloudy_night) else painterResource(R.drawable.cloudy)
            45, 48 -> painterResource(R.drawable.fog)
            51, 53, 55 , 61, 63, 65, 80, 81, 82 -> painterResource(R.drawable.rain)
            71, 73, 75 , 77, 85, 86-> painterResource(R.drawable.snow)
            95 -> painterResource(R.drawable.thunderstorm)
            96, 99 -> painterResource(R.drawable.thunderstorm_hail)
            else -> if (isNight) painterResource(R.drawable.clear_night) else painterResource(R.drawable.clear_day)
        }
    }

    private fun isNightTime(sunrise: String, sunset: String): Boolean {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
        val now = LocalDateTime.now()
        val sunriseTime = LocalDateTime.parse(sunrise, formatter)
        val sunsetTime = LocalDateTime.parse(sunset, formatter)

        return now.isBefore(sunriseTime) || now.isAfter(sunsetTime)
    }
}