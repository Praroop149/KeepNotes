package com.example.skilltest.viewmodel

import android.content.Intent
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Delete
import com.example.skilltest.db.Notes
import com.example.skilltest.repositry.NoteRespository
import com.example.skilltest.views.EditNotesScreen
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NoteViewModel(private val repository:NoteRespository):ViewModel(),Observable {

    private var isUpdateOrDelete=false
    private lateinit var subscriberUpdateOrDelete: Notes

    val noteRespository=repository.notes
    @Bindable
    val inputDate=MutableLiveData<String>()
    @Bindable
    val inputTitle=MutableLiveData<String>()
    @Bindable
    val inputDescription=MutableLiveData<String>()
    @Bindable
    val inputSubmitData=MutableLiveData<String>()

    fun saveOrUpdate(){
    val date=inputDate.value!!

    val title=inputTitle.value!!
    val inputDescriptions=inputDescription.value!!

        insert(Notes(0,date,title,inputDescriptions))
        inputDate.value=null
        inputTitle.value=null
        inputDescription.value=null
    }
    fun savedate(){
          val current = LocalDateTime.now()
           val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
           val formatted = current.format(formatter)
           val date = formatter.parse(formatted)
           val desiredFormat = DateTimeFormatter.ofPattern("dd, MMMM").format(date)
           println(desiredFormat)
         inputDate!!.value=desiredFormat
    }
    fun insert(notes: Notes):Job= viewModelScope.launch {
            repository.insert(notes)
        }

    fun update(notes: Notes):Job=viewModelScope.launch {
        repository.update(notes)
    }

    fun delete(notes: Notes):Job=viewModelScope.launch {
        repository.delete(notes)
    }
    fun deleteAll(notes: Notes):Job=viewModelScope.launch {
        repository.deleteAll()
    }

    fun initUpdateOrDelete(notes: Notes){
        inputDate.value=notes.date
        inputTitle.value=notes.title
        inputDescription.value=notes.description
        isUpdateOrDelete=true
        subscriberUpdateOrDelete=notes
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}