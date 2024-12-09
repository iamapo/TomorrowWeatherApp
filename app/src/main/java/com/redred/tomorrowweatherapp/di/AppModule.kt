package com.redred.tomorrowweatherapp.di

import com.redred.tomorrowweatherapp.data.api.WeatherApiService
import com.redred.tomorrowweatherapp.data.repository.LocationRepositoryImpl
import com.redred.tomorrowweatherapp.data.repository.WeatherRepositoryImpl
import com.redred.tomorrowweatherapp.domain.LocationRepository
import com.redred.tomorrowweatherapp.domain.WeatherRepository
import com.redred.tomorrowweatherapp.ui.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)
    }

    single<WeatherRepository> { WeatherRepositoryImpl(get()) }

    single<LocationRepository> { LocationRepositoryImpl(get()) }

    viewModel { WeatherViewModel(get(), get()) }

}
