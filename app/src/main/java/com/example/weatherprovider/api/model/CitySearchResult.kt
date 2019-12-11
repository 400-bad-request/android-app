package com.example.weatherprovider.api.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class CitySearchResult (
    var title: String,
    var location_type: String,
    var woeid: Int,
    var latt_long: String
) {
    class Deserializer: ResponseDeserializable<Array<CitySearchResult>> {
        override fun deserialize(content: String): Array<CitySearchResult>? = Gson().fromJson(content, Array<CitySearchResult>::class.java)
    }
}