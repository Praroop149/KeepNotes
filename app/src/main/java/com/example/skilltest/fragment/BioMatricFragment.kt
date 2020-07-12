package com.example.skilltest.fragment

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.skilltest.R
import com.example.skilltest.databinding.FragmentBiometricBinding
import com.example.spelltotinew.utils.MainApp


class BioMatricFragment : Fragment() {
    private lateinit var binding: FragmentBiometricBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_biometric,container,false)
        val  view=binding.root
        val switch1=view.findViewById<Switch>(R.id.switch1)
        if (MainApp.getPreference("bio_on","",activity!!).equals("1")){
            switch1.isChecked=true
        }
        else{
            switch1.isChecked=false
        }
        switch1?.setOnCheckedChangeListener({ _ , isChecked ->
            val message = if (isChecked) "Switch1:ON" else "Switch1:OFF"
            if (message.equals("Switch1:ON")){
                MainApp.savePreference("bio_on","1",activity!!)
                Toast.makeText(activity,"Biometric password enabled",Toast.LENGTH_SHORT).show()
            }
            else{
                MainApp.savePreference("bio_on","0",activity!!)
                Toast.makeText(activity,"Bioletric password disabled",Toast.LENGTH_SHORT).show()
            }
        })
        return view;
    }
}
