package com.projectbyjanconnect.imageeditor.ui.screens

import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projectbyjanconnect.imageeditor.R
import com.projectbyjanconnect.imageeditor.ui.activities.GalleryActivity
import com.projectbyjanconnect.imageeditor.ui.componetns.home_components.ActionsRow
import com.projectbyjanconnect.imageeditor.ui.componetns.home_components.LargeOptionButton
import com.projectbyjanconnect.imageeditor.ui.componetns.home_components.MainTopAppBar
import com.projectbyjanconnect.imageeditor.ui.componetns.home_components.PortfolioImage
import com.projectbyjanconnect.imageeditor.ui.theme.INTER_MEDIUM
import com.projectbyjanconnect.imageeditor.ui.theme.ImageEditorTheme
import com.projectbyjanconnect.imageeditor.ui.theme.MONTSERRAT_MEDIUM


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenForNormalPhones(
    modifier: Modifier = Modifier,
    listOfExamplesImages: List<String> = listOf(),
) {

    val context = LocalContext.current
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
                if (listOfExamplesImages.isEmpty()) {
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
                        columns = StaggeredGridCells.Adaptive(150.dp),
                        content = {

                            item(
                                span = StaggeredGridItemSpan.FullLine
                            ) {
                                ActionsRow(
                                    modifier = Modifier.fillMaxWidth(),
                                    listOfExamplesImages = listOfExamplesImages,
                                    onClickAI = {},
                                    onClickDraw = {},
                                    onClickGallery = {
                                        val intentGallery =
                                            Intent(context, GalleryActivity::class.java)
                                        context.startActivity(intentGallery)
                                    },
                                    onClickLink = {},
                                    onClickPicture = {}
                                )
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
                } else {
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Adaptive(150.dp),
                        content = {
                            item(
                                span = StaggeredGridItemSpan.FullLine
                            ) {
                                ActionsRow(
                                    modifier = Modifier.fillMaxWidth(),
                                    listOfExamplesImages = listOfExamplesImages,
                                    onClickAI = {},
                                    onClickDraw = {},
                                    onClickGallery = {
                                        val intentGallery =
                                            Intent(context, GalleryActivity::class.java)
                                        context.startActivity(intentGallery)
                                    },
                                    onClickLink = {},
                                    onClickPicture = {}
                                )
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenForLargeScreens(
    modifier: Modifier = Modifier,
    listOfExamplesImages: List<String> = listOf(),
) {

    val context = LocalContext.current
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
        {

            val lazyState = rememberLazyStaggeredGridState()

            Column(
                modifier = Modifier
                    .weight(0.4f)
                    .verticalScroll(rememberScrollState())
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(20.dp))
                            .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = "",
                            modifier = Modifier.size(35.dp),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Search for Images",
                            fontSize = 30.sp,
                        )
                    }
                    IconButton(
                        onClick = {},
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)
                        ),
                        modifier = Modifier
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.setting),
                            contentDescription = "",
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                Text(
                    text = "Options",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    fontSize = 40.sp,
                    fontFamily = INTER_MEDIUM,
                    color = MaterialTheme.colorScheme.onBackground
                )


                LargeOptionButton(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
                        .padding(12.dp),
                    text = "About Developer",
                    contentDescription = "",
                    image = R.drawable.my_logo,
                    textSize = 30.sp,
                    imageSize = 35.dp
                )

                Spacer(modifier = Modifier.height(16.dp))

                LargeOptionButton(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
                        .padding(12.dp),
                    text = "Select Image",
                    contentDescription = "",
                    image = R.drawable.add_svgrepo_com,
                    textSize = 30.sp,
                    imageSize = 35.dp
                )

                Spacer(modifier = Modifier.height(16.dp))
                LargeOptionButton(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
                        .padding(12.dp),
                    text = "Take Picture",
                    contentDescription = "",
                    image = R.drawable.camera_svgrepo_com,
                    textSize = 30.sp,
                    imageSize = 35.dp
                )
                Spacer(modifier = Modifier.height(16.dp))

                LargeOptionButton(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
                        .padding(12.dp),
                    text = "Draw Image",
                    contentDescription = "",
                    image = R.drawable.draw_svgrepo_com,
                    textSize = 30.sp,
                    imageSize = 35.dp
                )
                Spacer(modifier = Modifier.height(16.dp))

                LargeOptionButton(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
                        .padding(12.dp),
                    text = "Select By Link",
                    contentDescription = "",
                    image = R.drawable.link_svgrepo_com,
                    textSize = 30.sp,
                    imageSize = 35.dp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier =  Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "AI",
                        fontFamily = MONTSERRAT_MEDIUM,
                        fontSize = 35.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Generate Image By AI", fontSize = 30.sp)
                }

            }


            Column(
                modifier = Modifier
                    .weight(0.6f)
            ){
                Text(
                    text =if (listOfExamplesImages.isEmpty()) "Examples" else "Portfolio",
                    modifier = Modifier.padding(vertical = 16.dp),
                    fontSize = 40.sp,
                    fontFamily = INTER_MEDIUM,
                    color = MaterialTheme.colorScheme.onBackground
                )
                CompositionLocalProvider(
                    LocalOverscrollConfiguration provides null
                ) {
                    if (listOfExamplesImages.isEmpty())
                    {
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
                            columns = StaggeredGridCells.Adaptive(300.dp),
                            content = {
                                items(data) {
                                    PortfolioImage(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(8.dp)),
                                        image = it
                                    )
                                }
                            },
                            modifier = Modifier
                                .fillMaxSize(),
                            contentPadding = PaddingValues(8.dp),
                            verticalItemSpacing = 8.dp,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            state = lazyState
                        )
                    }
                    else
                    {
                        LazyVerticalStaggeredGrid(
                            columns = StaggeredGridCells.Adaptive(300.dp),
                            content = {
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
                            modifier = Modifier
                                .fillMaxSize(),
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


}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenForMediumScreens(
    modifier: Modifier = Modifier,
    listOfExamplesImages: List<String> = listOf(),
) {


    val context = LocalContext.current
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
        {

            val lazyState = rememberLazyStaggeredGridState()

            Column(
                modifier = Modifier
                    .weight(0.4f)
                    .verticalScroll(rememberScrollState())
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(20.dp))
                            .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = "",
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Search Images")
                    }
                    IconButton(
                        onClick = {},
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)
                        ),
                        modifier = Modifier
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.setting),
                            contentDescription = "",
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                Text(
                    text = "Options",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    fontSize = 24.sp,
                    fontFamily = INTER_MEDIUM,
                    color = MaterialTheme.colorScheme.onBackground
                )


                LargeOptionButton(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
                        .padding(12.dp),
                    text = "About Developer",
                    contentDescription = "",
                    image = R.drawable.my_logo
                )

                Spacer(modifier = Modifier.height(16.dp))

                LargeOptionButton(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
                        .padding(12.dp),
                    text = "Select Image",
                    contentDescription = "",
                    image = R.drawable.add_svgrepo_com
                )

                Spacer(modifier = Modifier.height(16.dp))
                LargeOptionButton(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
                        .padding(12.dp),
                    text = "Take Picture",
                    contentDescription = "",
                    image = R.drawable.camera_svgrepo_com
                )
                Spacer(modifier = Modifier.height(16.dp))

                LargeOptionButton(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
                        .padding(12.dp),
                    text = "Draw Image",
                    contentDescription = "",
                    image = R.drawable.draw_svgrepo_com
                )
                Spacer(modifier = Modifier.height(16.dp))

                LargeOptionButton(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
                        .padding(12.dp),
                    text = "Select By Link",
                    contentDescription = "",
                    image = R.drawable.link_svgrepo_com
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier =  Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "AI",
                        fontFamily = MONTSERRAT_MEDIUM,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Generate Image By AI")
                }

            }


            Column(
                modifier = Modifier
                    .weight(0.6f)
            ){
                Text(
                    text =if (listOfExamplesImages.isEmpty()) "Examples" else "Portfolio",
                    modifier = Modifier.padding(vertical = 16.dp),
                    fontSize = 24.sp,
                    fontFamily = INTER_MEDIUM,
                    color = MaterialTheme.colorScheme.onBackground
                )
                CompositionLocalProvider(
                    LocalOverscrollConfiguration provides null
                ) {
                    if (listOfExamplesImages.isEmpty())
                    {
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
                            columns = StaggeredGridCells.Adaptive(150.dp),
                            content = {
                                items(data) {
                                    PortfolioImage(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(8.dp)),
                                        image = it
                                    )
                                }
                            },
                            modifier = Modifier
                                .fillMaxSize(),
                            contentPadding = PaddingValues(8.dp),
                            verticalItemSpacing = 8.dp,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            state = lazyState
                        )
                    }
                    else
                    {
                        LazyVerticalStaggeredGrid(
                            columns = StaggeredGridCells.Adaptive(150.dp),
                            content = {
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
                            modifier = Modifier
                                .fillMaxSize(),
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

}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    ImageEditorTheme(isCustomColorUsed = true) {
        HomeScreenForNormalPhones(Modifier.fillMaxSize())
    }
}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, device = Devices.AUTOMOTIVE_1024p)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, device = Devices.AUTOMOTIVE_1024p)
@Composable
fun HomeScreenPreview4() {
    ImageEditorTheme(isCustomColorUsed = true) {
        HomeScreenForMediumScreens(Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, device = Devices.TV_1080p)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, device = Devices.TV_1080p)
@Composable
fun HomeScreenPreview5() {
    ImageEditorTheme(isCustomColorUsed = true) {
        HomeScreenForLargeScreens(Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, device = Devices.FOLDABLE)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, device = Devices.FOLDABLE)
@Composable
fun HomeScreenPreview7() {
    ImageEditorTheme(isCustomColorUsed = true) {
        HomeScreenForMediumScreens(Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, device = Devices.NEXUS_7)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, device = Devices.NEXUS_7)
@Composable
fun HomeScreenPreview6() {
    ImageEditorTheme(isCustomColorUsed = true) {
        HomeScreenForNormalPhones(Modifier.fillMaxSize())
    }
}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, device = Devices.TABLET)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, device = Devices.TABLET)
@Composable
fun HomeScreenPreview2() {
    ImageEditorTheme(isCustomColorUsed = true) {
        HomeScreenForMediumScreens(Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, device = Devices.PIXEL_XL)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, device = Devices.PIXEL_XL)
@Composable
fun HomeScreenPreview3() {
    ImageEditorTheme(isCustomColorUsed = true) {
        HomeScreenForNormalPhones(Modifier.fillMaxSize())
    }
}
