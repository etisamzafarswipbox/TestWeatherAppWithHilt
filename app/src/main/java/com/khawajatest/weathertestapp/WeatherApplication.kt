package com.khawajatest.weathertestapp

import android.app.Application
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@HiltAndroidApp
class WeatherApplication : Application() {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/") // Replace with the actual base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

//  https://api.openweathermap.org/data/2.5/
//  https://api.weather.com/