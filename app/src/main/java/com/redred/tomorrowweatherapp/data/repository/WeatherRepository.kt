package com.redred.tomorrowweatherapp.data.repository

import android.util.Log
import com.redred.tomorrowweatherapp.data.api.WeatherApiService
import com.redred.tomorrowweatherapp.data.model.weather.WeatherResponse

class WeatherRepository(private val api: WeatherApiService) {

    suspend fun getWeather(lat: Double, lon: Double): WeatherResponse {
        val response = api.getWeather(lat, lon)
        Log.d("WeatherResponse", response.toString())
        return response
    }
}
