package com.projectbyjanconnect.imageeditor.model

import android.net.Uri
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

data class GalleryImage(
    val id:Int,
    private val url:String,
    val name:String,
    private val dateLong: Long
){


    fun getCompleteUrl():Uri{
        return Uri.parse(url)
    }


    data class DateInfo(
        val dateLong: Long,
        val date: Date,
        val formatDate:String
    )

    fun getImageDate():DateInfo{
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS")
        val currentDate = Calendar.getInstance()
        currentDate.timeInMillis = dateLong
        val dateFormat = formatter.format(currentDate.time)
        return DateInfo(dateLong,currentDate.time,dateFormat)
    }


}