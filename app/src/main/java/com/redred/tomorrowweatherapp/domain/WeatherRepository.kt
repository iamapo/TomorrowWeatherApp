package com.redred.tomorrowweatherapp.domain

import com.redred.tomorrowweatherapp.data.model.weather.WeatherResponse

interface WeatherRepository {
    suspend fun getWeather(lat: Double, lon: Double): WeatherResponse
}