package id.android.codebase.domain.usecase

import id.android.codebase.domain.repository.WeatherRepository
import id.android.codebase.domain.state.WeatherUIState
import id.android.codebase.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.collect
import java.text.SimpleDateFormat
import java.util.Date


class WeatherUseCase(
    private val repository: WeatherRepository
) {
    fun getCurrentWeather(lat: String, lon: String): Flow<WeatherUIState> = flow {
        repository.getWeatherCurrentLocation(lat, lon).collect {
            when (it.status) {
                Resource.Status.LOADING -> emit(WeatherUIState.Loading)
                Resource.Status.ERROR -> emit(WeatherUIState.Error)
                Resource.Status.SUCCESS -> {
                    val data = it.data
                    emit(
                        WeatherUIState.ContentCurrentWeather(
                            city = data?.name.orEmpty(),
                            temp = formatTempToCelsius(
                                data?.temp ?: 0.0
                            ).toString() + CELSIUS_FORMAT,
                            weatherDescription = data?.weather.orEmpty()
                        )
                    )
                }
            }
        }
    }

    fun getForecastCurrentWeather(lat: String, lon: String): Flow<WeatherUIState> = flow {
        repository.getForecastWeatherCurrentLocation(lat, lon).collect {
            when (it.status) {
                Resource.Status.LOADING -> emit(WeatherUIState.Loading)
                Resource.Status.ERROR -> emit(WeatherUIState.Error)
                Resource.Status.SUCCESS -> {
                    val data = it.data
                    val groupByDay = data?.groupBy {dataGroup ->
                        stringToDate(dataGroup.dtTxt).day
                    }
                    val listData = ArrayList<WeatherUIState.ItemContentForecastWeather>()
                    groupByDay?.get(0)?.forEach { dataHourly ->
                        listData.add(
                            WeatherUIState.ItemContentForecastWeather(
                                time = dataHourly.dtTxt.split(" ").last(),
                                temp = formatTempToCelsius(
                                    dataHourly.temp
                                ).toString() + CELSIUS_FORMAT,
                                icon = dataHourly.icon,
                                weatherDescription = dataHourly.weather
                            )
                        )
                    }
                    emit(WeatherUIState.ContentForecastWeather(listData))

                }
            }
        }
    }

    private fun formatTempToCelsius(data: Double): Int {
        return (data - 273.15).toInt()
    }

    private fun stringToDate(date: String): Date {
        val sdf = SimpleDateFormat(DATE_PATTERN)
        return sdf.parse(date)
    }

    companion object {
        const val CELSIUS_FORMAT = "\u2103"
        const val DATE_PATTERN = "yyyy-MM-dd HH:mm"
    }
}