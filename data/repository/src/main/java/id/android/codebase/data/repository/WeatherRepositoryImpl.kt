package id.android.codebase.data.repository

import id.android.codebase.data.local.dao.ForecastDao
import id.android.codebase.data.local.dao.WeatherDao
import id.android.codebase.data.model.ForecastData
import id.android.codebase.data.model.response.ForecastResponse
import id.android.codebase.data.model.WeatherData
import id.android.codebase.data.model.response.WeatherResponse
import id.android.codebase.data.remote.ApiDataSource
import id.android.codebase.data.repository.utils.getFlow
import id.android.codebase.data.repository.utils.networkBoundHandling
import id.android.codebase.domain.repository.WeatherRepository
import id.android.codebase.domain.state.WeatherUIState
import id.android.codebase.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl(
    private val datasource: ApiDataSource,
    private val weatherDao: WeatherDao,
    private val forecastDao : ForecastDao
) : WeatherRepository {
    override suspend fun getWeatherCurrentLocation(
        lat: String,
        lon: String
    ): Flow<Resource<WeatherData>> = networkBoundHandling(
        callApi = {
            datasource.getWeatherCurrentLocation(lat, lon)
        },
        processResponse = {
            it?.let {
                WeatherData(
                    id = it.id,
                    name = it.name,
                    temp = it.main.temp,
                    weather = it.weather[0].main
                )
            }
        },
        saveFetchResult = {
            it?.let { data ->
                weatherDao.saveData(data)
            }
        },
        databaseQuery = {
            weatherDao.getWeather()
        }
    )

    override suspend fun getForecastWeatherCurrentLocation(
        lat: String,
        lon: String
    ): Flow<Resource<List<ForecastData>>> = networkBoundHandling(
        callApi = {
            datasource.getForecastWeatherCurrentLocation(lat, lon)
        },
        processResponse = {
            val listData = ArrayList<ForecastData>()
            it?.list?.forEach {data ->
                listData.add(ForecastData(
                    dtTxt = data.dtTxt,
                    temp = data.main.temp,
                    weather = data.weather[0].main,
                    icon = data.weather[0].icon
                ))
            }
            listData
        },
        saveFetchResult = {
            it?.let { data ->
                forecastDao.saveData(data)
            }
        },
        databaseQuery = {
            forecastDao.getForecast()
        }
    )

    override suspend fun getWeatherByCity(
        city: String
    ): Flow<Resource<WeatherResponse>> =
        datasource.getWeatherByCity(city).getFlow()
}