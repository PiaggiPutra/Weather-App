package id.android.codebase.data.remote

import id.android.codebase.data.model.response.ForecastResponse
import id.android.codebase.data.model.response.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getWeatherCurrentLocation(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appId: String
    ): Response<WeatherResponse>

    @GET("forecast")
    suspend fun getForecastWeatherCurrentLocation(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appId: String
    ): Response<ForecastResponse>

    @GET("weather")
    suspend fun getWeatherByCity(
        @Query("q") city: String,
        @Query("appid") appId: String
    ): Response<WeatherResponse>

}