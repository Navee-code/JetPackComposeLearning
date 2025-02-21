package com.ferbotz.calculatortest.database

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database

import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Remainders::class], version = 3, exportSchema = false)
abstract class AppDb : RoomDatabase() {

    abstract fun noteDao(): AppDao

    companion object {
        @Volatile
        private var instance: AppDb? = null

        fun getDatabase(context: Context): AppDb {
            val tempInstance = instance
            if (tempInstance != null) return tempInstance

            synchronized(this) {
                val instanceNew = Room.databaseBuilder(
                    context.applicationContext,
                    AppDb::class.java,
                    "app_database"
                ).build()
                instance = instanceNew
                return instanceNew
            }
        }
    }
}
