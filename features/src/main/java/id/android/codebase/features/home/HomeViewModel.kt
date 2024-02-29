package id.android.codebase.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.android.codebase.common.base.BaseViewModel
import id.android.codebase.data.repository.AppDispatchers
import id.android.codebase.domain.state.WeatherUIState
import id.android.codebase.domain.usecase.WeatherUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class HomeViewModel(
    private val appDispatchers: AppDispatchers,
    private val useCase: WeatherUseCase
) : BaseViewModel() {
    private val _contentCurrentWeather = MutableLiveData<WeatherUIState.ContentCurrentWeather>()
    val contentCurrentWeather: LiveData<WeatherUIState.ContentCurrentWeather> get() = _contentCurrentWeather

    private val _forecastWeatherData = MutableLiveData(mutableListOf<WeatherUIState.ItemContentForecastWeather>())
    val forecastWeatherData: LiveData<MutableList<WeatherUIState.ItemContentForecastWeather>> get() = _forecastWeatherData

    private val _forecastDailyWeatherData = MutableLiveData(mutableListOf<WeatherUIState.ItemDailyContentForecastWeather>())
    val forecastDailyWeatherData: LiveData<MutableList<WeatherUIState.ItemDailyContentForecastWeather>> get() = _forecastDailyWeatherData

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading
    var latitude = ""
    var longitude = ""

    fun getCurrentWeather(lat:String, lon: String) {
        latitude = lat
        longitude = lon
        viewModelScope.launch(appDispatchers.io) {
            useCase.getCurrentWeather(lat, lon).collect(::onCollectData)
        }
    }


    private fun onCollectData(data: WeatherUIState) {
        when (data) {
            WeatherUIState.Loading -> {
                _isLoading.postValue(true)
            }

            is WeatherUIState.ContentCurrentWeather -> {
                _isLoading.postValue(false)
                _contentCurrentWeather.postValue(data)
                getForecastCurrentWeather()
            }

            is WeatherUIState.Error -> {
                _isLoading.postValue(false)
            }

            else -> Unit
        }
    }

    private fun getForecastCurrentWeather() =
        viewModelScope.launch(appDispatchers.io) {
            useCase.getForecastCurrentWeather(latitude, longitude).collect(::onCollectForecastData)
        }

    private fun onCollectForecastData(data: WeatherUIState) {
        when (data) {
            WeatherUIState.Loading -> {
                _isLoading.postValue(true)
            }

            is WeatherUIState.ContentForecastWeather -> {
                _isLoading.postValue(false)
                _forecastWeatherData.postValue(data.listData.toMutableList())
                _forecastDailyWeatherData.postValue(data.listDaily.toMutableList())
            }

            is WeatherUIState.Error -> {
                _isLoading.postValue(false)
            }

            else -> Unit
        }
    }
}
