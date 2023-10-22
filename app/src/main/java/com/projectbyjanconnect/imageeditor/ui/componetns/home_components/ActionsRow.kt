package com.projectbyjanconnect.imageeditor.ui.componetns.home_components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projectbyjanconnect.imageeditor.R
import com.projectbyjanconnect.imageeditor.ui.theme.INTER_MEDIUM
import com.projectbyjanconnect.imageeditor.ui.theme.MONTSERRAT_MEDIUM

@Composable
fun <T>ActionsRow(
    modifier: Modifier = Modifier,
    listOfExamplesImages:List<T>,
    onClickGallery:()->Unit,
    onClickPicture:()->Unit,
    onClickDraw:()->Unit,
    onClickLink:()->Unit,
    onClickAI:()->Unit,
) {

    Column(modifier = modifier) {
        Text(
            text = "Options",
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 24.sp,
            fontFamily = INTER_MEDIUM,
            color = MaterialTheme.colorScheme.onBackground
        )

        val horizontalScrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(horizontalScrollState)
                .padding(8.dp)
        ) {

            val iconButtonModifier = Modifier
                .size(54.dp)
                .clip(CircleShape)




            OptionButton(
                modifier = iconButtonModifier,
                image = R.drawable.add_svgrepo_com,
                text = "Gallery",
                contentDescription = "Select Image From Gallery",
                onClick = onClickGallery
            )


            Spacer(modifier = Modifier.padding(8.dp))


            OptionButton(
                modifier = iconButtonModifier,
                image = R.drawable.camera_svgrepo_com,
                text = "Picture",
                contentDescription = "take photo using phone camera",
                onClick = onClickPicture
            )

            Spacer(modifier = Modifier.padding(8.dp))

            OptionButton(
                modifier = iconButtonModifier,
                image = R.drawable.draw_svgrepo_com,
                text = "Draw",
                contentDescription = "draw photo",
                onClick = onClickDraw
            )

            Spacer(modifier = Modifier.padding(8.dp))

            OptionButton(
                modifier = iconButtonModifier,
                image = R.drawable.link_svgrepo_com,
                text = "Link",
                contentDescription = "edit image using link",
                onClick = onClickLink
            )


            Spacer(modifier = Modifier.padding(8.dp))


            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = onClickAI,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)
                    ),
                    modifier = iconButtonModifier
                ) {
                    Text(
                        text = "AI",
                        fontFamily = INTER_MEDIUM,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = "AI",
                    fontFamily = MONTSERRAT_MEDIUM,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }


        }

        Text(
            text =if (listOfExamplesImages.isEmpty()) "Examples" else "Portfolio",
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 24.sp,
            fontFamily = INTER_MEDIUM,
            color = MaterialTheme.colorScheme.onBackground
        )
    }


}