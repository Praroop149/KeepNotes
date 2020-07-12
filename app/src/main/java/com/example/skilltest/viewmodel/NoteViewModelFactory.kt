package com.example.skilltest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.skilltest.repositry.NoteRespository

class NoteViewModelFactory(private val respository: NoteRespository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NoteViewModel::class.java)){
            return NoteViewModel(respository)as T
        }
        throw IllegalAccessException("Unknown View Moddel Class")
    }
}