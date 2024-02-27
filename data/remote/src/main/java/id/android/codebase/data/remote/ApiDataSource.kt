package id.android.codebase.data.remote

import id.android.codebase.data.model.ForecastResponse
import id.android.codebase.data.model.WeatherResponse
import retrofit2.Response

class ApiDataSource(private val apiService: ApiService) {

    private val appId = "7ec1d4b27035157a6cd4244b580c74ec"

    suspend fun getWeatherCurrentLocation(
        lat: String,
        long: String
    ): Response<WeatherResponse> {
        return apiService.getWeatherCurrentLocation(lat, long, appId)
    }

    suspend fun getForecastWeatherCurrentLocation(
        lat: String,
        long: String
    ): Response<ForecastResponse> {
        return apiService.getForecastWeatherCurrentLocation(lat, long, appId)
    }

    suspend fun getWeatherByCity(
        city: String
    ): Response<WeatherResponse> {
        return apiService.getWeatherByCity(city, appId)
    }

}