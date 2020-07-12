package com.example.skilltest.repositry

import com.example.skilltest.db.Notes
import com.example.skilltest.presenter.NotesDao

class NoteRespository(private val dao:NotesDao) {
    val notes=dao.getAllNotes()

    suspend fun insert(notes: Notes){
        dao.insertNotes(notes)

    }

    suspend fun update(notes: Notes){
        dao.updateNpotes(notes)
    }
    suspend fun delete(notes: Notes){
        dao.deleteNotes(notes)
    }
    suspend fun deleteAll(){
        dao.deleteAll()
    }
}