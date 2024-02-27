package id.android.codebase.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.android.codebase.data.local.converter.Converters
import id.android.codebase.data.local.dao.ExampleDao
import id.android.codebase.data.model.ExampleModel

@Database(entities = [ExampleModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    // DAO
    abstract fun exampleDao(): ExampleDao

    companion object {

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "App.db")
                .build()
    }
}
