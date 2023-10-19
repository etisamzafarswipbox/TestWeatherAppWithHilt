package com.khawajatest.weathertestapp


import android.os.Bundle
import android.util.Log


import com.khawajatest.weathertestapp.adapters.WeatherAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.khawajatest.weathertestapp.databinding.ActivityMainBinding
import com.khawajatest.weathertestapp.utilities.Constants.Companion.TAG
import com.khawajatest.weathertestapp.utilities.Constants.Companion.apiKey
import com.khawajatest.weathertestapp.viewmodels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private val weatherAdapter = WeatherAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)



        binding.weatherHistoryRecyclerView.layoutManager = LinearLayoutManager(this)

        //showing weather forecast in two columns
        val spanCount = 2
        val layoutManager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
        binding.weatherHistoryRecyclerView.layoutManager = layoutManager
        binding.weatherHistoryRecyclerView.adapter = weatherAdapter

        binding.fetchWeatherButton.setOnClickListener {
            Log.d(TAG, "onCreate: ${binding.locationEditText.text}")
            val location = binding.locationEditText.text.toString()
            if (location.isNotBlank()) {
                viewModel.fetchWeather(location, apiKey)
            } else {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Please enter a location",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        viewModel.currentWeather.observe(this) { currentWeather ->
            binding.currentWeatherTextView.text =
                currentWeather.weather[0].description
        }

        viewModel.weatherHistory.observe(this) { weatherHistory ->
            weatherAdapter.setWeatherData(weatherHistory.list)
        }
    }
}

