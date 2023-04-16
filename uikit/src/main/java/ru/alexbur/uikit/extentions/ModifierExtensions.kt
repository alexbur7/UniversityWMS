package ru.alexbur.uikit.extentions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import ru.alexbur.uikit.theme.ListColor
import ru.alexbur.uikit.theme.Secondary

fun Modifier.backgroundItem(): Modifier {
    return this
        .clip(RoundedCornerShape(24.dp))
        .border(
            width = 1.dp,
            brush = Brush.verticalGradient(listOf(Secondary, ListColor)),
            shape = RoundedCornerShape(24.dp)
        )
        .background(color = ListColor)
}