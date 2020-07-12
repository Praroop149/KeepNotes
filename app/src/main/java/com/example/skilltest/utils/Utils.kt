package com.example.skilltest.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getProgressDrawable(context: Context):CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth=10f
        centerRadius=50f
        start()
    }
}
 fun ImageView.loadImage(url:String?,progressDrawable: CircularProgressDrawable){

 }