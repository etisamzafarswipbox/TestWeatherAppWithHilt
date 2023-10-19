package com.khawajatest.weathertestapp.utilities

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

object Utility {


    fun convertLongToUtcTime(timestamp: Long): Instant {
        return Instant.ofEpochSecond(timestamp)
    }

    fun convertLongToUtcDay(timestamp: Long): String {
        val instant = Instant.ofEpochSecond(timestamp)
        val dateTime = instant.atZone(ZoneId.of("UTC"))
        val dayFormatter = DateTimeFormatter.ofPattern("d")

        return dateTime.format(dayFormatter)
    }

    fun convertLongToUtcDayName(timestamp: Long): String {
        val instant = Instant.ofEpochSecond(timestamp)
        val dateTime = instant.atZone(ZoneId.of("UTC"))

        val dayName = dateTime.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)

        return dayName
    }

    fun convertLongToUtcDayAndMonth(timestamp: Long): String {
        val instant = Instant.ofEpochSecond(timestamp)
        val dateTime = instant.atZone(ZoneId.of("UTC"))
        val dayFormatter = DateTimeFormatter.ofPattern("DD-mm")

        return dateTime.format(dayFormatter)
    }
}