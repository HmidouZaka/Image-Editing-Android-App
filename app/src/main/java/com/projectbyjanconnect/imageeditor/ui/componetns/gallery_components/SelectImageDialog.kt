package com.projectbyjanconnect.imageeditor.ui.componetns.gallery_components

import android.content.Context
import android.content.res.Configuration.CONTENTS_FILE_DESCRIPTOR
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.projectbyjanconnect.imageeditor.R
import com.projectbyjanconnect.imageeditor.ui.theme.INTER_BOLD
import com.projectbyjanconnect.imageeditor.ui.theme.INTER_LIGHT
import com.projectbyjanconnect.imageeditor.ui.theme.INTER_MEDIUM
import com.projectbyjanconnect.imageeditor.ui.theme.ImageEditorTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val DELAY_TIME = 200L


private suspend fun startDismissWithExitAnimation(
    onDismiss: () -> Unit,
    changeStateVisibility: () -> Unit,
) {
    changeStateVisibility()
    delay(DELAY_TIME)
    onDismiss()
}

@Composable
fun SelectImageDialogForCompatScreens(
    context: Context,
    image: Any?,
    onClickSelect: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {

    if (image != null) {
        val imageRequest = remember {
            ImageRequest.Builder(context)
                .data(image)
                .crossfade(true)
                .placeholder(R.drawable.placeholder_image)
                .build()
        }
        SelectImageDialogAnimator(onDismiss = onDismiss) {
            DialogContentForCompatScreens(modifier, imageRequest, onClickSelect)
        }
    }
}


@Composable
fun SelectImageDialogForMediumScreens(
    context: Context,
    image: Any?,
    onClickSelect: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {

    if (image != null) {
        val imageRequest = remember {
            ImageRequest.Builder(context)
                .data(image)
                .crossfade(true)
                .placeholder(R.drawable.placeholder_image)
                .build()
        }
        SelectImageDialogAnimator(onDismiss = onDismiss) {
            DialogContentForMediumScreens(modifier, imageRequest, onClickSelect)
        }
    }
}


@Composable
fun SelectImageDialogForExpandedScreens(
    context: Context,
    image: Any?,
    onClickSelect: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {

    if (image != null) {
        val imageRequest = remember {
            ImageRequest.Builder(context)
                .data(image)
                .crossfade(true)
                .placeholder(R.drawable.placeholder_image)
                .build()
        }
        SelectImageDialogAnimator(onDismiss = onDismiss) {
            DialogContentForExpandedScreens(modifier, imageRequest, onClickSelect)
        }
    }

}


@Composable
private fun SelectImageDialogAnimator(
    onDismiss: () -> Unit,
    content: @Composable () -> Unit,
) {

    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    var isVisible by rememberSaveable {
        mutableStateOf(false)
    }


    Dialog(onDismissRequest = {
        coroutineScope.launch {
            startDismissWithExitAnimation(onDismiss) {
                isVisible = false
            }
        }
    }, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures {
                        coroutineScope.launch {
                            startDismissWithExitAnimation(onDismiss) {
                                isVisible = false
                            }
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            AnimatedScaleInTransition(
                visible = isVisible,
                content = {
                    Box(modifier = Modifier.pointerInput(Unit) {
                        awaitEachGesture {
                            awaitPointerEvent().changes.forEach { it.consume() }
                        }
                    }) { content() }
                }
            )
        }

    }
    LaunchedEffect(key1 = true) {
        launch {
            delay(DELAY_TIME)
            isVisible = true
        }
    }

}

@Composable
private fun DialogContentForCompatScreens(
    modifier: Modifier,
    imageRequest: ImageRequest,
    onClickSelect: () -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        AsyncImage(
            model = imageRequest,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .height(300.dp),
        )

        Text(
            text = "Selected Image",
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 22.sp,
            fontFamily = INTER_BOLD,
        )
        Text(
            text = "Do You want to edit This Image",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            fontSize = 16.sp,
            fontFamily = INTER_LIGHT,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
        ElevatedButton(
            onClick = onClickSelect,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Select")
        }
    }
}

@Composable
private fun DialogContentForMediumScreens(
    modifier: Modifier,
    imageRequest: ImageRequest,
    onClickSelect: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .then(modifier)
    ) {
        AsyncImage(
            model = imageRequest,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(8.dp))
                .height(300.dp),
        )

        Column(
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
        ) {
            Text(
                text = "Selected Image",
                modifier = Modifier.padding(horizontal = 8.dp),
                fontSize = 35.sp,
                fontFamily = INTER_BOLD,
            )
            Text(
                text = "Do You want to edit This Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                fontSize = 24.sp,
                fontFamily = INTER_LIGHT,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
            ElevatedButton(
                onClick = onClickSelect,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Select", fontSize = 30.sp)
            }
        }
    }
}

@Composable
private fun DialogContentForExpandedScreens(
    modifier: Modifier,
    imageRequest: ImageRequest,
    onClickSelect: () -> Unit,
) = DialogContentForMediumScreens(modifier, imageRequest, onClickSelect)

@Composable
internal fun AnimatedScaleInTransition(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            animationSpec = tween(DELAY_TIME.toInt())
        ),
        exit = slideOutVertically(
            animationSpec = tween(DELAY_TIME.toInt())
        ),
        content = content
    )
}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun DialogContentForCompatScreensPreview() {
    ImageEditorTheme {
        val context = LocalContext.current
        DialogContentForCompatScreens(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background.copy(0.8f)),
            imageRequest = ImageRequest.Builder(context)
                .data("")
                .crossfade(true)
                .placeholder(R.drawable.placeholder_image)
                .build()
        ) {

        }
    }
}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, device = Devices.TABLET)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, device = Devices.TABLET)
@Composable
fun DialogContentForMediumScreensPreview() {
    ImageEditorTheme {
        val context = LocalContext.current
        DialogContentForMediumScreens(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background.copy(0.8f)),
            imageRequest = ImageRequest.Builder(context)
                .data("")
                .crossfade(true)
                .placeholder(R.drawable.placeholder_image)
                .build()
        ) {
        }
    }
}