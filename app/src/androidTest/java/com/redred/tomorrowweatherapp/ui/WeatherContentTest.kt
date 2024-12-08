package com.redred.tomorrowweatherapp.ui

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.redred.tomorrowweatherapp.data.model.weather.CurrentWeather
import com.redred.tomorrowweatherapp.data.model.weather.HourlyWeather
import com.redred.tomorrowweatherapp.data.model.weather.WeatherResponse
import com.redred.tomorrowweatherapp.ui.components.WeatherContent
import com.redred.tomorrowweatherapp.ui.viewmodel.WeatherState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_weatherContent_displays_cityName() {

        val weather = createMockWeatherState()

        composeTestRule.setContent {
            WeatherContent(weather)
        }

        composeTestRule.onNodeWithText(weather.cityName).assertExists().assertIsDisplayed()
    }

    @Test
    fun test_weatherContent_displays_temperature() {
        val weather = createMockWeatherState()

        composeTestRule.setContent {
            WeatherContent(weather)
        }

        val expectedTemperature = "${weather.data.current_weather.temperature}°C"
        composeTestRule.onNodeWithText(expectedTemperature).assertExists().assertIsDisplayed()
    }

    @Test
    fun test_weatherContent_displays_wind_speed() {
        val weather = createMockWeatherState()

        composeTestRule.setContent {
            WeatherContent(weather)
        }

        val expectedWindSpeed = "${weather.data.current_weather.windspeed} km/h"
        composeTestRule.onNodeWithText(expectedWindSpeed).assertExists().assertIsDisplayed()
    }

    @Test
    fun test_weatherContent_displays_wind_direction() {
        val weather = createMockWeatherState()

        composeTestRule.setContent {
            WeatherContent(weather)
        }

        val expectedWindDirection = "North"
        composeTestRule.onNodeWithText(expectedWindDirection).assertExists().assertIsDisplayed()
    }

    @Test
    fun test_weatherContent_displays_weather_icon() {
        val weather = createMockWeatherState()

        composeTestRule.setContent {
            WeatherContent(weather)
        }

        composeTestRule.onAllNodesWithContentDescription("Weather Icon").assertCountEquals(1)
    }

    private fun createMockWeatherState(): WeatherState.Success {
        return WeatherState.Success(
            cityName = "Berlin",
            data = WeatherResponse(
                current_weather = CurrentWeather(
                    temperature = 23.0,
                    weathercode = 0,
                    is_day = 1,
                    windspeed = 15.0,
                    winddirection = 0.0
                ),
                hourly = HourlyWeather(
                    time = listOf("2023-08-20T10:00", "2023-08-20T11:00"),
                    windspeed_10m = listOf(12.0, 14.0)
                )
            )
        )
    }
}