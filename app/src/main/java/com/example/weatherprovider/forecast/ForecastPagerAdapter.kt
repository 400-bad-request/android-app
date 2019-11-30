package com.example.weatherprovider.forecast

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.weatherprovider.model.Forecast

class ForecastPagerAdapter(fragmentManager: FragmentManager, private val forecasts: ArrayList<Forecast>) :
    FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return ForecastFragment.newInstance(forecasts[position])
    }

    // 3
    override fun getCount(): Int {
        return forecasts.size
    }
}