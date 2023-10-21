package com.projectbyjanconnect.imageeditor.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projectbyjanconnect.imageeditor.R
import com.projectbyjanconnect.imageeditor.ui.componetns.home_components.ActionsRow
import com.projectbyjanconnect.imageeditor.ui.componetns.home_components.MainTopAppBar
import com.projectbyjanconnect.imageeditor.ui.componetns.home_components.OptionButton
import com.projectbyjanconnect.imageeditor.ui.componetns.home_components.PortfolioImage
import com.projectbyjanconnect.imageeditor.ui.theme.INTER_BOLD
import com.projectbyjanconnect.imageeditor.ui.theme.INTER_MEDIUM
import com.projectbyjanconnect.imageeditor.ui.theme.ImageEditorTheme
import com.projectbyjanconnect.imageeditor.ui.theme.MONTSERRAT_MEDIUM
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    listOfExamplesImages:List<String> = listOf()
) {


    Scaffold(
        modifier = modifier,
        topBar = {
            MainTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                onClickLogo = {},
                onClickSearch = {},
                onClickSetting = {}
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
        {


            val lazyState = rememberLazyStaggeredGridState()

            CompositionLocalProvider(
                LocalOverscrollConfiguration provides null
            ) {
                if (listOfExamplesImages.isEmpty()){
                    val data = remember {
                        listOf(
                            R.drawable.example2,
                            R.drawable.example1,
                            R.drawable.example3,
                            R.drawable.example5,
                            R.drawable.example6,
                            R.drawable.example7,
                            R.drawable.example8,
                            R.drawable.example9,
                            R.drawable.example10,
                            R.drawable.example4
                        )
                    }

                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(2),
                        content = {

                            item(
                                span = StaggeredGridItemSpan.FullLine
                            ){
                                ActionsRow(Modifier.fillMaxWidth(),listOfExamplesImages)
                            }


                            items(data) {
                                PortfolioImage(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp)),
                                    image = it
                                )
                            }
                        },
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp),
                        verticalItemSpacing = 8.dp,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        state = lazyState
                    )
                }
                else{
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(2),
                        content = {
                            item(
                                span = StaggeredGridItemSpan.FullLine
                            ){
                                ActionsRow(Modifier.fillMaxWidth(),listOfExamplesImages)
                            }

                            items(
                                listOf("", "", "", "", "", "")
                            ) {
                                PortfolioImage(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .heightIn(min = 250.dp, 330.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    image = it
                                )
                            }
                        },
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp),
                        verticalItemSpacing = 8.dp,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        state = lazyState
                    )
                }
            }
        }
    }


}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    ImageEditorTheme(isCustomColorUsed = true) {
        HomeScreen(Modifier.fillMaxSize())
    }
}