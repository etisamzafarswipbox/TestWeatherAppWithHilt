package com.khawajatest.weathertestapp.services

import com.khawajatest.weathertestapp.data.models.Weather
import com.khawajatest.weathertestapp.data.models.currentWeather.CurrentWeatherResponse
import com.khawajatest.weathertestapp.data.models.weatherforcast.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") location: String,
        @Query("appid") apiKey: String
    ): CurrentWeatherResponse

    @GET("forecast")
    suspend fun getWeatherHistory(
        @Query("q") location: String,
        @Query("appid") apiKey: String
    ): ForecastResponse
}

