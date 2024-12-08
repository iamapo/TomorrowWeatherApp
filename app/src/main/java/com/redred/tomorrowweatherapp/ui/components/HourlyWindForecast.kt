package com.redred.tomorrowweatherapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.redred.tomorrowweatherapp.R
import com.redred.tomorrowweatherapp.data.model.weather.HourlyWeather
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun HourlyWindForecast(hourlyWeather: HourlyWeather) {

    val currentTime = System.currentTimeMillis()
    val dateFormat = remember { DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.getDefault()) }
    val timeFormat = remember { DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault()) }

    val parsedTimes = remember(hourlyWeather) {
        hourlyWeather.time.map { timeString ->
            try {
                LocalDateTime.parse(timeString, dateFormat).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
            } catch (e: Exception) {
                0L
            }
        }
    }

    val currentHourIndex = parsedTimes.indexOfFirst { it >= currentTime }
    val startIndex = if (currentHourIndex != -1) currentHourIndex else 0
    val endIndex = (startIndex + 6).coerceAtMost(hourlyWeather.time.size)

    LazyColumn {
        item { ForecastHeader() }
        itemsIndexed(hourlyWeather.time.subList(startIndex, endIndex)) { index, timeString ->
            val actualIndex = startIndex + index
            ForecastCard(
                time = timeString,
                windspeed = hourlyWeather.windspeed_10m[actualIndex],
                dateFormat = dateFormat,
                timeFormat = timeFormat
            )
        }
    }
}

@Composable
fun ForecastHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(stringResource(id = R.string.hourly_wind_forecast), style = MaterialTheme.typography.headlineSmall)
    }
}

@Composable
fun ForecastCard(time: String, windspeed: Double, dateFormat: DateTimeFormatter, timeFormat: DateTimeFormatter) {
    val formattedTime = formatTime(time, dateFormat, timeFormat)
    Card(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "$formattedTime ${stringResource(id = R.string.time_suffix)}", style = MaterialTheme.typography.bodyMedium)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.wind),
                    modifier = Modifier.size(24.dp),
                    contentDescription = stringResource(id = R.string.wind)
                )
                Text(text = "$windspeed km/h", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

fun formatTime(timeString: String, dateFormat: DateTimeFormatter, timeFormat: DateTimeFormatter): String {
    return try {
        val parsedTime = LocalDateTime.parse(timeString, dateFormat)
        timeFormat.format(parsedTime)
    } catch (e: Exception) {
        "00:00"
    }
}