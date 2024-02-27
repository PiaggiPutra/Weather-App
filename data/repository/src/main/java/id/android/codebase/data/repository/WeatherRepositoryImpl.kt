package id.android.codebase.data.repository

import id.android.codebase.data.model.ForecastResponse
import id.android.codebase.data.model.WeatherResponse
import id.android.codebase.data.remote.ApiDataSource
import id.android.codebase.data.repository.utils.getFlow
import id.android.codebase.domain.repository.WeatherRepository
import id.android.codebase.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl(private val datasource: ApiDataSource) :
    WeatherRepository {
    override suspend fun getWeatherCurrentLocation(
        lat: String,
        lon: String
    ): Flow<Resource<WeatherResponse>> =
        datasource.getWeatherCurrentLocation(lat, lon).getFlow()

    override suspend fun getForecastWeatherCurrentLocation(
        lat: String,
        lon: String
    ): Flow<Resource<ForecastResponse>> =
        datasource.getForecastWeatherCurrentLocation(lat, lon).getFlow()

    override suspend fun getWeatherByCity(
        city: String
    ): Flow<Resource<WeatherResponse>> =
        datasource.getWeatherByCity(city).getFlow()
}