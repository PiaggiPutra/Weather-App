package id.android.codebase.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather")
data class WeatherData(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("weather")
    val weather: String
)