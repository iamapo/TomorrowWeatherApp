package com.redred.tomorrowweatherapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.redred.tomorrowweatherapp.R
import com.redred.tomorrowweatherapp.ui.util.WindDirectionUtils
import com.redred.tomorrowweatherapp.ui.viewmodel.WeatherState

@Composable
fun WeatherContent(weather: WeatherState.Success) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = weather.cityName,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "${weather.data.current_weather.temperature}°C",
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        WeatherIcon(
            weatherCode = weather.data.current_weather.weathercode,
            isDay = weather.data.current_weather.is_day
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.wind),
                modifier = Modifier.size(24.dp),
                contentDescription = "Wind"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${weather.data.current_weather.windspeed} km/h",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.wind_direction),
                modifier = Modifier.size(24.dp),
                contentDescription = "Wind"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = WindDirectionUtils.getWindDirection(weather.data.current_weather.winddirection),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        HourlyWindForecast(weather.data.hourly)
    }
}