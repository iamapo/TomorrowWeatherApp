package com.redred.tomorrowweatherapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.redred.tomorrowweatherapp.R
import com.redred.tomorrowweatherapp.ui.viewmodel.WeatherState

@Composable
fun HourlyWindForecast(weather: WeatherState.Success) {

    LazyColumn {
        items(weather.data.hourly.time) { timeString ->
            ForecastCard(
                time = timeString,
                windspeed = weather.data.hourly.windspeed_10m[weather.data.hourly.time.indexOf(timeString)]
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
fun ForecastCard(time: String, windspeed: Double) {

    Card(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "$time ${stringResource(id = R.string.time_suffix)}", style = MaterialTheme.typography.bodyMedium)
            Row(
                modifier = Modifier.background(color = Color.Gray),
                verticalAlignment = Alignment.CenterVertically
            ) {
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
