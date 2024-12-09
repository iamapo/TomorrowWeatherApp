package com.redred.tomorrowweatherapp.data.repository

import android.content.Context
import android.location.Geocoder
import android.util.Log
import com.redred.tomorrowweatherapp.data.model.location.Location
import com.redred.tomorrowweatherapp.domain.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale

class LocationRepositoryImpl(private val context: Context) : LocationRepository {

    override suspend fun getCities(): List<Location> {
        val locations = listOf(
            Location(53.619653, 10.079969, ""),
            Location(53.080917, 8.847533, ""),
            Location(52.378385, 9.794862, ""),
            Location(52.496385, 13.444041, ""),
            Location(53.866865, 10.739542, ""),
            Location(54.304540, 10.152741, ""),
            Location(54.797277, 9.491039, ""),
            Location(52.426412, 10.821392, ""),
            Location(53.542788, 8.613462, ""),
            Location(53.141598, 8.242565, "")
        )

        return locations.map { location ->
            val cityName = withContext(Dispatchers.IO) {
                getCityFromCoordinates(location.lat, location.lon)
            }
            location.copy(name = cityName ?: "Unbekannte Stadt")
        }
    }

    private suspend fun getCityFromCoordinates(latitude: Double, longitude: Double): String? {
        return withContext(Dispatchers.IO) {
            try {
                val geocoder = Geocoder(context, Locale.getDefault())
                val addresses = geocoder.getFromLocation(latitude, longitude, 1)
                if (!addresses.isNullOrEmpty()) {
                    return@withContext addresses[0].locality ?: addresses[0].subAdminArea
                }
            } catch (e: Exception) {
                Log.e("LocationRepository", "Fehler beim Abrufen der Stadt: ${e.localizedMessage}")
            }
            null
        }
    }
}