package com.redred.tomorrowweatherapp.ui.viewmodel

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
}

sealed class WeatherState {
    object Loading : WeatherState()
    data class Success(
        val data: WeatherResponse,
        val cityName: String
    ) : WeatherState()

    data class Error(
        val message: String,
        val cityName: String
    ) : WeatherState()
}