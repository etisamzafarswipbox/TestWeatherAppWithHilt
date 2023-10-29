package com.khawajatest.weathertestapp.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Spinner
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

object Utility {

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    //  https://api.openweathermap.org/data/2.5/
    //  https://api.weather.com/
    fun getBaseUrl(): String {
        return BASE_URL
    }


    fun convertLongToUtcDayName(timestamp: Long): String {
        val instant = Instant.ofEpochSecond(timestamp)
        val dateTime = instant.atZone(ZoneId.of("UTC"))
        val dayName = dateTime.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
        val dayFormatter = DateTimeFormatter.ofPattern("d  ha")
        return (dayName + "," +dateTime.format(dayFormatter))
    }

    fun kelvinToFahrenheit(kelvin: Double): String {
        val fahrenheit = kelvin * 9/5 - 459.67
        val fahrenInt  = fahrenheit.toInt()
        return fahrenInt.toString() + "\u2109"
    }

    fun kelvinToCelsius(kelvin: Double): String {
        val celsius = kelvin - 273.15
        val celInt = celsius.toInt()
        return celInt.toString() + "\u2103"
    }

    fun getWeatherIconUrl(weatherCondition: String): String {
        return "https://openweathermap.org/img/w/${weatherCondition}.png"
    }

    val convertTemperature: (Double, Spinner) -> String = { kelvinInput, unitSpinner ->
        val kelvinValue = kelvinInput.toString().toDoubleOrNull()
        val selectedUnit = unitSpinner.selectedItemPosition
        if (kelvinValue != null) {
            val result = when (selectedUnit) {
                0 -> kelvinToFahrenheit(kelvinValue)
                1 -> kelvinToCelsius(kelvinValue)
                else -> "Invalid unit"
            }

            "$result"
        } else {
            "Invalid Kelvin value"
        }
    }


    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            return networkCapabilities != null && (networkCapabilities.hasTransport(
                NetworkCapabilities.TRANSPORT_WIFI) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }

}