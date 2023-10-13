package com.projectbyjanconnect.imageeditor.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.projectbyjanconnect.imageeditor.ui.screens.StartUpScreenWidthCompact
import com.projectbyjanconnect.imageeditor.ui.screens.StartUpScreenWidthExpanded
import com.projectbyjanconnect.imageeditor.ui.screens.StartUpScreenWidthMedium
import com.projectbyjanconnect.imageeditor.ui.theme.ImageEditorTheme

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageEditorTheme {

            }
        }
    }

}