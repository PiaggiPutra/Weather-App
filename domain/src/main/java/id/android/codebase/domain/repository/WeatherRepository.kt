package id.android.codebase.domain.repository

import id.android.codebase.data.model.ForecastResponse
import id.android.codebase.data.model.WeatherResponse
import id.android.codebase.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherCurrentLocation(lat: String, lon: String): Flow<Resource<WeatherResponse>>

    suspend fun getForecastWeatherCurrentLocation(
        lat: String,
        lon: String
    ): Flow<Resource<ForecastResponse>>

    suspend fun getWeatherByCity(city: String): Flow<Resource<WeatherResponse>>
}