package ru.alexbur.uikit.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexbur.uikit.theme.BackgroundSecond
import ru.alexbur.uikit.theme.TextColor

@Composable
fun UniversityWmsButton(
    modifier: Modifier,
    text: String,
    cornerRadius: Dp = 24.dp,
    onClick: () -> Unit
) {

    Column(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(listOf(BackgroundSecond, MaterialTheme.colorScheme.primary)),
                shape = RoundedCornerShape(cornerRadius)
            )
            .padding(14.dp)
            .clickable {
                onClick()
            }
    ) {

        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(color = TextColor, fontSize = 16.sp, textAlign = TextAlign.Center)
        )
    }
}

@Preview
@Composable
fun UniversityWmsButtonPreview() {
    UniversityWmsButton(modifier = Modifier.fillMaxWidth(), text = "Войти") {}
}