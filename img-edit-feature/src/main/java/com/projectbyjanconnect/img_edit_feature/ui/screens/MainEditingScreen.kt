package com.projectbyjanconnect.img_edit_feature.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.projectbyjanconnect.img_edit_feature.presintations.ImageEditingViewModel

@Composable
fun MainEditingScreen(
    modifier: Modifier = Modifier,
    viewModel: ImageEditingViewModel,
) {

    val imageState by viewModel.imageState.collectAsState()

    if (imageState != null){
        val context = LocalContext.current
        val imageRequest = remember{
            ImageRequest.Builder(context = context).apply {
                data(imageState)
            }.build()
        }
        AsyncImage(model = imageRequest, contentDescription = null, modifier = modifier)
    }else{
        TargetImageNotFoundScreen(modifier)
    }


}

