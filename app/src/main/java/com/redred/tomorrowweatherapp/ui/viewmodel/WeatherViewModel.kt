package com.redred.tomorrowweatherapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redred.tomorrowweatherapp.data.model.location.Location
import com.redred.tomorrowweatherapp.data.model.weather.WeatherResponse
import com.redred.tomorrowweatherapp.domain.LocationRepository
import com.redred.tomorrowweatherapp.domain.WeatherRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState: StateFlow<WeatherState> = _weatherState.asStateFlow()

    private val _progressState = MutableStateFlow(0f)
    val progressState: StateFlow<Float> = _progressState.asStateFlow()

    init {
        startLocationCycle()
    }

    private fun startLocationCycle() {
        viewModelScope.launch {
            while (isActive) {
                for (location in locationRepository.getCities()) {

                    Log.d("WeatherViewModel", "Fetching weather for location: ${location.name}")

                    fetchWeather(location)

                    val totalDuration = 10_000L
                    val updateInterval = 16L
                    val steps = (totalDuration / updateInterval).toInt()

                    for (i in 0..steps) {
                        _progressState.emit(i.toFloat() / steps)
                        delay(updateInterval)
                    }
                }
            }
        }
    }

    private suspend fun fetchWeather(location: Location) {
        try {
            _weatherState.emit(WeatherState.Loading)
            val response: WeatherResponse = weatherRepository.getWeather(location.lat, location.lon)

            val hourlyWeather = response.hourly

            val currentTime = System.currentTimeMillis()
            val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
            val timeFormat = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())

            val parsedTimes = hourlyWeather.time.map { timeString ->
                try {
                    LocalDateTime.parse(timeString, dateFormat).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                } catch (e: Exception) {
                    0L
                }
            }

            val currentHourIndex = parsedTimes.indexOfFirst { it >= currentTime }
            val startIndex = if (currentHourIndex != -1) currentHourIndex else 0
            val endIndex = (startIndex + 6).coerceAtMost(hourlyWeather.time.size)

            response.hourly.time = hourlyWeather.time.subList(startIndex, endIndex).map { timeString ->
                formatTime(timeString, dateFormat, timeFormat)
            }

            _weatherState.emit(
                WeatherState.Success(
                    data = response,
                    cityName = location.name
                )
            )
        } catch (e: Exception) {
            _weatherState.emit(
                WeatherState.Loading
            )
        }
    }

    private fun formatTime(timeString: String, dateFormat: DateTimeFormatter, timeFormat: DateTimeFormatter): String {
        return try {
            val parsedTime = LocalDateTime.parse(timeString, dateFormat)
            timeFormat.format(parsedTime)
        } catch (e: Exception) {
            "00:00"
        }
    }
}

sealed class WeatherState {
    data object Loading : WeatherState()
    data class Success(
        val data: WeatherResponse,
        val cityName: String
    ) : WeatherState()

    data class Error(
        val message: String,
        val cityName: String
    ) : WeatherState()
}