package com.redred.tomorrowweatherapp.domain

import com.redred.tomorrowweatherapp.data.model.location.Location

interface LocationRepository {

    suspend fun getCities(): List<Location>
}
