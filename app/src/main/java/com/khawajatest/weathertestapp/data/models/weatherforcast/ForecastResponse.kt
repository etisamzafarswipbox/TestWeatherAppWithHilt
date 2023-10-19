package com.khawajatest.weathertestapp.data.models.weatherforcast

data class ForecastResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<ForecastData>,
    val city: ForecastCity
)

data class ForecastData(
    val dt: Long,
    val main: ForecastMain,
    val weather: List<ForecastWeather>,
    val clouds: ForecastClouds,
    val wind: ForecastWind,
    val visibility: Int,
    val pop: Double,
    val sys: ForecastSys,
    val dt_txt: String
)

data class ForecastMain(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val sea_level: Int,
    val grnd_level: Int,
    val humidity: Int,
    val temp_kf: Double
)

data class ForecastWeather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class ForecastClouds(
    val all: Int
)

data class ForecastWind(
    val speed: Double,
    val deg: Int,
    val gust: Double
)

data class ForecastSys(
    val pod: String
)

data class ForecastCity(
    val id: Long,
    val name: String,
    val coord: ForecastCoord,
    val country: String,
    val population: Long,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long
)

data class ForecastCoord(
    val lat: Double,
    val lon: Double
)
