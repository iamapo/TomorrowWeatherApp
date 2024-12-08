package com.redred.tomorrowweatherapp.data.repository

import com.redred.tomorrowweatherapp.data.api.WeatherApiService
import com.redred.tomorrowweatherapp.data.model.weather.WeatherResponse
import com.redred.tomorrowweatherapp.domain.WeatherRepository

class WeatherRepositoryImpl(private val api: WeatherApiService) : WeatherRepository {

    override suspend fun getWeather(lat: Double, lon: Double): WeatherResponse {
        return api.getWeather(lat, lon)
    }
}


