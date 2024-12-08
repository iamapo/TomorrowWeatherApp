package com.redred.tomorrowweatherapp.data.repository

import com.redred.tomorrowweatherapp.data.model.location.Location
import com.redred.tomorrowweatherapp.domain.LocationRepository

class LocationRepositoryImpl : LocationRepository {

    override fun getCities(): List<Location> {
        return listOf(
            Location(53.619653, 10.079969, "Hamburg"),
            Location(53.080917, 8.847533, "Bremen"),
            Location(52.378385, 9.794862, "Hannover"),
            Location(52.496385, 13.444041, "Berlin"),
            Location(53.866865, 10.739542, "Lübeck"),
            Location(54.304540, 10.152741, "Kiel"),
            Location(54.797277, 9.491039, "Flensburg"),
            Location(52.426412, 10.821392, "Wolfsburg"),
            Location(53.542788, 8.613462, "Wilhelmshaven"),
            Location(53.141598, 8.242565, "Oldenburg")
        )
    }
}