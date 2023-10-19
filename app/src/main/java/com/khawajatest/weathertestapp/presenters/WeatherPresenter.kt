package com.khawajatest.weathertestapp.presenters

import com.khawajatest.weathertestapp.data.models.WeatherData
//import com.khawajatest.weathertestapp.data.models.WeatherModel
import com.khawajatest.weathertestapp.interfaces.WeatherContract
import com.khawajatest.weathertestapp.interfaces.WeatherView

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherPresenter{} /*@Inject constructor(private val weatherModel: WeatherModel) : BasePresenter<WeatherView>() {

    fun fetchCurrentWeather(cityName: String) {
        view?.showLoading()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val currentWeather = weatherModel.getCurrentWeather(cityName)
                val weatherForecast = weatherModel.getWeatherHistory(cityName)

                withContext(Dispatchers.Main) {
                    view?.hideLoading()
                    view?.displayCurrentWeather(currentWeather)
                    view?.displayWeatherHistory(weatherForecast.list)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    view?.hideLoading()
                    view?.displayError("Error fetching weather data: ${e.message}")
                }
            }
        }
    }

    fun onDestroy() {
        // Cleanup resources if needed
    }
}*/
