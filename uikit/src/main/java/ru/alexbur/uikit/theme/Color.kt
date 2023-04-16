package ru.alexbur.uikit.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val PrimaryFirst = Color(0xFF6461FC)
val BackgroundSecond = Color(0xFF1E1BAD)
val ListColor = Color(0xFF8886F1)
val TextColor = Color(0xFFD0D0D0)
val SnackBarTextColor = Color(0xFF012550)
val PlaceHolderColor = Color(0xFF7E7E7E)
val Secondary = Color(0xFFEBF0F9)
val IconTint = Color(0xFF2C0383)
val SuccessColor = Color(0xFF58C64E)
val ErrorColor = Color(0xFFEB3A3A)
val BackgroundColor = Brush.verticalGradient(listOf(PrimaryFirst, BackgroundSecond))
val ShimmerPlaceHolderColor = Color(0xFFA3D4D4).copy(alpha = 0.8f)