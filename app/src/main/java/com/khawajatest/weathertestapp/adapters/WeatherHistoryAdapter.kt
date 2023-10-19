package com.khawajatest.weathertestapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khawajatest.weathertestapp.R
import com.khawajatest.weathertestapp.data.models.WeatherData

class WeatherHistoryAdapter(private val weatherHistory: List<WeatherData>) :
    RecyclerView.Adapter<WeatherHistoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Define UI elements in the layout
        private val dateTextView = itemView.findViewById<TextView>(R.id.weatherCardDate)
        private val temperatureTextView = itemView.findViewById<TextView>(R.id.weatherCardTemperature)
        private val descriptionTextView = itemView.findViewById<TextView>(R.id.weatherCardDescription)
        private val weatherIconImageView = itemView.findViewById<ImageView>(R.id.weatherCardIcon)

        fun bind(weatherData: WeatherData) {
            dateTextView.text = weatherData.date
            temperatureTextView.text = weatherData.temperature
            descriptionTextView.text = weatherData.description

            // Load weather icon using Glide or another image loading library
            Glide.with(itemView.context)
                .load(weatherData.iconUrl)
                .placeholder(R.drawable.ic_weather_placeholder) // Placeholder image
                .error(R.drawable.ic_weather_error) // Error image
                .into(weatherIconImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weatherData = weatherHistory[position]
        holder.bind(weatherData)
    }

    override fun getItemCount(): Int {
        return weatherHistory.size
    }
}
