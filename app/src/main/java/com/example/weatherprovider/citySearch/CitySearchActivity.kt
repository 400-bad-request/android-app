package com.example.weatherprovider.citySearch

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.weatherprovider.R

class CitySearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_search)
    }




//    private lateinit var citySearchViewModel: CitySearchViewModel
//    lateinit var woeidInput: EditText
//    lateinit var cityInput: EditText
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_city_search)
//        woeidInput = findViewById(R.id.woeid_input)
//        cityInput = findViewById(R.id.city_input)
//
//        citySearchViewModel = ViewModelProviders.of(this)[CitySearchViewModel::class.java]
//    }
//
//    fun onButtonPress(view: View) {
//        val woeid = Integer.parseInt(woeidInput.text.toString())
//        val cityName = cityInput.text.toString()
//        citySearchViewModel.insert(woeid, cityName)
//        finish()
//    }
}
