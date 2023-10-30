package com.projectbyjanconnect.imageeditor.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.projectbyjanconnect.imageeditor.R
import com.projectbyjanconnect.imageeditor.model.GalleryImage
import com.projectbyjanconnect.imageeditor.presintation.GalleryViewModel
import com.projectbyjanconnect.imageeditor.ui.componetns.gallery_components.ErrorScreenGallery
import com.projectbyjanconnect.imageeditor.ui.componetns.gallery_components.LoadingScreen
import com.projectbyjanconnect.imageeditor.ui.componetns.gallery_components.SelectImageDialogForCompatScreens
import com.projectbyjanconnect.imageeditor.ui.componetns.gallery_components.SelectImageDialogForExpandedScreens
import com.projectbyjanconnect.imageeditor.ui.componetns.gallery_components.SelectImageDialogForMediumScreens
import com.projectbyjanconnect.imageeditor.utils.Response
import com.projectbyjanconnect.img_edit_feature.ui.activities.EditImageActivity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun GalleryScreen(
    modifier: Modifier = Modifier,
    activity: ComponentActivity,
    galleryViewModel: GalleryViewModel,
) {
    val windowClass = calculateWindowSizeClass(activity = activity)
    val imagesResponse by galleryViewModel.imageState.collectAsState()




    when (imagesResponse) {
        is Response.Error -> {
            var isPermissionDialogShowed by rememberSaveable {
                mutableStateOf(false)
            }

            val activityRequest = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission(),
                onResult = {
                    if (it) {
                        galleryViewModel.loadImages()
                    } else {
                        if (!isPermissionDialogShowed) {
                            isPermissionDialogShowed = true
                        }
                    }
                })

            val message = (imagesResponse as Response.Error).message ?: "unKnow error"
            when (windowClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> ErrorScreenGallery(
                    modifier = Modifier.fillMaxSize(),
                    textSize = 17.sp,
                    imageSize = 100.dp,
                    onClickRetry = {
                        if (message == GalleryViewModel.PERMISSION_ERROR) {
                            activityRequest.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                        } else {
                            galleryViewModel.loadImages()
                        }
                    },
                    message = message,
                    buttonText = if (message == GalleryViewModel.PERMISSION_ERROR) "Get Permission" else "Retry"
                )

                WindowWidthSizeClass.Medium -> ErrorScreenGallery(
                    modifier = Modifier.fillMaxSize(),
                    textSize = 20.sp,
                    imageSize = 130.dp,
                    onClickRetry = {
                        if (message == GalleryViewModel.PERMISSION_ERROR) {
                            activityRequest.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                        } else {
                            galleryViewModel.loadImages()
                        }
                    },
                    message = message,
                    buttonText = if (message == GalleryViewModel.PERMISSION_ERROR) "Get Permission" else "Retry"
                )

                WindowWidthSizeClass.Expanded -> ErrorScreenGallery(
                    modifier = Modifier.fillMaxSize(),
                    textSize = 24.sp,
                    imageSize = 160.dp,
                    onClickRetry = {
                        if (message == GalleryViewModel.PERMISSION_ERROR) {
                            activityRequest.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                        } else {
                            galleryViewModel.loadImages()
                        }
                    },
                    message = message,
                    buttonText = if (message == GalleryViewModel.PERMISSION_ERROR) "Get Permission" else "Retry"
                )
            }

            if (isPermissionDialogShowed) {
                AlertDialog(
                    onDismissRequest = { isPermissionDialogShowed = false },
                    confirmButton = {
                        Button(onClick = {
                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            intent.data = Uri.fromParts("package",activity.packageName,null)
                            activity.startActivity(intent)
                        }) {
                            Text(text = "OK")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    dismissButton = { Button(onClick = {isPermissionDialogShowed = false }) {
                        Text(text = "CANCEL")
                    } },
                    icon = { Icon(imageVector = Icons.Default.Info, contentDescription = null, modifier = Modifier.size(70.dp)) },
                    title = { Text(text = "Important") },
                    text = {
                        Text(text = "Permission is important to fetch images in your phone so you can chose any image and edit it \nPERMISSION IS REQUIRED  ")
                    },
                    shape = RoundedCornerShape(8.dp),
                    tonalElevation = 8.dp
                )
            }
        }

        is Response.Loading -> {
            when (windowClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> LoadingScreen(
                    modifier = Modifier.fillMaxSize(),
                    progressSize = 30.dp
                )

                WindowWidthSizeClass.Medium -> LoadingScreen(
                    modifier = Modifier.fillMaxSize(),
                    progressSize = 40.dp
                )

                WindowWidthSizeClass.Expanded -> LoadingScreen(
                    modifier = Modifier.fillMaxSize(),
                    progressSize = 50.dp
                )
            }
        }

        is Response.Success -> {
            val imagesList = (imagesResponse as Response.Success).data ?: mutableListOf()

            when (windowClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> GalleryScreenForCompatScreen(
                    modifier,
                    imagesList,
                    2
                ) {
                        image: () -> Uri?,
                        onDismiss: () -> Unit,
                        onClickSelect: () -> Unit,
                        context: Context,
                        modifierDialog: Modifier,
                    ->

                    SelectImageDialogForCompatScreens(
                        context = context,
                        image = image(),
                        onClickSelect = onClickSelect,
                        onDismiss = onDismiss,
                        modifier = modifierDialog
                    )
                }

                WindowWidthSizeClass.Medium -> GalleryScreenForCompatScreen(
                    modifier,
                    imagesList,
                    3
                ) {
                        image: () -> Uri?,
                        onDismiss: () -> Unit,
                        onClickSelect: () -> Unit,
                        context: Context,
                        modifierDialog: Modifier,
                    ->

                    SelectImageDialogForMediumScreens(
                        context = context,
                        image = image(),
                        onClickSelect = onClickSelect,
                        onDismiss = onDismiss,
                        modifier = modifierDialog
                    )
                }

                WindowWidthSizeClass.Expanded -> GalleryScreenForCompatScreen(
                    modifier,
                    imagesList,
                    4
                ) {
                        image: () -> Uri?,
                        onDismiss: () -> Unit,
                        onClickSelect: () -> Unit,
                        context: Context,
                        modifierDialog: Modifier,
                    ->

                    SelectImageDialogForExpandedScreens(
                        context = context,
                        image = image(),
                        onClickSelect = onClickSelect,
                        onDismiss = onDismiss,
                        modifier = modifierDialog
                    )
                }
            }
        }
    }

}


@OptIn(ExperimentalLayoutApi::class, ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GalleryScreenForCompatScreen(
    modifier: Modifier = Modifier,
    list: List<GalleryImage>,
    countImagesPar: Int = 2,
    dialog: @Composable (
        image: () -> Uri?,
        onDismiss: () -> Unit,
        onClickSelect: () -> Unit,
        context: Context,
        modifier: Modifier,
    ) -> Unit,
) {
    val context = LocalContext.current
    val gridState = rememberLazyGridState()
    val isFirstImageVisible by remember {
        derivedStateOf { gridState.firstVisibleItemIndex == 0 }
    }

    var countImages by rememberSaveable {
        mutableStateOf(countImagesPar)
    }

    val countImageInRow by animateIntAsState(targetValue = countImages, label = "aaaaaa")

    val coroutine = rememberCoroutineScope()


    val maxWidth = LocalConfiguration.current.screenWidthDp




    Scaffold(
        modifier = modifier
            .windowInsetsPadding(WindowInsets(0, 0, 0, 0))
            .pointerInput(Unit) {
                var isAllowToChangeState = true
                detectHorizontalDragGestures(
                    onDragEnd = { isAllowToChangeState = true },
                    onDragStart = { isAllowToChangeState = true }
                ) { change, dragAmount ->
                    Log.d(
                        "ssssssssssssssssss",
                        "GalleryScreenForCompatScreen: ${change.previousPosition.x}  ${change.position.x} "
                    )
                    if (change.type == PointerType.Touch) {
                        if (change.previousPosition.x > change.position.x && isAllowToChangeState) {
                            if (countImages in countImagesPar..countImagesPar + 2) {
                                isAllowToChangeState = false
                                ++countImages
                            }
                        } else if (change.previousPosition.x < change.position.x && isAllowToChangeState) {
                            if (countImages in (countImagesPar + 1)..countImagesPar + 3) {
                                isAllowToChangeState = false
                                --countImages
                            }
                        }
                    }
                }
            },
        floatingActionButton = {
            if (!isFirstImageVisible) {
                FloatingActionButton(
                    onClick = {
                        coroutine.launch {
                            gridState.animateScrollToItem(0)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "go to top"
                    )
                }
            }
        }
    ) {


        var selectedImage: String? by rememberSaveable {
            mutableStateOf(null)
        }


        LazyVerticalGrid(
            modifier = modifier,
            contentPadding = PaddingValues(4.dp),
            columns = GridCells.Fixed(countImageInRow),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            state = gridState
        ) {
            item(
                span = { GridItemSpan(countImageInRow) }
            ) {
                Spacer(
                    Modifier.windowInsetsPadding(
                        WindowInsets.statusBars
                    )
                )
            }
            items(list, key = { it.id }) {
                var isPressed by remember {
                    mutableStateOf(false)
                }
                var isImageAvailable by remember {
                    mutableStateOf(true)
                }
                val image = remember {
                    ImageRequest.Builder(context)
                        .data(it.getCompleteUrl())
                        .crossfade(true)
                        .placeholder(R.drawable.placeholder_image)
                        .crossfade(500)
                        .error(R.drawable.error_image)
                        .build()
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(((maxWidth - 20) / countImageInRow).dp)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            Log.d("ssssssssssssssssss", " $isImageAvailable")
                            Log.d(
                                "ssssssssssssssssss", " ${
                                    it
                                        .getCompleteUrl()
                                }"
                            )
                            if (isImageAvailable) {
                                selectedImage = it
                                    .getCompleteUrl()
                                    .toString()


                            } else {
                                Toast
                                    .makeText(
                                        context,
                                        "this image is not available",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        }
                        .pointerInput(Unit) {
                            awaitEachGesture {
                                do {
                                    val painter = this.awaitPointerEvent()
                                    if (painter.type == PointerEventType.Press) {
                                        isPressed = true
                                    }
                                    if (painter.type == PointerEventType.Release) {
                                        isPressed = false
                                        break
                                    }
                                } while (painter.changes.any { it.pressed })

                            }
                        }
                ) {


                    AsyncImage(
                        model = image,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center,
                        onError = {
                            Log.d("ssssssssssssssssss", " $isImageAvailable")
                            isImageAvailable = false
                        }
                    )
                    AnimatedVisibility(
                        visible = isPressed,
                        enter = slideInVertically { it / 2 } + fadeIn(),
                        exit = slideOutVertically { it / 2 } + fadeOut()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f))
                        ) {
                            Text(
                                text = "#${it.id}",
                                color = MaterialTheme.colorScheme.background,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .align(Alignment.TopEnd),
                                fontSize = 14.sp,
                                textAlign = TextAlign.End,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = it.name,
                                color = MaterialTheme.colorScheme.background,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp)
                                    .align(Alignment.BottomCenter),
                                fontSize = 14.sp,
                                textAlign = TextAlign.Start,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
            item(
                span = { GridItemSpan(countImageInRow) }
            ) {
                Spacer(
                    Modifier.windowInsetsPadding(
                        WindowInsets.navigationBars
                    )
                )
            }
        }

        dialog(
            { selectedImage?.let { Uri.parse(it) } },
            { selectedImage = null },
            {
                selectedImage?.let {
                    val statEditingIntent = Intent(context,EditImageActivity::class.java)
                    statEditingIntent.action = Intent.ACTION_SEND
                    statEditingIntent.type = "image/*"
                    statEditingIntent.putExtra(Intent.EXTRA_STREAM,Uri.parse(selectedImage))
                    context.startActivity(statEditingIntent)
                    (context as ComponentActivity).finish()
                }
            },
            context,
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f))
        )
    }


}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GalleryScreenForCompatScreenPreview() {
    GalleryScreenForCompatScreen(
        modifier = Modifier.fillMaxSize(),
        list = listOf(
            GalleryImage(0, "", "", 0L),
            GalleryImage(0, "", "", 0L),
            GalleryImage(0, "", "", 0L),
            GalleryImage(0, "", "", 0L),
            GalleryImage(0, "", "", 0L),
            GalleryImage(0, "", "", 0L),
            GalleryImage(0, "", "", 0L),
            GalleryImage(0, "", "", 0L),
        ), dialog = { _, _, _, _, _ ->

        })
}
