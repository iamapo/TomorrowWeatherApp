package com.redred.tomorrowweatherapp.data.model.weather

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current_weather")
    val current_weather: CurrentWeather,
    @SerializedName("hourly")
    var hourly: HourlyWeather
)

data class CurrentWeather(
    @SerializedName("temperature")
    val temperature: Double,
    @SerializedName("weathercode")
    val weathercode: Int,
    @SerializedName("windspeed")
    val windspeed: Double,
    @SerializedName("winddirection")
    val winddirection: Double,
    @SerializedName("is_day")
    val is_day: Int
)

data class HourlyWeather(
    @SerializedName("time")
    var time: List<String>,
    @SerializedName("windspeed_10m")
    val windspeed_10m: List<Double>,
    val highlighted: List<Boolean>
)
