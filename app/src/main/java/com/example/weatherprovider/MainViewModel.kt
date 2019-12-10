package com.example.weatherprovider

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherprovider.location.Location
import com.example.weatherprovider.location.LocationRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: LocationRepository
    val allLocations: LiveData<List<Location>>

    init {
        val locationDao = AppDatabase.getDatabase(application, viewModelScope).locationDao()
        repository = LocationRepository(locationDao)
        allLocations = repository.allLocations
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(location: Location) = viewModelScope.launch {
        repository.insert(location)
    }
}
