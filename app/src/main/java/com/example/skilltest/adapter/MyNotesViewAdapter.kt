package com.example.skilltest.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.skilltest.R
import com.example.skilltest.databinding.InfulateNotesBinding
import com.example.skilltest.db.Notes
import com.example.skilltest.generated.callback.OnClickListener

class MyNotesViewAdapter(private val nots: List<Notes>,private val clickListener:(Notes)->Unit):RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding:InfulateNotesBinding=DataBindingUtil.inflate(layoutInflater, R.layout.infulate_notes,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return nots.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(nots[position],clickListener)


    }
}
class MyViewHolder(val binding:InfulateNotesBinding):RecyclerView.ViewHolder(binding.root){
    private val cardNotes=binding.cardNotes
    fun bind(nots:Notes,clickListener:(Notes)->Unit) {

        val  string=nots.id
        binding.textTitle.text=nots.title
        binding.textDesc.text=nots.description
        binding.textCreateddate.text=nots.date
        binding.cardNotes.setOnClickListener {
            clickListener(nots)
        }
        if (string==0){
            binding.cardNotes!!.setCardBackgroundColor(Color.parseColor("#F3FFE2"));
        }

        if (string==1){
            binding.cardNotes!!.setCardBackgroundColor(Color.parseColor("#F9F9F9"));
        }
        if (string==3){
            binding.cardNotes!!.setCardBackgroundColor(Color.parseColor("#FFFBE0"));
        }
        if (string==4){
            binding.cardNotes!!.setCardBackgroundColor(Color.parseColor("#F3FFE2"));
        }
        else{

        }
    }

}