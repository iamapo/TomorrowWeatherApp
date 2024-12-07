package com.redred.tomorrowweatherapp.data.model.weather

data class WeatherResponse(
    val current_weather: CurrentWeather,
    val daily: DailyWeather,
    val hourly: HourlyWeather
)

data class CurrentWeather(
    val temperature: Double,
    val weathercode: Int,
    val windspeed: Double,
    val winddirection: Double
)

data class DailyWeather(
    val time: List<String>,
    val sunrise: List<String>,
    val sunset: List<String>
)

data class HourlyWeather(
    val time: List<String>,
    val windspeed_10m: List<Double>
)
