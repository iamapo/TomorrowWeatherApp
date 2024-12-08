package com.redred.tomorrowweatherapp.domain

import com.redred.tomorrowweatherapp.data.model.location.Location

interface LocationRepository {

    fun getCities(): List<Location>
}
