package com.example.weatherprovider.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ForecastLocationDAO {
    @Query("SELECT * FROM forecast_location")
    fun getAll(): LiveData<List<ForecastLocation>>

    @Insert
    fun insert(location: ForecastLocation)

    @Insert
    fun insertAll(vararg location: ForecastLocation)

    @Delete
    fun delete(location: ForecastLocation)

    @Query("DELETE FROM forecast_location")
    fun deleteAll()

    @Update
    fun updateLocation(vararg location: ForecastLocation)
}