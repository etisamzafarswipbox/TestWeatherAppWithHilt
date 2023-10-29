package com.khawajatest.weathertestapp.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.khawajatest.weathertestapp.data.WeatherRepository

import com.khawajatest.weathertestapp.data.models.currentWeather.CurrentWeatherResponse
import com.khawajatest.weathertestapp.data.models.weatherforcast.ForecastResponse

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    private val _currentWeather = MutableLiveData<CurrentWeatherResponse>()
    val currentWeather: LiveData<CurrentWeatherResponse> get() = _currentWeather
    private val _weatherHistory = MutableLiveData<ForecastResponse>()
    val weatherHistory: LiveData<ForecastResponse> get() = _weatherHistory
    fun fetchWeather(location: String, apiKey: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val currentWeatherData = weatherRepository.getCurrentWeather(location, apiKey)
                val weatherHistoryData = weatherRepository.getWeatherHistory(location, apiKey)

                _currentWeather.postValue(currentWeatherData)
                _weatherHistory.postValue(weatherHistoryData)
            } catch (e: Exception) {
                // Handle errors here
            }
        }
    }
}


