package com.khawajatest.weathertestapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Spinner
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.khawajatest.weathertestapp.R
import com.khawajatest.weathertestapp.data.models.weatherforcast.ForecastData
import com.khawajatest.weathertestapp.databinding.ItemWeatherHistoryBinding
import com.khawajatest.weathertestapp.utilities.Constants
import com.khawajatest.weathertestapp.utilities.Utility
import com.khawajatest.weathertestapp.utilities.Utility.convertTemperature
import com.squareup.picasso.Picasso

class WeatherAdapter(
    private val spinner: Spinner,
) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

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
            itemBinding.temperatureTextView.text = "${convertTemperature(weatherForcast.main.temp_min, spinner)} / ${convertTemperature(weatherForcast.main.temp_max, spinner)}"
            itemBinding.descriptionTextView.text = weatherForcast.weather[0].description
            val cornerRadiusInDp = itemBinding.root.context.resources.getDimension(R.dimen.card_corner_radius)
            itemBinding.dayWeatherCard.radius = cornerRadiusInDp
            itemBinding.dayWeatherIcon.post {
                Log.d(Constants.TAG, "currentLiveWeatherUpdate: ${weatherForcast.weather[0].icon}")
                val iconUrl = Utility.getWeatherIconUrl(weatherForcast.weather[0].icon)
                Picasso.get().load(iconUrl).into(itemBinding.dayWeatherIcon)
            }

        }
    }
}
