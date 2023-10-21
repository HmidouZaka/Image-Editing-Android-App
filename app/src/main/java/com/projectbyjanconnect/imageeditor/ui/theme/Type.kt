package com.projectbyjanconnect.imageeditor.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.projectbyjanconnect.imageeditor.R


val MONTSERRAT_BOLD  = FontFamily(Font(R.font.montserrat_bold))
val MONTSERRAT_LIGHT  = FontFamily(Font(R.font.montserrat_light))
val MONTSERRAT_MEDIUM  = FontFamily(Font(R.font.montserrat_medium))
val INTER_BOLD  = FontFamily(Font(R.font.inter_bold))
val INTER_LIGHT  = FontFamily(Font(R.font.inter_light))
val INTER_MEDIUM  = FontFamily(Font(R.font.inter_medium))
val INTER_REGULAR  = FontFamily(Font(R.font.inter_regular))
val MONTSERRAT_THIN  = FontFamily(Font(R.font.inter_thin))

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */,

)