package com.projectbyjanconnect.imageeditor.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import com.projectbyjanconnect.imageeditor.ui.screens.HomeScreenForLargeScreens
import com.projectbyjanconnect.imageeditor.ui.screens.HomeScreenForMediumScreens
import com.projectbyjanconnect.imageeditor.ui.screens.HomeScreenForNormalPhones
import com.projectbyjanconnect.imageeditor.ui.screens.StartUpScreenWidthCompact
import com.projectbyjanconnect.imageeditor.ui.screens.StartUpScreenWidthExpanded
import com.projectbyjanconnect.imageeditor.ui.screens.StartUpScreenWidthMedium
import com.projectbyjanconnect.imageeditor.ui.theme.ImageEditorTheme

class HomeActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageEditorTheme(isCustomColorUsed = true) {
                val windowSizeClass = calculateWindowSizeClass(activity = this)
                if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact){
                    HomeScreenForNormalPhones(modifier = Modifier.fillMaxSize())
                }else if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Medium){
                    HomeScreenForMediumScreens(modifier = Modifier.fillMaxSize())
                }else if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded){
                    Log.d("ssssssssssssssssssssssssssssssssssss", "onCreate: ")
                    HomeScreenForLargeScreens(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }

}