package com.example.weatherprovider.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.TextView
import com.example.weatherprovider.R


class ForecastFragment : Fragment() {
    // Store instance variables
    private var title: String? = null
    private var woeid: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        woeid = arguments?.getInt("someInt", 0)
        title = arguments?.getString("someTitle")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.weather_forecast_fragment, container, false)
        val woeidLabel = view.findViewById(R.id.woeid_text) as TextView
        woeidLabel.text = woeid.toString()
        return view
    }

    companion object {
        fun newInstance(page: Int, title: String): ForecastFragment {
            val fragmentFirst = ForecastFragment()
            val args = Bundle()
            args.putInt("someInt", page)
            args.putString("someTitle", title)
            fragmentFirst.setArguments(args)
            return fragmentFirst
        }
    }
}
