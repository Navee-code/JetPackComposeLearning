package com.ferbotz.calculatortest.database

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.ferbotz.calculatortest.AppConstants


@Database(entities = [Remainders::class], version = 1, exportSchema = false)
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
                    AppConstants.appDb
                ).build()
                instance = instanceNew
                return instanceNew
            }
        }
    }
}
