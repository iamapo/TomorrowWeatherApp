package com.redred.tomorrowweatherapp.ui.util

import com.redred.tomorrowweatherapp.R
import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherIconUtilsTest {

    @Test
    fun `test clear day weather code 0 during the day`() {
        val result = WeatherIconUtils.getWeatherIconResId(weatherCode = 0, isDay = 1)
        assertEquals(R.drawable.clear_day, result)
    }

    @Test
    fun `test clear night weather code 0 during the night`() {
        val result = WeatherIconUtils.getWeatherIconResId(weatherCode = 0, isDay = 0)
        assertEquals(R.drawable.clear_night, result)
    }

    @Test
    fun `test cloudy weather code 1 during the day`() {
        val result = WeatherIconUtils.getWeatherIconResId(weatherCode = 1, isDay = 1)
        assertEquals(R.drawable.cloudy, result)
    }

    @Test
    fun `test cloudy weather code 1 during the night`() {
        val result = WeatherIconUtils.getWeatherIconResId(weatherCode = 1, isDay = 0)
        assertEquals(R.drawable.cloudy_night, result)
    }
}