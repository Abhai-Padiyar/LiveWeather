package com.example.liveweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.liveweather.ui.theme.LiveWeatherTheme
import android.util.Log
import androidx.activity.compose.setContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.liveweather.data.RetrofitInstance
import com.example.liveweather.BuildConfig

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        CoroutineScope(Dispatchers.IO).launch {
            val geoResponse = RetrofitInstance.api.getCoords(
                city = "Atlanta",
                apiKey = BuildConfig.OPEN_WEATHER_MAP_API_KEY
            )
            val location = geoResponse.firstOrNull()

            if (location != null) {
                val weather = RetrofitInstance.api.getCurrentWeather(
                    city = "Atlanta",
                    apiKey = BuildConfig.OPEN_WEATHER_MAP_API_KEY
                )

                Log.d(
                    "WeatherTest",
                    "Temp: ${weather.main.temp}, Desc: ${weather.weather.firstOrNull()?.description}"
                )
            } else {
                Log.e("WeatherTest", "No coordinates found.")
            }

        }

        setContent {
            LiveWeatherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LiveWeatherTheme {
        Greeting("Android")
    }
}