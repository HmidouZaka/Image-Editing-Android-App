package com.projectbyjanconnect.img_edit_feature.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TargetImageNotFoundScreen(
    modifier: Modifier,
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "target img is not found",
            textAlign = TextAlign.Center
        )
    }

}