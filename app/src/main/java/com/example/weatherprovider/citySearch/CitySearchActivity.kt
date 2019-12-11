package com.example.weatherprovider.citySearch

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherprovider.R
import com.example.weatherprovider.api.CitySearchAPI
import com.example.weatherprovider.api.model.CitySearchResult
import kotlinx.android.synthetic.main.city_search_list_child.view.*

class CitySearchActivity : AppCompatActivity() {

    lateinit var cityList: RecyclerView
    var citiesSearchResults: MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_search)

        loadData()
        cityList = findViewById<RecyclerView>(R.id.city_search_list)
        cityList.layoutManager = LinearLayoutManager(this)
        cityList.adapter = CityAdapter(citiesSearchResults, this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.city_search_view_menu, menu)
        val searchItem = menu?.findItem(R.id.menu_city_search)

        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView

            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText!!.isNotEmpty()) {
                        println(CitySearchAPI.getCitiesAsync(newText, test))
                    }
                    return true
                }
            })
        }

        return super.onCreateOptionsMenu(menu)
    }

    val test: (data: List<CitySearchResult>) -> Unit = { data ->
        citiesSearchResults.clear()
        citiesSearchResults.addAll(data.map { item -> item.title })
        cityList.adapter?.notifyDataSetChanged()
    }

    class CityAdapter(items: List<String>, ctx: Context): RecyclerView.Adapter<CityAdapter.ViewHolder>() {

        var list = items
        var context = ctx

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(context).inflate(R.layout.city_search_list_child, parent, false))
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.name.text = list[position]
        }

        class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val name = view.city_name
        }
    }

    fun loadData() {
        citiesSearchResults.add("Los Angels")
        citiesSearchResults.add("San Francisco")
        citiesSearchResults.add("London")
        citiesSearchResults.add("Oslo")
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
