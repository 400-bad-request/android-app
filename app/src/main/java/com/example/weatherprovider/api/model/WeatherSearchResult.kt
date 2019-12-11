package com.example.weatherprovider.api.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import java.math.BigInteger

data class WeatherSearchResult (
    var consolidated_weather: List<WeatherDetail>,
    var time: String,
    var sun_rise: String,
    var sun_set: String,
    var timezone_name: String,
    var parent: ParentDetail,
    var sources: List<SourceDetail>,
    var title: String,
    var location_type: String,
    var woeid: Int,
    var latt_long: String,
    var timezone: String
) {
    class Deserializer: ResponseDeserializable<WeatherSearchResult> {
        override fun deserialize(content: String): WeatherSearchResult? = Gson().fromJson(content, WeatherSearchResult::class.java)
    }
}

data class SourceDetail (
    var title: String,
    var slug: String,
    var url: String,
    var crawl_rate: Int
)

data class ParentDetail (
    var title: String,
    var location_type: String,
    var woeid: Int,
    var latt_long: String
)

data class WeatherDetail (
    var id: BigInteger,
    var weather_state_name: String,
    var weather_state_abbr: String,
    var wind_direction_compass: String,
    var created: String,
    var applicable_date: String,
    var min_temp: Float,
    var max_temp: Float,
    var the_temp: Float,
    var wind_speed: Float,
    var wind_direction: Float,
    var air_pressure: Float,
    var humidity: Int,
    var visibility: Float,
    var predictability: Int
)