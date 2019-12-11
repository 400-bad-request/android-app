package com.example.weatherprovider.location

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class LocationRepository(private val locationDAO: LocationDAO) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allLocations: LiveData<List<Location>> = locationDAO.getAll()

    fun insert(location: Location) {
        locationDAO.insert(location)
    }
}