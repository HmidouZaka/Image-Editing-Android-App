package com.projectbyjanconnect.imageeditor.ui.componetns.gallery_components

import android.content.Context
import android.content.res.Configuration.CONTENTS_FILE_DESCRIPTOR
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.projectbyjanconnect.imageeditor.R
import com.projectbyjanconnect.imageeditor.ui.theme.ImageEditorTheme

@Composable
fun SelectImageDialog(
    image:()->Any,
    modifier: Modifier =Modifier,
    onDismiss:()->Unit,
    onClickSelect:()->Unit,
    context:Context
) {
    val imageRequest = remember {
        ImageRequest.Builder(context)
            .data(image())
            .crossfade(true)
            .placeholder(R.drawable.placeholder_image)
            .build()
    }
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = modifier
        ) {
            AsyncImage(
                model = imageRequest ,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
            )

            Text(text = "Do You want to Select This Image")
            ElevatedButton(
                onClick = onClickSelect
            ) {
                Text(text = "Select")
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SelectImageDialogPreview() {
    ImageEditorTheme {
        SelectImageDialog(
            modifier = Modifier.fillMaxWidth(),
            image = { R.drawable.example1 },
            onClickSelect = {},
            onDismiss = {},
            context = LocalContext.current
        )
    }
}