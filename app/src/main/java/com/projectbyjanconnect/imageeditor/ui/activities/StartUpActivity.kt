package com.projectbyjanconnect.imageeditor.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.projectbyjanconnect.imageeditor.ui.screens.StartUpScreenWidthCompact
import com.projectbyjanconnect.imageeditor.ui.screens.StartUpScreenWidthExpanded
import com.projectbyjanconnect.imageeditor.ui.screens.StartUpScreenWidthMedium
import com.projectbyjanconnect.imageeditor.ui.theme.ImageEditorTheme

class StartUpActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
            ImageEditorTheme(isCustomColorUsed = true) {

                val classSize = calculateWindowSizeClass(this)

                val startEditingIntent = Intent(this,HomeActivity::class.java)
                startEditingIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                when (classSize.widthSizeClass){
                    WindowWidthSizeClass.Compact -> {
                        StartUpScreenWidthCompact(Modifier.fillMaxSize()){
                            startActivity(startEditingIntent)
                        }
                    }
                    WindowWidthSizeClass.Medium -> {
                        StartUpScreenWidthMedium(Modifier.fillMaxSize()){
                            startActivity(startEditingIntent)

                        }
                    }
                    WindowWidthSizeClass.Expanded -> {
                        StartUpScreenWidthExpanded(Modifier.fillMaxSize()){
                            startActivity(startEditingIntent)
                        }
                    }
                }
            }
        }
    }

}