package com.ferbotz.calculatortest.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note_table")
data class Remainders(
    @PrimaryKey(autoGenerate = true) val  noteId:Int?,
    @ColumnInfo val hint:String
)
