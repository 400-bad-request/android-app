package com.example.weatherprovider.citySearch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherprovider.AppDatabase
import com.example.weatherprovider.location.Location
import com.example.weatherprovider.location.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CitySearchViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: LocationRepository

    init {
        val locationDao = AppDatabase.getDatabase(application, viewModelScope).locationDao()
        repository = LocationRepository(locationDao)
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(woeid: Int, cityName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val location = Location(woeid, cityName)
            repository.insert(location)
        }
    }
}