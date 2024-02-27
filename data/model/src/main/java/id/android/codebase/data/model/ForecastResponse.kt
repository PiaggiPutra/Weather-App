package id.android.codebase.data.model


import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("list")
    val list: List<ListWeather>,
    @SerializedName("message")
    val message: Int
) {
    data class ListWeather(
        @SerializedName("dt_txt")
        val dtTxt: String,
        @SerializedName("main")
        val main: Main,
        @SerializedName("weather")
        val weather: List<Weather>,
    ) {

        data class Main(
            @SerializedName("temp")
            val temp: Double
        )

        data class Weather(
            @SerializedName("description")
            val description: String,
            @SerializedName("icon")
            val icon: String,
            @SerializedName("main")
            val main: String
        )
    }
}