package com.khawajatest.weathertestapp.data

import android.util.Log
import com.khawajatest.weathertestapp.data.models.currentWeather.CurrentWeatherResponse
import com.khawajatest.weathertestapp.data.models.weatherforcast.ForecastResponse
import com.khawajatest.weathertestapp.services.WeatherService
import com.khawajatest.weathertestapp.utilities.Constants.Companion.TAG
import com.khawajatest.weathertestapp.utilities.Utility.getBaseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Inject

class WeatherRepository @Inject constructor() {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(getBaseUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val weatherService: WeatherService =
        retrofit.create(WeatherService::class.java)

    @Throws(IOException::class)
    suspend fun getCurrentWeather(location: String, apiKey: String): CurrentWeatherResponse {
        try {
            val response = weatherService.getCurrentWeather(location, apiKey)
            return response
        } catch (e: Exception) {
            Log.d(TAG, "getCurrentWeather: ${e.message}")
            throw e
        }
    }

    suspend fun getWeatherHistory(location: String, apiKey: String): ForecastResponse {
        try {
            val response = weatherService.getWeatherHistory(location, apiKey)
            return response
        } catch (e: Exception) {
            throw e
        }
    }
}

