package com.projectbyjanconnect.imageeditor.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.projectbyjanconnect.imageeditor.presintation.GalleryViewModel
import com.projectbyjanconnect.imageeditor.ui.screens.GalleryScreen
import com.projectbyjanconnect.imageeditor.ui.theme.ImageEditorTheme

class GalleryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application)).get<GalleryViewModel>()
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
            ImageEditorTheme(isCustomColorUsed = true) {
                GalleryScreen(
                    modifier = Modifier.fillMaxSize(),
                    activity = this,
                    galleryViewModel = viewModel
                )
            }
        }
    }

}