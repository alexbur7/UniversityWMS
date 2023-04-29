package ru.alexbur.uikit.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val ListColor = Color(0xFF8A96B5)
val TextColor = Color(0xFFD0D0D0)
val SnackBarTextColor = Color(0xFF012550)
val PlaceHolderColor = Color(0xFFBCCBDF)
val BorderColor = Color(0xFFC3CDE5)
val Secondary = Color(0xFFEBF0F9)
val IconTint = Color(0xFF303D5F)
val IconBackColor = Color(0xFFBCCBDF)
val SuccessColor = Color(0xFF58C64E)
val ErrorColor = Color(0xFFEB3A3A)
val FirstBackColorColor = Color(0xFFDDECF4)
val SecondBackColorColor = Color(0xFF7585AF)
val ThirdBackColorColor = Color(0xFF1C2948)
val BackgroundColor = Brush.verticalGradient(listOf(ThirdBackColorColor, SecondBackColorColor, FirstBackColorColor))
val ShimmerPlaceHolderColor = Color(0xFFA3D4D4).copy(alpha = 0.8f)