package com.projectbyjanconnect.imageeditor.ui.componetns.gallery_components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projectbyjanconnect.imageeditor.R
import com.projectbyjanconnect.imageeditor.ui.theme.ImageEditorTheme

@Composable
fun ErrorScreenGallery(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit,
    imageSize:Dp = 100.dp,
    textSize:TextUnit = 16.sp,
    buttonText:String = "Retry"
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_images),
            contentDescription = null,
            modifier = Modifier
                .size(imageSize)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Text(text = message, fontSize = textSize, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.padding(2.dp))
        Button(
            onClick = onClickRetry
        ) {
            Text(text = buttonText, fontSize = textSize)
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ErrorScreenGalleryPreview() {
    ImageEditorTheme {
        ErrorScreenGallery(
            modifier = Modifier.fillMaxSize(),
            message = "no permissions",
            onClickRetry = {}
        )
    }
}