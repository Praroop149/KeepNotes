package com.example.skilltest.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_data_table")
data class Notes (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    val id:Int,
    @ColumnInfo(name = "note_date")
    val date:String,
    @ColumnInfo(name = "note_title")
    val title:String,
    @ColumnInfo(name = "note_description")
    val description:String
)

