package com.khawajatest.weathertestapp.data.models

data class CurrentWeatherResponse(val main: Main, val weather: List<Weather>)
data class Main(val temp: Double)
data class Weather(
    val date: String,
    val temperature: String,
)


data class WeatherForecastResponse(val list: List<Forecast>)
data class Forecast(val main: Main, val weather: List<Weather>, val dt_txt: String)

