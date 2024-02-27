package id.android.codebase.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import id.android.codebase.data.model.ExampleModel

@Dao
abstract class ExampleDao: BaseDao<ExampleModel>() {

    @Query("SELECT * FROM ExampleModel WHERE name = :name LIMIT 1")
    abstract fun getExample(name: String): ExampleModel

    fun save(data: ExampleModel) {
        insert(data)
    }

    fun save(datas: List<ExampleModel>) {
        insert(datas)
    }
}
