package com.projectbyjanconnect.img_edit_feature.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.projectbyjanconnect.img_edit_feature.ui.screens.MainEditingScreen
import com.projectbyjanconnect.img_edit_feature.ui.screens.TargetImageNotFoundScreen

class EditImageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            if (intent.action == Intent.ACTION_SEND && intent.type?.startsWith("image/") == true) {
                val imageUri: Uri? = intent?.getParcelableExtra(Intent.EXTRA_STREAM) as? Uri
                if (imageUri != null) {
                    MainEditingScreen(
                        modifier = Modifier.fillMaxSize(),
                        uriImage = imageUri
                    )
                }else{
                    TargetImageNotFoundScreen(Modifier.fillMaxSize())
                }
            } else {
                TargetImageNotFoundScreen(Modifier.fillMaxSize())
            }
        }

    }
}