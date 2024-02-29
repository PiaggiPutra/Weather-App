package id.android.codebase.domain.state

sealed class WeatherUIState {

    object Loading : WeatherUIState()
    object Error : WeatherUIState()

    data class ContentCurrentWeather(
        val city: String = "",
        val temp: String = "",
        val weatherDescription: String = ""
    ) : WeatherUIState()

    data class ContentForecastWeather(
        val listData : ArrayList<ItemContentForecastWeather>,
        val listDaily : ArrayList<ItemDailyContentForecastWeather>
    ): WeatherUIState()

    data class ItemContentForecastWeather(
        val time: String = "",
        val temp: String = "",
        val icon: String = "",
        val weatherDescription: String = ""
    ): WeatherUIState()

    data class ItemDailyContentForecastWeather (
        val day: String = "",
        val temp: String,
        val weather: String
        )

}