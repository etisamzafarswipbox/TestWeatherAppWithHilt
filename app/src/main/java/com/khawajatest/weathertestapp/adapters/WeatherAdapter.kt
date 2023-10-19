package com.khawajatest.weathertestapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.khawajatest.weathertestapp.data.models.weatherforcast.ForecastData
import com.khawajatest.weathertestapp.databinding.ItemWeatherHistoryBinding
import com.khawajatest.weathertestapp.utilities.Utility


class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private val weatherData = mutableListOf<ForecastData>()

    fun setWeatherData(data: List<ForecastData>) {
        weatherData.clear()
        weatherData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWeatherHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val forcastWeather = weatherData[position]
        holder.bind(forcastWeather)
    }

    override fun getItemCount() = weatherData.size

    inner class ViewHolder(@NonNull val itemBinding: ItemWeatherHistoryBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind( weatherForcast: ForecastData) {
            itemBinding.dateTextView.text = "${Utility.convertLongToUtcDayName(weatherForcast.dt)}"
            itemBinding.temperatureTextView.text = "${weatherForcast.main.temp.toInt()}"
            itemBinding.descriptionTextView.text = weatherForcast.weather[0].description
        }
    }
}
