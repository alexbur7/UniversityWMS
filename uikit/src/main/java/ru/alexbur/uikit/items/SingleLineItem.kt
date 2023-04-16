package ru.alexbur.uikit.items

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SingleLineItem(text: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.padding(start = 54.dp, top = 12.dp, bottom = 12.dp, end = 12.dp),
        text = text,
        style = TextStyle(color = Color.White, fontSize = 14.sp)
    )
}

@Preview
@Composable
internal fun SingleLineItemPreview() {
    SingleLineItem(text = "kqnfwqnfqwnfpwqnnfqw", modifier = Modifier.fillMaxWidth())
}