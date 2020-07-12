package com.example.skilltest.fragment

import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skilltest.R
import com.example.skilltest.adapter.MyNotesViewAdapter
import com.example.skilltest.databinding.AddnotesscreenBinding
import com.example.skilltest.databinding.FragmentHomeBinding
import com.example.skilltest.db.NoteDatabase
import com.example.skilltest.repositry.NoteRespository
import com.example.skilltest.viewmodel.NoteViewModel
import com.example.skilltest.viewmodel.NoteViewModelFactory


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var noteViewModel: NoteViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        val  view=binding.root
        val dao= NoteDatabase.getInstance(activity!!).notesDao
        val repositoty= NoteRespository(dao)
        val factory= NoteViewModelFactory(repositoty)
        noteViewModel= ViewModelProvider(this,factory).get(NoteViewModel::class.java)
        binding.myViewModel1=noteViewModel
        binding.lifecycleOwner=this
       // initRecyclerView()
        return view
    }
/*    private fun displayNotesList(){
        noteViewModel.noteRespository.observe(this, Observer {
            Log.i("MYTAG",it.toString())
            binding.recyclerNotes.adapter=MyNotesViewAdapter(it)
        })
    }
    private fun initRecyclerView(){
        binding.recyclerNotes.layoutManager=GridLayoutManager(activity,2)
        displayNotesList()
    }*/
}
