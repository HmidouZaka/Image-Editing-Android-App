package com.projectbyjanconnect.img_edit_feature.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.projectbyjanconnect.img_edit_feature.R

@Composable
fun MainEditingScreen(
    modifier: Modifier = Modifier,
    uriImage:Uri
) {

    var isImageAvailable by rememberSaveable {
        mutableStateOf(true)
    }
    if (isImageAvailable){
        val context = LocalContext.current

        Column(
            modifier = modifier
        ) {


            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(uriImage)
                    .crossfade(true)
                    .error(R.drawable.error_image)
                    .placeholder(R.drawable.error_image)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .height(130.dp)
                    .background(MaterialTheme.colorScheme.background),

            ) {

            }

        }
    }else{
        TargetImageNotFoundScreen(modifier = modifier)
    }
}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun MainEditingScreen() {
    MainEditingScreen(Modifier.fillMaxSize(), uriImage = Uri.parse(""))
}