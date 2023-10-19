package com.khawajatest.weathertestapp.interfaces

import com.khawajatest.weathertestapp.data.models.CurrentWeatherData
import com.khawajatest.weathertestapp.data.models.WeatherData

interface WeatherView {
    fun showLoading()
    fun hideLoading()
    fun displayCurrentWeather(currentWeather: CurrentWeatherData)
    fun displayWeatherHistory(weatherHistory: List<WeatherData>)
    fun displayError(message: String)
}
