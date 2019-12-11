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
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherprovider.R
import com.example.weatherprovider.api.WeatherSearchAPI
import com.example.weatherprovider.api.model.CitySearchResult
import com.example.weatherprovider.api.model.WeatherSearchResult
import java.net.URL


class LocationFragment : Fragment() {
    // Store instance variables
    private var cityName: String? = null
    private var woeid: Int? = 0
    private var weatherData: WeatherSearchResult? = null

    private lateinit var cityNameLabel: TextView
    private lateinit var cityParentNameLabel: TextView
    private lateinit var weatherImageAvatar: ImageView

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
        cityNameLabel = view.findViewById(R.id.city_name) as TextView
        cityParentNameLabel = view.findViewById(R.id.city_parent_name) as TextView
        weatherImageAvatar = view.findViewById(R.id.weather_image_avatar) as ImageView

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
        cityNameLabel.text = data?.title
        cityParentNameLabel.text = data?.parent?.title

        val currentWeather = data?.consolidated_weather?.first()
        DownLoadImageTask(weatherImageAvatar).execute("https://www.metaweather.com/static/img/weather/png/${currentWeather?.weather_state_abbr}.png")
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
