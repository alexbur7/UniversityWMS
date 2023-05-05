package ru.alexbur.uikit.extentions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import ru.alexbur.uikit.theme.ListColor
import ru.alexbur.uikit.theme.Secondary

fun Modifier.backgroundItem(): Modifier {
    return this
        .shadow(24.dp, shape = RoundedCornerShape(24.dp))
        .clip(RoundedCornerShape(24.dp))
        .border(
            width = 0.5.dp,
            brush = Brush.verticalGradient(listOf(ListColor, Secondary)),
            shape = RoundedCornerShape(24.dp)
        )
        .background(color = ListColor)
}