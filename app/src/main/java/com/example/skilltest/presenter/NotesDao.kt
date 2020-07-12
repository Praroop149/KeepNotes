package com.example.skilltest.presenter

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.skilltest.db.Notes

@Dao
interface NotesDao {
    @Insert
    suspend fun insertNotes(notes: Notes):Long

    @Update
    suspend fun updateNpotes(notes: Notes)

    @Delete
    suspend fun deleteNotes(notes: Notes)

    @Query(value = "DELETE From notes_data_table")
    suspend fun deleteAll()


    @Query(value = "SELECT *  From notes_data_table")
    fun getAllNotes():LiveData<List<Notes>>
}