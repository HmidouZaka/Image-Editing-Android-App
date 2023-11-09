package com.projectbyjanconnect.img_edit_feature.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.projectbyjanconnect.img_edit_feature.presintations.ImageEditingViewModel

@Composable
fun MainEditingScreen(
    modifier: Modifier = Modifier,
    viewModel: ImageEditingViewModel,
) {

    val imageState by viewModel.imageState.collectAsState()

    if (imageState != null) {






    } else {
        TargetImageNotFoundScreen(modifier)
    }


}

