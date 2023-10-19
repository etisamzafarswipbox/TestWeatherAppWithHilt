package com.khawajatest.weathertestapp.data.models

data class WeatherResponse(
    val main: TemperatureData,
    val weather: List<WeatherDescription>,
)