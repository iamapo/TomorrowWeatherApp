package com.redred.tomorrowweatherapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun HourlyWindForecast(hourlyWeather: HourlyWeather) {

    val currentTime = System.currentTimeMillis()
    val dateFormat = remember { SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault()) }
    val timeFormat = remember { SimpleDateFormat("HH:mm", Locale.getDefault()) }

    val currentHourIndex = hourlyWeather.time.indexOfFirst { time ->
        val timeInMillis = try {
            dateFormat.parse(time)?.time ?: 0L
        } catch (e: ParseException) {
            0L
        }
        timeInMillis >= currentTime
    }

    val startIndex = if (currentHourIndex != -1) currentHourIndex else 0
    val endIndex = (startIndex + 6).coerceAtMost(hourlyWeather.time.size)

    LazyColumn {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(id = R.string.hourly_wind_forecast), style = MaterialTheme.typography.headlineSmall)
            }
        }
        items(endIndex - startIndex) { index ->
            val actualIndex = startIndex + index
            val formattedTime = formatTime(hourlyWeather.time[actualIndex], dateFormat, timeFormat)
            Card(modifier = Modifier.padding(8.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "${formattedTime} ${stringResource(id = R.string.time_suffix)}", style = MaterialTheme.typography.bodyMedium)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.wind),
                            modifier = Modifier.size(24.dp),
                            contentDescription = stringResource(id = R.string.wind)
                        )
                        Text(text = "${hourlyWeather.windspeed_10m[actualIndex]} km/h", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

fun formatTime(timeString: String, dateFormat: SimpleDateFormat, timeFormat: SimpleDateFormat): String? {
    return dateFormat.parse(timeString)?.let { timeFormat.format(it) }
}