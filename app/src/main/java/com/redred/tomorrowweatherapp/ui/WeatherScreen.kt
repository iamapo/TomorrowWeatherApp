package com.redred.tomorrowweatherapp.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.redred.tomorrowweatherapp.ui.components.ErrorMessage
import com.redred.tomorrowweatherapp.ui.components.LoadingIndicator
import com.redred.tomorrowweatherapp.ui.components.TimeProgressBar
import com.redred.tomorrowweatherapp.ui.components.WeatherContent
import com.redred.tomorrowweatherapp.ui.components.WeatherTopBar
import com.redred.tomorrowweatherapp.ui.viewmodel.WeatherState
import com.redred.tomorrowweatherapp.ui.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    val weatherState by viewModel.weatherState.collectAsState()
    val progress by viewModel.progressState.collectAsState()

    Scaffold(
        topBar = { WeatherTopBar() },
        bottomBar = {
            TimeProgressBar(
                progress = progress,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    ) { paddingValues ->
        AnimatedContent(
            modifier = Modifier.padding(paddingValues),
            targetState = weatherState,
            transitionSpec = {
                fadeIn() togetherWith fadeOut()
            },
            label = "Weather Animation"
        ) { state ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                when (state) {
                    is WeatherState.Loading -> LoadingIndicator()
                    is WeatherState.Success -> WeatherContent(weather = state)
                    is WeatherState.Error -> ErrorMessage(
                        message = "${state.cityName}: ${state.message}"
                    )
                }
            }
        }
    }
}