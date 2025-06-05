package com.example.liveweather.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(MoshiConverterFactory.create()).build()
    }
    val api: WeatherAPI by lazy {
        retrofit.create(WeatherAPI::class.java)
    }
}