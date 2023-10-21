package com.projectbyjanconnect.imageeditor.ui.componetns.home_components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projectbyjanconnect.imageeditor.R
import com.projectbyjanconnect.imageeditor.ui.theme.ImageEditorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    modifier: Modifier = Modifier,
    onClickLogo: () -> Unit,
    onClickSearch: () -> Unit,
    onClickSetting: () -> Unit,
) {
    val color = MaterialTheme.colorScheme.onBackground
    val modifierWithSize = Modifier.size(24.dp)
    TopAppBar(
        title = {},
        actions = {

            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "search",
                    modifier = modifierWithSize,
                    tint = color
                )
            }


            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.setting),
                    contentDescription = "setting",
                    modifier = modifierWithSize,
                    tint = color
                )
            }
        },
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.my_logo),
                contentDescription = "my logo",
                modifier = Modifier
                    .padding(8.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(width = 0.3.dp, color, CircleShape)
                    .clickable {

                    },
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
        },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        )
    )
}

@Preview(name = "top app bar", uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "top app bar night", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MainTopAppBarPreview() {
    ImageEditorTheme {
        MainTopAppBar(
            modifier = Modifier.fillMaxWidth(),
            onClickSetting = {},
            onClickSearch = {},
            onClickLogo = {}
        )
    }
}