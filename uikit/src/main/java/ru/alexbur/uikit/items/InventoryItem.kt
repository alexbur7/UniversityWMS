package ru.alexbur.uikit.items

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexbur.uikit.R
import ru.alexbur.uikit.extentions.backgroundItem
import ru.alexbur.uikit.theme.IconTint
import ru.alexbur.uikit.theme.Secondary
import ru.alexbur.uikit.theme.TextColor

@Composable
fun InventoryItem(
    modifier: Modifier,
    title: String,
    count: String,
    @DrawableRes
    startIcon: Int,
    barcode: String
) {
    var isExpandable by remember { mutableStateOf(false) }
    Row(
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 20.dp)
            .backgroundItem()
            .clickable {
                isExpandable = !isExpandable
            }
            .padding(12.dp)
    ) {
        Icon(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .align(Alignment.CenterVertically)
                .weight(1f),
            painter = painterResource(id = startIcon),
            contentDescription = "Icon",
            tint = IconTint
        )

        Column(
            modifier = Modifier
                .weight(8f)
                .wrapContentHeight()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp),
                text = title,
                style = LocalTextStyle.current.copy(fontSize = 12.sp),
                color = Secondary
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, top = 4.dp),
                text = count,
                style = LocalTextStyle.current.copy(fontSize = 8.sp),
                color = TextColor
            )

            if (isExpandable) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    text = barcode,
                    style = LocalTextStyle.current.copy(fontSize = 14.sp),
                    color = Secondary,
                    textAlign = TextAlign.Center
                )
            }
        }

        Icon(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .align(Alignment.CenterVertically)
                .weight(1f),
            painter = painterResource(id = if (isExpandable) R.drawable.ic_arrow_top else R.drawable.ic_arrow_down),
            contentDescription = "Icon",
            tint = IconTint
        )
    }
}

@Preview
@Composable
internal fun InventoryItemPreview() {
    InventoryItem(
        modifier = Modifier.fillMaxWidth(),
        title = "qfwjfqfpwfnlkjfnqwfhnqwpi",
        count = "1224",
        startIcon = R.drawable.ic_lock,
        barcode = "2512512502185"
    )
}