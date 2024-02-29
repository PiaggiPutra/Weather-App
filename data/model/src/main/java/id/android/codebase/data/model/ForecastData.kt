package id.android.codebase.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "forecast")
data class ForecastData(
    @PrimaryKey
    @SerializedName("dt_txt")
    val dtTxt: String,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("weather")
    val weather: String,
    @SerializedName("icon")
    val icon: String
)