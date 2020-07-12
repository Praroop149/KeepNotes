package com.example.skilltest.views


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skilltest.R
import com.example.skilltest.adapter.MyNotesViewAdapter
import com.example.skilltest.databinding.FragmentHomeBinding
import com.example.skilltest.databinding.MainScreenBinding
import com.example.skilltest.db.NoteDatabase
import com.example.skilltest.db.Notes
import com.example.skilltest.fragment.BioMatricFragment
import com.example.skilltest.fragment.HomeFragment
import com.example.skilltest.fragment.PasswordFragment
import com.example.skilltest.repositry.NoteRespository
import com.example.skilltest.viewmodel.NoteViewModel
import com.example.skilltest.viewmodel.NoteViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.main_screen.*


class MainScreen : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: MainScreenBinding
    private lateinit var noteViewModel: NoteViewModel
    var  recycler_Notess:RecyclerView?=null
    var  text_title:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.main_screen)
        setSupportActionBar(toolbar)
            getSupportActionBar()!!.setTitle("");
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.text_color));
        nav_view.setNavigationItemSelectedListener(this)
        val  fab_button=findViewById<FloatingActionButton>(R.id.fab_button)
        recycler_Notess=findViewById<RecyclerView>(R.id.recycler_Notess)
        text_title=findViewById<TextView>(R.id.text_title)
        fab_button.setOnClickListener {
            val intent=Intent(this,AddNotesScreen::class.java)
            startActivity(intent)
            finish()
        }
        displayScreen(-1)

        val dao= NoteDatabase.getInstance(this!!).notesDao
        val repositoty= NoteRespository(dao)
        val factory= NoteViewModelFactory(repositoty)
        noteViewModel= ViewModelProvider(this,factory).get(NoteViewModel::class.java)
        binding.myViewModelMain=noteViewModel
        binding.lifecycleOwner=this
        initRecyclerView()
    }
    private fun displayNotesList(){
        noteViewModel.noteRespository.observe(this, Observer {
            Log.i("MYTAG",it.toString())
            recycler_Notess!!.adapter= MyNotesViewAdapter(it,{selectitem:Notes->listClick(selectitem)})
            text_title!!.setText(it.size.toString()+" Notes")
        })
    }
    private fun initRecyclerView(){
        recycler_Notess!!.layoutManager= GridLayoutManager(this,2)
        displayNotesList()
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
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun displayScreen(id: Int){

       // val fragment =  when (id){

        when (id){
           /* R.id.nav_home -> {
                supportFragmentManager.beginTransaction().replace(R.id.relativelayout, HomeFragment()).commit()
            }*/
            R.id.nav_pin -> {
                recycler_Notess!!.visibility=View.GONE
                supportFragmentManager.beginTransaction().replace(R.id.relativelayout, PasswordFragment()).commit()

            }

            R.id.nav_photos -> {
                recycler_Notess!!.visibility=View.GONE
                supportFragmentManager.beginTransaction().replace(R.id.relativelayout, BioMatricFragment()).commit()
            }

        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        displayScreen(item.itemId)

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


}
