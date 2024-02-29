package id.android.codebase.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import id.android.codebase.data.model.ForecastData
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ForecastDao: BaseDao<ForecastData>() {

    @Query("SELECT * FROM FORECAST")
    abstract fun getForecast(): Flow<List<ForecastData>>

    fun saveData(data: List<ForecastData>) {
        insert(data)
    }

}