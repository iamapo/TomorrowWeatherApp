package com.redred.tomorrowweatherapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.redred.tomorrowweatherapp.ui.util.WeatherIconUtils

@Composable
fun WeatherIcon(weatherCode: Int, isDay: Int) {
    val icon = WeatherIconUtils.getWeatherIcon(
        weatherCode = weatherCode,
        isDay = isDay
    )

    Icon(
        painter = icon,
        contentDescription = "Weather Icon",
        modifier = Modifier.size(100.dp),
        tint = MaterialTheme.colorScheme.primary
    )
}