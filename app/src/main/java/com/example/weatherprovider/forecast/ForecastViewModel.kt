package com.example.weatherprovider.forecast

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherprovider.model.AppDatabase
import com.example.weatherprovider.model.ForecastLocation
import com.example.weatherprovider.model.ForecastLocationRepository
import kotlinx.coroutines.launch

class ForecastViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ForecastLocationRepository
    val allLocations: LiveData<List<ForecastLocation>>

    init {
        val locationDao = AppDatabase.getDatabase(application, viewModelScope).locationDao()
        repository = ForecastLocationRepository(locationDao)
        allLocations = repository.allLocations
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(location: ForecastLocation) = viewModelScope.launch {
        repository.insert(location)
    }
}
