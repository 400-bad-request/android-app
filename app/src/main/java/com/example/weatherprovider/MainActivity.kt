package com.example.weatherprovider

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_view_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.itemId
        if (id == R.id.add_button) {
            val intent = Intent(this, CitySearchActivity::class.java).apply {}
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
 