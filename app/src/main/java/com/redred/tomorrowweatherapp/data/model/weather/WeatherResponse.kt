package com.redred.tomorrowweatherapp.data.model.weather

data class WeatherResponse(
    val current_weather: CurrentWeather,
    val hourly: HourlyWeather
)

data class CurrentWeather(
    val temperature: Double,
    val weathercode: Int,
    val windspeed: Double,
    val winddirection: Double,
    val is_day: Int
)

data class HourlyWeather(
    val time: List<String>,
    val windspeed_10m: List<Double>
)
