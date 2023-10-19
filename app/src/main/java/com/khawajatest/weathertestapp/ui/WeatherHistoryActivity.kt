package com.khawajatest.weathertestapp.ui


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khawajatest.weathertestapp.R

import com.google.android.material.snackbar.Snackbar
import com.khawajatest.weathertestapp.adapters.WeatherHistoryAdapter
//import com.khawajatest.weathertestapp.data.implementations.WeatherModelImpl
import com.khawajatest.weathertestapp.data.models.CurrentWeatherData
import com.khawajatest.weathertestapp.data.models.WeatherData
import com.khawajatest.weathertestapp.interfaces.WeatherView
import com.khawajatest.weathertestapp.viewmodels.WeatherViewModel

class WeatherHistoryActivity : AppCompatActivity(), WeatherView {

    //private lateinit var presenter: WeatherPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WeatherHistoryAdapter
    private lateinit var cityNameEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var currentWeatherTextView: TextView
    private lateinit var currentWeatherIcon: ImageView

    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_history)

        // Initialize UI
        recyclerView = findViewById(R.id.weatherHistoryRecyclerView)
        adapter = WeatherHistoryAdapter(emptyList())
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        cityNameEditText = findViewById(R.id.cityNameEditText)
        searchButton = findViewById(R.id.searchButton)
        progressBar = findViewById(R.id.progressBar)
        currentWeatherTextView = findViewById(R.id.currentWeatherTextView)
        currentWeatherIcon = findViewById(R.id.currentWeatherIcon)

        searchButton.setOnClickListener {
            val cityName = cityNameEditText.text.toString()
            if (cityName.isNotEmpty()) {
                fetchCurrentWeather(cityName)
            }
        }
    }


    private fun fetchCurrentWeather(cityName: String) {
    }

    // Implement methods from WeatherView interface
    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun displayCurrentWeather(currentWeather: CurrentWeatherData) {
        currentWeatherTextView.text = currentWeather.description
        // Load the current weather icon using Glide or another image loading library
        Glide.with(this)
            .load(currentWeather.iconUrl)
            .into(currentWeatherIcon)
    }

    override fun displayWeatherHistory(weatherHistory: List<WeatherData>) {
        adapter = WeatherHistoryAdapter(weatherHistory)
        recyclerView.adapter = adapter
    }

    override fun displayError(message: String) {
        // Handle and display error message
        Snackbar.make(
            recyclerView,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        //presenter.onDestroy()
    }
}
