package com.projectbyjanconnect.img_edit_feature.presintations

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class ImageEditingViewModel(
    application:Application
) : AndroidViewModel(application) {


    private val _imageState  = MutableStateFlow<Bitmap?>(null)
    val imageState  = _imageState.asStateFlow()




    fun setImageState(imageBitmap:Bitmap?){
        _imageState.value = imageBitmap
    }





}