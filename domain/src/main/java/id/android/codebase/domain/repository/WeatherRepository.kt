package id.android.codebase.domain.repository

import id.android.codebase.data.model.ForecastData
import id.android.codebase.data.model.WeatherData
import id.android.codebase.data.model.response.WeatherResponse
import id.android.codebase.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherCurrentLocation(lat: String, lon: String): Flow<Resource<WeatherData>>

    suspend fun getForecastWeatherCurrentLocation(
        lat: String,
        lon: String
    ): Flow<Resource<List<ForecastData>>>

    suspend fun getWeatherByCity(city: String): Flow<Resource<WeatherResponse>>
}