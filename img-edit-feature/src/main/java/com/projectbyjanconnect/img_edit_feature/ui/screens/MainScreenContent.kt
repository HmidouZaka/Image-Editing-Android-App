package com.projectbyjanconnect.img_edit_feature.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.projectbyjanconnect.img_edit_feature.R


@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    image: Any,
) {
    Column(
        modifier = modifier.background(Color.White)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(2.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        val context = LocalContext.current
        val scrollerState = rememberScrollState()
        val imageRequest = remember {
            ImageRequest.Builder(context = context).apply {
                data(image)
            }.build()
        }
        Box(
            modifier = Modifier.weight(1f)
        ) {
            AsyncImage(
                model = imageRequest,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .horizontalScroll(scrollerState)
                .padding(horizontal = 4.dp, vertical = 8.dp),
        ) {

            for (e in 0..10) {
                if (e != 0) {
                    Spacer(modifier = Modifier.width(4.dp))
                }
                Column(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF1D1D1D))
                        .border(
                            width = 0.2.dp,
                            color = Color(0xFF797979),
                            RoundedCornerShape(8.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ThumbUp,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "edit",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W300,
                        fontFamily = FontFamily(Font(R.font.inter_light))

                    )
                }

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenContentPreview() {

    MainScreenContent(
        modifier = Modifier.fillMaxSize(),
        R.drawable.placeholder_image
    )
}