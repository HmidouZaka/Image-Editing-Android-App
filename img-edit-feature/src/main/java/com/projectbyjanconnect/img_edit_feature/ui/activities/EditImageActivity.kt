package com.projectbyjanconnect.img_edit_feature.ui.activities

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.projectbyjanconnect.img_edit_feature.presintations.ImageEditingViewModel
import com.projectbyjanconnect.img_edit_feature.ui.screens.MainEditingScreen
import com.projectbyjanconnect.img_edit_feature.ui.screens.TargetImageNotFoundScreen
import java.io.File


class EditImageActivity : ComponentActivity() {

    private val imageEditingViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory(application).create(ImageEditingViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            if (intent.action == Intent.ACTION_SEND && intent.type?.startsWith("image/") == true) {
                val imageUri: Uri? = intent?.getParcelableExtra(Intent.EXTRA_STREAM) as? Uri

                if (imageUri != null) {
                    MainEditingScreen(
                        modifier = Modifier.fillMaxSize(),
                        viewModel = imageEditingViewModel
                    )
                }else{
                    TargetImageNotFoundScreen(Modifier.fillMaxSize())
                }
                LaunchedEffect(key1 = true ){
                    if ( imageEditingViewModel.imageState.value == null){
                        imageUri?.let {
                            val imageBitmap = convertUriToBitmap(imageUri)
                            Log.d("ssssssssssssssssss", "$imageBitmap")
                            imageEditingViewModel.setImageState(imageBitmap)
                        }
                    }
                }

            } else {
                TargetImageNotFoundScreen(Modifier.fillMaxSize())
            }
        }

    }

    private fun convertUriToBitmap(uri: Uri): Bitmap? {
        return try {

            val inputStream = contentResolver.openInputStream(uri)
            val image = BitmapFactory.decodeStream(inputStream)
            inputStream?.close()
            image
        } catch (ex: Exception) {
            val filePath = uri.toString()
            val sd = Environment.getExternalStorageDirectory().path
            val image = File(sd + filePath, "jjjjj.jpg")
            val bmOptions = BitmapFactory.Options()
            val bitmap = BitmapFactory.decodeFile(image.absolutePath, bmOptions)
            bitmap
        }
    }
}