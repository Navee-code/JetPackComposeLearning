package com.ferbotz.calculatortest.database

import androidx.room.*


@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(remainders: Remainders)
}
