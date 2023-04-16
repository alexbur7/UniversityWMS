package ru.alexbur.uikit.snackbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexbur.uikit.R
import ru.alexbur.uikit.theme.ErrorColor
import ru.alexbur.uikit.theme.Secondary
import ru.alexbur.uikit.theme.SnackBarTextColor
import ru.alexbur.uikit.theme.SuccessColor

@Composable
fun UniversityWmsSnackBar(
    message: String,
    color: Color,
    @DrawableRes
    iconRes: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .background(Secondary, shape = RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                brush = Brush.horizontalGradient(colors = listOf(color, Color.White)),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Icon(
            modifier = Modifier
                .padding(start = 14.dp, top = 12.dp, bottom = 12.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(id = iconRes),
            contentDescription = "Icon in snackBar",
            tint = color
        )
        Text(
            modifier = Modifier
                .padding(start = 20.dp, top = 8.dp, bottom = 8.dp, end = 14.dp)
                .align(Alignment.CenterVertically),
            text = message,
            style = TextStyle(color = SnackBarTextColor, fontSize = 12.sp)
        )
    }
}

@Preview
@Composable
internal fun UniversityWmsSnackBarSuccessPreview() {
    UniversityWmsSnackBar(
        message = "Успех! Все круто....",
        color = SuccessColor,
        iconRes = R.drawable.ic_ok
    )
}

@Preview
@Composable
internal fun UniversityWmsSnackBarErrorPreview() {
    UniversityWmsSnackBar(
        message = "Ошибка! Все сломалось....",
        color = ErrorColor,
        iconRes = R.drawable.ic_alert
    )
}

@Preview
@Composable
internal fun UniversityWmsSnackBarLongTextErrorPreview() {
    UniversityWmsSnackBar(
        message = "Ошибка! Все сломалось^ ;fnqkgjqwjfwqjfwjf[wjofjoqw[fjqwojfwf[;fknqwkfnwqnfnwqfnqwnn" +
                "pqwnfpiqwnfnwpnfnwqfnwnfqwnfnqwfnwfqwnfpwiqnfqp....",
        color = ErrorColor,
        iconRes = R.drawable.ic_alert
    )
}