package com.example.weatherprovider.location

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import com.example.weatherprovider.R
import com.example.weatherprovider.api.WeatherSearchAPI
import com.example.weatherprovider.api.model.WeatherSearchResult
import java.net.URL
import kotlin.math.round


class LocationFragment : Fragment() {
    // Store instance variables
    private var cityName: String? = null
    private var woeid: Int? = 0
    private var weatherData: WeatherSearchResult? = null

    private lateinit var weatherImageAvatar: ImageView

    private lateinit var minTemperatureValue: TextView
    private lateinit var maxTemperatureValue: TextView
    private lateinit var windSpeedValue: TextView
    private lateinit var windDirectionValue: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        woeid = arguments?.getInt("woeid", 0)
        cityName = arguments?.getString("cityName")

        if (woeid !== null) {
            WeatherSearchAPI.getWeatherAsync(woeid as Int, onSuccessfulSearchCallback)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_location_forecast, container, false)

        weatherImageAvatar = view.findViewById(R.id.weather_image_avatar) as ImageView

        minTemperatureValue = view.findViewById(R.id.min_temperature_value) as TextView
        maxTemperatureValue = view.findViewById(R.id.max_temperature_value) as TextView
        windSpeedValue = view.findViewById(R.id.wind_speed_value) as TextView
        windDirectionValue = view.findViewById(R.id.wind_direction_value) as TextView

        if (weatherData !== null) {
            applyDataOnView(weatherData)
        }

        return view
    }

    val onSuccessfulSearchCallback: (data: WeatherSearchResult) -> Unit = { data ->
        weatherData = data
        applyDataOnView(data)
    }

    val applyDataOnView: (data: WeatherSearchResult?) -> Unit = { data ->
        val currentWeather = data?.consolidated_weather?.first()
        DownLoadImageTask(weatherImageAvatar).execute("https://www.metaweather.com/static/img/weather/png/${currentWeather?.weather_state_abbr}.png")

        minTemperatureValue.text = "${currentWeather?.min_temp?.round(1)} °C"
        maxTemperatureValue.text = "${currentWeather?.max_temp?.round(1)} °C"
        windSpeedValue.text = "${currentWeather?.wind_speed?.round(2)} mph"
        windDirectionValue.text = currentWeather?.wind_direction_compass
    }

    fun Float.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }

    private class DownLoadImageTask(internal val imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
        override fun doInBackground(vararg urls: String): Bitmap? {
            val urlOfImage = urls[0]
            return try {
                val inputStream = URL(urlOfImage).openStream()
                BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) { // Catch the download exception
                e.printStackTrace()
                null
            }
        }
        override fun onPostExecute(result: Bitmap?) {
            if(result!=null){
                imageView.setImageBitmap(result)
            } else {
                Toast.makeText(imageView.context,"Error downloading",Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        fun newInstance(woeid: Int, cityName: String): LocationFragment {
            val fragmentFirst = LocationFragment()
            val args = Bundle()
            args.putInt("woeid", woeid)
            args.putString("cityName", cityName)
            fragmentFirst.setArguments(args)
            return fragmentFirst
        }
    }
}
