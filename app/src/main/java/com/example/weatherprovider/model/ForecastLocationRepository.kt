package com.example.weatherprovider.model

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class ForecastLocationRepository(private val forecastLocationDAO: ForecastLocationDAO) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allLocations: LiveData<List<ForecastLocation>> = forecastLocationDAO.getAll()

    suspend fun insert(location: ForecastLocation) {
        forecastLocationDAO.insert(location)
    }
}