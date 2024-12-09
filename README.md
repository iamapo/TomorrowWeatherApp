# TomorrowWeatherApp

TomorrowWeatherApp is an Android application that displays weather information for various cities. The app uses Koin for dependency injection and Jetpack Compose for the user interface.

## Features

- Display weather information for various cities
- Automatic switching between cities
- Progress bar for city switching

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/iamapo/TomorrowWeatherApp.git
    ```
2. Open the project in Android Studio.
3. Build the project and run it on an Android device or emulator.

## Dependencies

- [Koin](https://insert-koin.io/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

## Architecture

The project follows the MVVM (Model-View-ViewModel) architecture pattern.

- **Model**: Contains data classes and repositories.
- **View**: Consists of Composables that represent the UI.
- **ViewModel**: Manages UI-related data and logic.

## Directory Structure

```plaintext
.
├── data
│   ├── model
│   │   ├── location
│   │   └── weather
│   └── repository
├── di
├── domain
├── ui
│   ├── components
│   ├── theme
│   └── viewmodel
└── WeatherApp.kt
