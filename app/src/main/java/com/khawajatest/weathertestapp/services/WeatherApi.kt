package com.khawajatest.weathertestapp.services

import com.khawajatest.weathertestapp.data.models.CurrentWeatherResponse
import com.khawajatest.weathertestapp.data.models.WeatherForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getCurrentWeather(@Query("q") cityName: String): CurrentWeatherResponse

    @GET("forecast")
    suspend fun getWeatherForecast(@Query("q") cityName: String): WeatherForecastResponse


}
