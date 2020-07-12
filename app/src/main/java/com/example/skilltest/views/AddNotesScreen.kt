package com.example.skilltest.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.skilltest.R
import com.example.skilltest.databinding.AddnotesscreenBinding
import com.example.skilltest.db.NoteDatabase
import com.example.skilltest.repositry.NoteRespository
import com.example.skilltest.viewmodel.NoteViewModel
import com.example.skilltest.viewmodel.NoteViewModelFactory
import kotlinx.android.synthetic.main.addnotesscreen.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddNotesScreen:AppCompatActivity() {
    private lateinit var binding:AddnotesscreenBinding
    private lateinit var noteViewModel: NoteViewModel
    var text_date:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.addnotesscreen)
        text_date=findViewById(R.id.text_date)
        val dao=NoteDatabase.getInstance(application).notesDao
        val repositoty=NoteRespository(dao)
        val factory=NoteViewModelFactory(repositoty)
        noteViewModel=ViewModelProvider(this,factory).get(NoteViewModel::class.java)
        binding.myViewModel=noteViewModel
        binding.lifecycleOwner=this
        binding.textHeadTitle.setText("Add Note")
        displayNotesList()
        val iv_back=findViewById<ImageView>(R.id.iv_back)
        iv_back.setOnClickListener {
           val intent=Intent(this,MainScreen::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun displayNotesList(){
        noteViewModel.noteRespository.observe(this, Observer {
            Log.i("MYTAG",it.toString())
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent=Intent(this,MainScreen::class.java)
        startActivity(intent)
        finish()
    }
}