package id.android.codebase.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import id.android.codebase.data.model.WeatherData
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WeatherDao: BaseDao<WeatherData>() {

    @Query("SELECT * FROM WEATHER")
    abstract fun getWeather(): Flow<WeatherData>

    fun saveData(data: WeatherData) {
        insert(data)
    }

}