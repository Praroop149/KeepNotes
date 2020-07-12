package com.example.spelltotinew.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by anupsingh on 03/12/18.
 */
class MainApp : Application() {

    internal var mInstance:MainApp?=null


   companion object {
       fun savePreference( key:String,value:String,context: Context){
           try{
               var sharedPreferences: SharedPreferences?=null
               sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context)
               var editor : SharedPreferences.Editor=sharedPreferences.edit()
               editor.putString(key, value)
               editor.commit()

           }catch (e:Exception){
                e.printStackTrace()
           }

       }
       fun getPreference(key:String,value1:String,context: Context):String{
           var value: String? = ""
           var sharedPreferences: SharedPreferences?=null
           sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context)
           try {
               value = sharedPreferences.getString(key, value1)

           } catch (e: Exception) {
               e.printStackTrace()
           }

           return value!!
       }


    }


    override fun onCreate() {
        super.onCreate()
        mInstance=this

    }

}