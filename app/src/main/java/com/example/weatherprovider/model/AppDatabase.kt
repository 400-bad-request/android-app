package com.example.weatherprovider.model;

import android.content.Context
import androidx.room.Database;
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [ForecastLocation::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): ForecastLocationDAO

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.locationDao())
                }
            }
        }

        suspend fun populateDatabase(locationDAO: ForecastLocationDAO) {
            // Delete all content here.
            locationDAO.deleteAll()

            // Add sample words.
            val location1 = ForecastLocation(2487956, "San Francisco")
            locationDAO.insert(location1)
            val location2 = ForecastLocation(44418, "London")
            locationDAO.insert(location2)

            println(locationDAO.getAll().toString());
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context, scope: CoroutineScope
        ): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance

                return instance
            }
        }
    }
}