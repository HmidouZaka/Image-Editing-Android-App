package com.projectbyjanconnect.imageeditor.ui.screens




import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projectbyjanconnect.imageeditor.R
import com.projectbyjanconnect.imageeditor.ui.theme.ImageEditorTheme

@Composable
fun StartUpScreenWidthCompact(
    modifier: Modifier = Modifier,
    onClickStart:()->Unit
) {

    val height = LocalConfiguration.current.screenHeightDp
    Box(
        modifier = modifier
            .paint(
                painterResource(id = R.drawable.backround_image),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )
            .background(
                Color.Black.copy(alpha = .5f)
            )
    ) {

        val animateRotation = rememberInfiniteTransition( label = "animateRotation" )


        val rotateAnimation by animateRotation.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                tween(durationMillis = 1000, delayMillis = 1000, LinearOutSlowInEasing),
                repeatMode = RepeatMode.Reverse),
            label = "rotate"
        )

        Box(
            modifier = Modifier
                .padding(
                    top = (height / 3).dp
                )
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {



            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                alignment = Alignment.Center,
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .graphicsLayer {
                        this.translationY = rotateAnimation * 1000f
                    }
                    .clip(CircleShape)
                ,
                contentScale = ContentScale.Crop
            )



        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .padding(bottom = 140.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = "Start Editing Now",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_bold))
            )


            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = "you can edit any image and draw your custome images now click start to beginning",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_light))
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Button(
                onClick = onClickStart,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "Start",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_light)),
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}


@Composable
fun StartUpScreenWidthMedium(
    modifier: Modifier = Modifier,
    onClickStart:()->Unit
) {
    Row(
        modifier = modifier
            .paint(
                painterResource(id = R.drawable.backround_image),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )
            .background(
                Color.Black.copy(alpha = .5f)
            )
    ) {

        val animateRotation = rememberInfiniteTransition( label = "animateRotation" )


        val rotateAnimation by animateRotation.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                tween(durationMillis = 1000, delayMillis = 1000, LinearOutSlowInEasing),
                repeatMode = RepeatMode.Reverse),
            label = "rotate"
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {



            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                alignment = Alignment.Center,
                modifier = Modifier
                    .size(250.dp)
                    .clip(CircleShape)
                    .graphicsLayer {
                        this.translationY = rotateAnimation * 1000f
                    }
                    .clip(CircleShape)
                ,
                contentScale = ContentScale.Crop
            )



        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Text(
                text = "Start Editing Now",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 35.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_bold))
            )


            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = "you can edit any image and draw your custome images now click start to beginning",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_light))
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Button(
                onClick = onClickStart,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.width(200.dp)
            ) {
                Text(
                    text = "Start",
                    fontSize = 28.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_light)),
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}


@Composable
fun StartUpScreenWidthExpanded(
    modifier: Modifier = Modifier,
    onClickStart:()->Unit
) {

    Row(
        modifier = modifier
            .paint(
                painterResource(id = R.drawable.backround_image),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )
            .background(
                Color.Black.copy(alpha = .5f)
            )
    ) {

        val animateRotation = rememberInfiniteTransition( label = "animateRotation" )


        val rotateAnimation by animateRotation.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                tween(durationMillis = 1000, delayMillis = 1000, LinearOutSlowInEasing),
                repeatMode = RepeatMode.Reverse),
            label = "rotate"
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {



            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                alignment = Alignment.Center,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .graphicsLayer {
                        this.translationY = rotateAnimation * 1000f
                    }
                    .clip(CircleShape)
                ,
                contentScale = ContentScale.Crop
            )



        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Text(
                text = "Start Editing Now",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_bold))
            )


            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = "you can edit any image and draw your custome images now click start to beginning",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_light))
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Button(
                onClick = onClickStart,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.width(200.dp)
            ) {
                Text(
                    text = "Start",
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_light)),
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, name = "light")
@Composable
fun StartUpScreenPreviewWidthCompact() {
    ImageEditorTheme(isCustomColorUsed = true) {
        StartUpScreenWidthCompact(Modifier.fillMaxSize()){}
    }
}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, name = "night", device = Devices.TABLET)
@Composable
fun StartUpScreenPreviewWidthMedium() {
    ImageEditorTheme(isCustomColorUsed = true) {
        StartUpScreenWidthMedium(Modifier.fillMaxSize()){}
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, name = "night", device = Devices.AUTOMOTIVE_1024p)
@Composable
fun StartUpScreenPreviewWithExpanded() {
    ImageEditorTheme(isCustomColorUsed = true) {
        StartUpScreenWidthExpanded(Modifier.fillMaxSize()){}
    }
}

