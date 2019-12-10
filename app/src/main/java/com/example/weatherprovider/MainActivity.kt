package com.example.weatherprovider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.weatherprovider.forecast.ForecastFragment
import com.example.weatherprovider.forecast.ForecastPagerAdapter
import com.example.weatherprovider.model.Location


class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var mainViewModel: MainViewModel
    private lateinit var pagerAdapter: ForecastPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewPager)
        pagerAdapter = ForecastPagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter

        mainViewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
        mainViewModel.allLocations.observe(this, Observer<List<Location>> { locations ->
            for (location in locations) {
                pagerAdapter.addFragment(location.woeid, location.name)
            }
            pagerAdapter.notifyDataSetChanged()
        })
    }
}
 