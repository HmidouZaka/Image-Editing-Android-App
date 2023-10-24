package com.projectbyjanconnect.imageeditor.ui.screens

import android.content.res.Configuration
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.input.pointer.PointerType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.projectbyjanconnect.imageeditor.R
import com.projectbyjanconnect.imageeditor.model.GalleryImage
import com.projectbyjanconnect.imageeditor.presintation.GalleryViewModel
import com.projectbyjanconnect.imageeditor.utils.Response
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
        WindowWidthSizeClass.Compact -> GalleryScreenForCompatScreen(modifier, imagesList)
        WindowWidthSizeClass.Medium -> GalleryScreenForMediumScreen(modifier, imagesList)
        WindowWidthSizeClass.Expanded -> GalleryScreenForLargeScreen(modifier, imagesList)
    }
}


@Composable
fun GalleryScreenForCompatScreen(
    modifier: Modifier = Modifier,
    list: List<GalleryImage>,
) {
    val context = LocalContext.current
    val gridState = rememberLazyGridState()
    val isFirstImageNotVisible by remember {
        derivedStateOf { gridState.firstVisibleItemIndex != 0 }
    }
    var itemImageWidth by rememberSaveable {
        mutableStateOf(150)
    }
    val animateItemImageWidth by animateDpAsState(
        targetValue = itemImageWidth.dp,
        label = "animateWidth"
    )
    val coroutine = rememberCoroutineScope()


    Scaffold(
        modifier = modifier.pointerInput(Unit) {
            detectHorizontalDragGestures { change, dragAmount ->
                if (change.type ==  PointerType.Touch && change.position.x != itemImageWidth.toFloat()){
                    if (change.previousPosition.x > change.position.x){
                        if (itemImageWidth in 50..150) {
                            itemImageWidth -= 50
                        }
                    }else if (change.previousPosition.x < change.position.x){
                        if (itemImageWidth in 50..149) {
                            itemImageWidth += 50
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            AnimatedVisibility(visible = !isFirstImageNotVisible) {
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
        LazyVerticalGrid(
            modifier = modifier.padding(it),
            contentPadding = PaddingValues(8.dp),
            columns = GridCells.Adaptive(itemImageWidth.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            state = gridState
        ) {
            items(list) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(it.getCompleteUrl())
                        .crossfade(true)
                        .placeholder(R.drawable.example1)
                        .crossfade(1000)
                        .error(R.drawable.example8)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            }
        }
    }


}

@Composable
fun GalleryScreenForLargeScreen(
    modifier: Modifier = Modifier,
    list: List<GalleryImage>,
) {

}

@Composable
fun GalleryScreenForMediumScreen(
    modifier: Modifier = Modifier,
    list: List<GalleryImage>,
) {

}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GalleryScreenForCompatScreenPreview() {
    val context = LocalContext.current
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
        )
    )
}
