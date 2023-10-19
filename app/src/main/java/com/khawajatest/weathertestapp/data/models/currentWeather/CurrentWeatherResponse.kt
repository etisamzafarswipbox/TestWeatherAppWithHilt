package com.khawajatest.weathertestapp.data.models.currentWeather

data class CurrentWeatherResponse(
    val coord: CurrentCoord,
    val weather: List<CurrentWeather>,
    val base: String,
    val main: CurrentMain,
    val visibility: Int,
    val wind: CurrentWind,
    val clouds: CurrentClouds,
    val dt: Long,
    val sys: CurrentSys,
    val timezone: Int,
    val id: Long,
    val name: String,
    val cod: Int
)

data class CurrentCoord(
    val lon: Double,
    val lat: Double
)

data class CurrentWeather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class CurrentMain(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int,
    val sea_level: Int,
    val grnd_level: Int
)

data class CurrentWind(
    val speed: Double,
    val deg: Int,
    val gust: Double
)

data class CurrentClouds(
    val all: Int
)

data class CurrentSys(
    val type: Int,
    val id: Long,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)
