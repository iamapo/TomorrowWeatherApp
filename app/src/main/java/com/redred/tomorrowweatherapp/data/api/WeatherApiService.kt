package com.redred.tomorrowweatherapp.data.api

import com.redred.tomorrowweatherapp.data.model.weather.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("current_weather") currentWeather: Boolean = true,
        @Query("hourly") hourly: String = "windspeed_10m",
    ): WeatherResponse
}