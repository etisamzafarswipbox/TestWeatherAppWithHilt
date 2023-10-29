package com.khawajatest.weathertestapp


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.khawajatest.weathertestapp.adapters.WeatherAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.khawajatest.weathertestapp.databinding.ActivityMainBinding
import com.khawajatest.weathertestapp.utilities.Constants.Companion.TAG
import com.khawajatest.weathertestapp.utilities.Constants.Companion.apiKey
import com.khawajatest.weathertestapp.utilities.Utility.convertTemperature
import com.khawajatest.weathertestapp.utilities.Utility.getWeatherIconUrl
import com.khawajatest.weathertestapp.utilities.Utility.isInternetAvailable
import com.khawajatest.weathertestapp.viewmodels.WeatherViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var weatherAdapter: WeatherAdapter
    private var inputMethodManager: InputMethodManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val spinner = binding.unitSpinner
        weatherAdapter = WeatherAdapter(spinner)
        // Set up the ArrayAdapter for the Spinner
        val unitAdapter = ArrayAdapter.createFromResource(this, R.array.unit_choices, android.R.layout.simple_spinner_item)
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.unitSpinner.adapter = unitAdapter

        // Set a listener for the Spinner to detect unit changes
        binding.unitSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                currentLiveWeatherUpdate()
                weatherAdapter.notifyDataSetChanged()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
        binding.weatherHistoryRecyclerView.layoutManager = LinearLayoutManager(this)

        //showing weather forecast in two columns
        val spanCount = 1
        val layoutManager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
        binding.weatherHistoryRecyclerView.layoutManager = layoutManager
        binding.weatherHistoryRecyclerView.adapter = weatherAdapter
        binding.fetchWeatherButton.setOnClickListener {
            hideKeyboard()
            if(isInternetAvailable(this@MainActivity))
                fetchWeather()
            else{
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "No Internet access",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        currentLiveWeatherUpdate()
        viewModel.weatherHistory.observe(this) { weatherHistory ->
            showProgressLoading(false)
            weatherAdapter.setWeatherData(weatherHistory.list)
        }
    }

    fun fetchWeather(){
        showProgressLoading(true)
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
    fun currentLiveWeatherUpdate(){
        viewModel.currentWeather.observe(this) { currentWeather ->
            binding.currentWeatherTextView.text = currentWeather.weather[0].description
            binding.locationName.text = currentWeather.name
            binding.temperatureText.text = convertTemperature(currentWeather.main.temp, binding.unitSpinner)
            binding.minMaxTemp.text = convertTemperature(currentWeather.main.temp_min, binding.unitSpinner) + " / " +convertTemperature(currentWeather.main.temp_max, binding.unitSpinner)

            // Display the appropriate weather icon based on the weather condition
            runOnUiThread {
                Log.d(TAG, "currentLiveWeatherUpdate: ${currentWeather.weather[0].icon}")
                val iconUrl = getWeatherIconUrl(currentWeather.weather[0].icon)
                Picasso.get().load(iconUrl).into(binding.currentWeatherIcon)
            }
        }
    }
    fun showProgressLoading(showLoader: Boolean){
        if(showLoader)
            binding.progressBar.visibility = View.VISIBLE
        else
            binding.progressBar.visibility = View.GONE
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}

