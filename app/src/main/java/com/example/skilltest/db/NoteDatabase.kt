package com.example.skilltest.db

import android.content.Context
import android.provider.CalendarContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.skilltest.presenter.NotesDao
import java.security.AccessControlContext

@Database(entities = [Notes::class],version = 1)
abstract class NoteDatabase:RoomDatabase() {
    abstract val notesDao:NotesDao

    companion object{
        private var INSTANCE:NoteDatabase?=null
        fun getInstance(context:Context):NoteDatabase{
            synchronized(this){
                var instance= INSTANCE
                if (instance==null){
                    instance=Room.databaseBuilder(
                        context.applicationContext,NoteDatabase::class.java,
                        "notes_data_table"
                    ).build()
                }
                return instance
            }
        }
    }
}