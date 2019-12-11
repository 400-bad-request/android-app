package com.example.weatherprovider.api

import com.example.weatherprovider.api.model.CitySearchResult
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result;

class CitySearchAPI {
    companion object {
        fun getCitiesAsync(query: String, onSuccess: (data: ArrayList<CitySearchResult>) -> Unit) {
            val URL = "https://www.metaweather.com/api/location/search/?query=$query"
            val citySearchResults = ArrayList<CitySearchResult>()

            URL.httpGet().responseObject(CitySearchResult.Deserializer()) { _, _, result ->
                when (result) {
                    is Result.Success -> {
                        val (data, _) = result
                        data?.forEach { entry ->
                            citySearchResults.add(entry)
                        }
                        onSuccess(citySearchResults)
                    }
                }
            }
        }
    }
}