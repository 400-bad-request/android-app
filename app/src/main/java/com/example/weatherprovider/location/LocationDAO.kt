package com.example.weatherprovider.location

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LocationDAO {
    @Query("SELECT * FROM forecast_location")
    fun getAll(): LiveData<List<Location>>

    @Insert
    fun insert(location: Location)

    @Insert
    fun insertAll(vararg location: Location)

    @Delete
    fun delete(location: Location)

    @Query("DELETE FROM forecast_location")
    fun deleteAll()

    @Update
    fun updateLocation(vararg location: Location)
}