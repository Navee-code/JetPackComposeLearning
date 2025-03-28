package com.ferbotz.calculatortest.database

import androidx.room.*


@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     suspend fun insertNote(remainders: Remainders)

    @Query("SELECT * FROM note_table")
     suspend fun getAllNotes(): List<Remainders>
}
