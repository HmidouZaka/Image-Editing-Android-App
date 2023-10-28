package com.projectbyjanconnect.imageeditor.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.awaitHorizontalDragOrCancellation
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.areStatusBarsVisible
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsIgnoringVisibility
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
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
import androidx.compose.ui.graphics.Color
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
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.projectbyjanconnect.imageeditor.R
import com.projectbyjanconnect.imageeditor.model.GalleryImage
import com.projectbyjanconnect.imageeditor.presintation.GalleryViewModel
import com.projectbyjanconnect.imageeditor.ui.componetns.gallery_components.SelectImageDialogForCompatScreens
import com.projectbyjanconnect.imageeditor.ui.componetns.gallery_components.SelectImageDialogForExpandedScreens
import com.projectbyjanconnect.imageeditor.ui.componetns.gallery_components.SelectImageDialogForMediumScreens
import com.projectbyjanconnect.imageeditor.utils.Response
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun GalleryScreen(
    modifier: Modifier = Modifier,
    activity: ComponentActivity,
    galleryViewModel: GalleryViewModel,
) {
    val imagesResponse by galleryViewModel.imageState.collectAsState()
    val imagesList = (imagesResponse as Response.Success).data ?: mutableListOf()
    val windowClass = calculateWindowSizeClass(activity = activity)
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> GalleryScreenForCompatScreen(modifier, imagesList,2) {
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
        WindowWidthSizeClass.Medium -> GalleryScreenForCompatScreen(modifier, imagesList,3) {
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
        WindowWidthSizeClass.Expanded -> GalleryScreenForCompatScreen(modifier, imagesList,4) {
                image: () -> Uri?,
                onDismiss: () -> Unit,
                onClickSelect: () -> Unit,
                context: Context,
                modifierDialog: Modifier,
            ->

            SelectImageDialogForExpandedScreens(
                context = context,
                image =  image(),
                onClickSelect = onClickSelect,
                onDismiss = onDismiss,
                modifier = modifierDialog
            )
        }
    }
}


@OptIn(ExperimentalLayoutApi::class, ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GalleryScreenForCompatScreen(
    modifier: Modifier = Modifier,
    list: List<GalleryImage>,
    countImagesPar:Int = 2,
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
                            if (countImages in (countImagesPar+1)..countImagesPar+3) {
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(((maxWidth - 20) / countImageInRow).dp)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            selectedImage = it
                                .getCompleteUrl()
                                .toString()
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
                        model = ImageRequest.Builder(context)
                            .data(it.getCompleteUrl())
                            .crossfade(true)
                            .placeholder(R.drawable.placeholder_image)
                            .crossfade(500)
                            .error(R.drawable.error_image)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
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
                selectedImage = null
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
