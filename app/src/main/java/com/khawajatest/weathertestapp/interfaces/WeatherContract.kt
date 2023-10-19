package com.khawajatest.weathertestapp.interfaces

import com.khawajatest.weathertestapp.data.models.WeatherData

interface WeatherContract {
    interface View {
        fun displayWeatherData(weatherData: WeatherData)
        fun displayError(errorMessage: String)
    }

    interface Presenter {
        fun fetchWeatherForCity(cityName: String)
        fun onDestroy()
    }
}