package com.projectbyjanconnect.imageeditor.presintation

import android.app.Application
import android.os.Environment
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectbyjanconnect.imageeditor.model.GalleryImage
import com.projectbyjanconnect.imageeditor.repositories.GalleryRepository
import com.projectbyjanconnect.imageeditor.utils.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Appendable
import java.util.Calendar
import kotlin.math.log

class GalleryViewModel(
    application: Application
) : AndroidViewModel(application) {

    private var repository: GalleryRepository? = null

    init {
        repository = GalleryRepository()
    }

    private val mutableImagesState :MutableStateFlow<Response<List<GalleryImage>>> = MutableStateFlow(Response.Loading())
    val imageState = mutableImagesState.asStateFlow()

    init {
        loadImages()
    }

    fun loadImages(){
        repository?.let {
            viewModelScope.launch {
                val result = it.loadImages(getApplication())
                result.forEach {
                    Log.d("ssssssssssssssss", "$it")
                }
                mutableImagesState.emit(Response.Success(data = result))
            }
        }
    }


    override fun onCleared() {
        repository = null
        super.onCleared()
    }
}