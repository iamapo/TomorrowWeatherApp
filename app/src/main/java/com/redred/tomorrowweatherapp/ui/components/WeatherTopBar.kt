package com.redred.tomorrowweatherapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTopBar() {
    TopAppBar(
        title = { },
        modifier = Modifier.fillMaxWidth()
    )
}