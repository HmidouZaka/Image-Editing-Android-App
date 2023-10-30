package com.projectbyjanconnect.imageeditor.presintation

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.os.Environment
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectbyjanconnect.imageeditor.model.GalleryImage
import com.projectbyjanconnect.imageeditor.repositories.GalleryRepository
import com.projectbyjanconnect.imageeditor.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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



    private suspend fun checkPermissions() : Boolean{
        return withContext(Dispatchers.Main){
            ContextCompat.checkSelfPermission(getApplication(),Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        }
    }

    init {
        loadImages()
    }

    fun loadImages(){
        repository?.let {
            viewModelScope.launch {
                if (mutableImagesState.value !is Response.Loading){
                    mutableImagesState.emit(Response.Loading())
                }
                if (checkPermissions()){
                    val result = it.loadImages(getApplication())
                    if (result.isEmpty()){
                        mutableImagesState.emit(Response.Error(message = "you don't have any image to edit it"))
                    }else{
                        mutableImagesState.emit(Response.Success(data = result))
                    }
                }else{
                    mutableImagesState.emit(Response.Error(message = PERMISSION_ERROR))
                }
            }
        }
    }

    companion object{
        val PERMISSION_ERROR = "please gave the app permissions to show your images"
    }


    override fun onCleared() {
        repository = null
        super.onCleared()
    }
}