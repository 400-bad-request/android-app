package com.example.weatherprovider.api

import com.example.weatherprovider.model.CitySearchResult
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result;

class CitySearchAPI {
    companion object {
        fun getCitiesAsync(query: String): ArrayList<CitySearchResult> {
            val URL = "https://www.metaweather.com/api/location/search/?query=$query"
            val citySearchResults = ArrayList<CitySearchResult>()

            URL.httpGet().responseObject(CitySearchResult.Deserializer()) { _, _, result ->
                when (result) {
                    is Result.Success -> {
                        val (data, _) = result
                        data?.forEach { entry ->
                            citySearchResults.add(entry)
                        }
                        println(citySearchResults)
                    }
                }
            }
            return citySearchResults
        }
    }
}