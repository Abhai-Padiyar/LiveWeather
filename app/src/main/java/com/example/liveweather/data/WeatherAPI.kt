package com.example.liveweather.data

import com.example.liveweather.data.model.CurrentWeather
import com.example.liveweather.data.model.GeoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("geo/1.0/direct")
    suspend fun getCoords (
        @Query("q") city: String,
        @Query("limit") limit: Int = 1,
        @Query("appid") apiKey: String
    ): List<GeoResponse>

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather (
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): CurrentWeather
}