package com.redred.tomorrowweatherapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redred.tomorrowweatherapp.data.model.location.Location
import com.redred.tomorrowweatherapp.data.model.weather.WeatherResponse
import com.redred.tomorrowweatherapp.data.repository.WeatherRepository
import com.redred.tomorrowweatherapp.domain.constants.Locations
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState: StateFlow<WeatherState> = _weatherState.asStateFlow()

    private val _progressState = MutableStateFlow(0f)
    val progressState: StateFlow<Float> = _progressState.asStateFlow()

    private var currentLocationIndex = 0
    private var locationJob: Job? = null

    init {
        startLocationCycle()
    }

    private fun startLocationCycle() {
        locationJob?.cancel()
        locationJob = viewModelScope.launch {
            while (isActive) {
                val location = Locations.cities[currentLocationIndex]
                fetchWeather(location)

                val totalDuration = 10000L
                val updateInterval = 16L
                val steps = (totalDuration / updateInterval).toInt()

                for (i in 0..steps) {
                    _progressState.value = i.toFloat() / steps
                    delay(updateInterval)
                }

                currentLocationIndex = (currentLocationIndex + 1) % Locations.cities.size
            }
        }
    }

    private suspend fun fetchWeather(location: Location) {
        if (!isValidCoordinates(location.lat, location.lon)) {
            _weatherState.value = WeatherState.Error(
                message = "Ungültige Koordinaten: lat=${location.lat}, lon=${location.lon}",
                cityName = location.name
            )
            return
        }

        try {
            _weatherState.value = WeatherState.Loading
            val response = repository.getWeather(location.lat, location.lon)
            _weatherState.value = WeatherState.Success(
                data = response,
                cityName = location.name
            )
        } catch (e: Exception) {
            _weatherState.value = WeatherState.Error(
                message = e.message ?: "Ein Fehler ist aufgetreten",
                cityName = location.name
            )
        }

    }

    private fun isValidCoordinates(lat: Double, lon: Double): Boolean {
        return lat in -90.0..90.0 && lon in -180.0..180.0
    }

    override fun onCleared() {
        super.onCleared()
        locationJob?.cancel()
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