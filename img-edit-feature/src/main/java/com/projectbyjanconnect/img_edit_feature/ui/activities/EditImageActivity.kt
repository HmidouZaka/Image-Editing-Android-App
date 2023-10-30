package com.projectbyjanconnect.img_edit_feature.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text

class EditImageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val value = intent.getStringExtra("target_image_uri")
            Text(text = "$value")
        }

    }
}