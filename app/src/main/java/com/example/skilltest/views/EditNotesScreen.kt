package com.example.skilltest.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.skilltest.R
import com.example.skilltest.databinding.AddnotesscreenBinding
import com.example.skilltest.databinding.EditnotesscreenBinding
import com.example.skilltest.db.NoteDatabase
import com.example.skilltest.db.Notes
import com.example.skilltest.repositry.NoteRespository
import com.example.skilltest.viewmodel.NoteViewModel
import com.example.skilltest.viewmodel.NoteViewModelFactory
import kotlinx.android.synthetic.main.addnotesscreen.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EditNotesScreen:AppCompatActivity() {
    private lateinit var binding:EditnotesscreenBinding
    //private lateinit var noteViewModel: NoteViewModel
    var text_date:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.editnotesscreen)
        val intent=intent
        val date=intent.getStringExtra("date")
        val id=intent.getStringExtra("dbid")
        val title=intent.getStringExtra("title")
        val description=intent.getStringExtra("description")

       /* val dao=NoteDatabase.getInstance(application).notesDao
        val repositoty=NoteRespository(dao)
        val factory=NoteViewModelFactory(repositoty)
        noteViewModel=ViewModelProvider(this,factory).get(NoteViewModel::class.java)
        binding.myViewModel=noteViewModel
        binding.lifecycleOwner=this*/
        binding.ivUndo.setImageResource(R.drawable.ic_bin)
        binding.ivRedo.setImageResource(R.drawable.ic_push_pin)
      binding.textDate.setText(date)
      binding.textHeadTitle.setText("Edit Note")
      binding.textTitle.setText(title)
      binding.textTitle.setText(title)
      binding.textContent.setText(description)
        val iv_back=findViewById<ImageView>(R.id.iv_back)
        iv_back.setOnClickListener {
           val intent=Intent(this,MainScreen::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun listClick(notes: Notes){
        /* Toast.makeText(this,"Selected ${notes.description}",Toast.LENGTH_LONG).show()*/
        val intent=Intent(this,EditNotesScreen::class.java)
        val s=notes.id
        intent.putExtra("dbid",s.toString())
        intent.putExtra("date",notes.date)
        intent.putExtra("title",notes.title)
        intent.putExtra("description",notes.description)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent=Intent(this,MainScreen::class.java)
        startActivity(intent)
        finish()
    }
}