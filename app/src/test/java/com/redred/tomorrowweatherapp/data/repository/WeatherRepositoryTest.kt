package com.redred.tomorrowweatherapp.data.repository

import com.redred.tomorrowweatherapp.data.api.WeatherApiService
import com.redred.tomorrowweatherapp.data.model.weather.CurrentWeather
import com.redred.tomorrowweatherapp.data.model.weather.DailyWeather
import com.redred.tomorrowweatherapp.data.model.weather.HourlyWeather
import com.redred.tomorrowweatherapp.data.model.weather.WeatherResponse
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith

class WeatherRepositoryTest {

    private lateinit var api: WeatherApiService
    private lateinit var repository: WeatherRepository

    @Before
    fun setup() {
        api = mockk()
        repository = WeatherRepository(api)
    }

    @Test
    fun `getWeather returns weather data successfully`() = runTest {
        val expectedResponse = WeatherResponse(
            current_weather = CurrentWeather(
                temperature = 20.5,
                weathercode = 0,
                windspeed = 5.0,
                winddirection = 0.0
            ),
            daily = DailyWeather(
                time = listOf("2023-05-01"),
                sunrise = listOf("06:33"),
                sunset = listOf("18:33")
            ),
            hourly = HourlyWeather(
                time = listOf("2023-05-01T12:00"),
                windspeed_10m = listOf(5.0)
            )
        )

        coEvery {
            api.getWeather(
                lat = 52.52,
                lon = 13.41,
                currentWeather = any()
            )
        } returns expectedResponse

        val result = repository.getWeather(52.52, 13.41)

        assertEquals(expectedResponse, result)
        assertEquals(20.5, result.current_weather.temperature)
        assertEquals(0, result.current_weather.weathercode)
        assertEquals(5.0, result.current_weather.windspeed)
    }

    @Test
    fun `getWeather throws exception when API call fails`() = runTest {
        val errorMessage = "Network error"
        coEvery {
            api.getWeather(
                lat = any(),
                lon = any(),
                currentWeather = any()
            )
        } throws Exception(errorMessage)

        val exception = assertFailsWith<Exception> {
            repository.getWeather(52.52, 13.41)
        }

        assertEquals(errorMessage, exception.message)
    }
}