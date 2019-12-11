package com.example.weatherprovider.api

import com.example.weatherprovider.api.model.WeatherSearchResult
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class WeatherSearchAPI {
    companion object {

        fun getWeatherAsync(id: Int, onSuccess: (data: WeatherSearchResult) -> Unit) {
            val URL = "https://www.metaweather.com/api/location/$id"
            URL.httpGet().responseObject(WeatherSearchResult.Deserializer()) { _, _, result ->
                when (result) {
                    is Result.Success -> {
                        val (data, _) = result
                        if (data !== null) {
                            onSuccess(data)
                        }
                    }
                }
            }
        }
    }
}