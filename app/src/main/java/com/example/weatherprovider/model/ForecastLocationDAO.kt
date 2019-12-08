package com.example.weatherprovider.model

import androidx.room.*

@Dao
interface ForecastLocationDAO {
    @Query("SELECT * FROM forecast_location")
    fun getAll(): List<ForecastLocation>

    @Insert
    fun insertAll(vararg location: ForecastLocation)

    @Delete
    fun delete(location: ForecastLocation)

    @Update
    fun updateLocation(vararg location: ForecastLocation)
}