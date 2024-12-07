package com.redred.tomorrowweatherapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.redred.tomorrowweatherapp.ui.util.WeatherIconUtils

@Composable
fun WeatherIcon(weatherCode: Int, sunrise: String, sunset: String) {
    val icon = WeatherIconUtils.getWeatherIcon(
        weatherCode = weatherCode,
        sunrise = sunrise,
        sunset = sunset
    )

    Icon(
        painter = icon,
        contentDescription = "Weather Icon",
        modifier = Modifier.size(100.dp),
        tint = MaterialTheme.colorScheme.primary
    )
}