package ru.alexbur.uikit.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexbur.uikit.theme.BorderColor
import ru.alexbur.uikit.theme.IconTint

@Composable
fun UniversityWmsButton(
    modifier: Modifier,
    text: String,
    cornerRadius: Dp = 24.dp,
    onClick: () -> Unit
) {

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(color = IconTint)
            .border(
                width = 0.5.dp,
                brush = Brush.horizontalGradient(listOf(BorderColor, Color.Transparent)),
                shape = RoundedCornerShape(cornerRadius)
            )
            .clickable {
                onClick()
            }
            .padding(14.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(color = Color.White, fontSize = 16.sp, textAlign = TextAlign.Center)
        )
    }
}

@Preview
@Composable
fun UniversityWmsButtonPreview() {
    UniversityWmsButton(modifier = Modifier.fillMaxWidth(), text = "Войти") {}
}