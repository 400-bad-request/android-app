package com.example.weatherprovider.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.TextView
import com.example.weatherprovider.R


class LocationFragment : Fragment() {
    // Store instance variables
    private var cityName: String? = null
    private var woeid: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        woeid = arguments?.getInt("woeid", 0)
        cityName = arguments?.getString("cityName")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_location_forecast, container, false)
        val woeidLabel = view.findViewById(R.id.woeid_text) as TextView
        woeidLabel.text = woeid.toString()
        return view
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