package com.projectbyjanconnect.imageeditor.ui.componetns.home_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projectbyjanconnect.imageeditor.R
import com.projectbyjanconnect.imageeditor.ui.theme.MONTSERRAT_LIGHT
import com.projectbyjanconnect.imageeditor.ui.theme.MONTSERRAT_MEDIUM


@Composable
fun OptionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    contentDescription: String,
    image: Int,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = onClick,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)
            ),
            modifier = modifier
        ) {
            Icon(
                painter = painterResource(id = image),
                contentDescription = contentDescription,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = text,
            fontFamily = MONTSERRAT_MEDIUM,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}