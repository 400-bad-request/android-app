package com.example.weatherprovider.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast_location")
data class ForecastLocation(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "woeid") var woeid: Int,
    @ColumnInfo(name = "name") var name: String
)