package ru.alexbur.uikit.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexbur.uikit.theme.IconTint
import ru.alexbur.uikit.theme.SecondaryTextColor

@Composable
fun UniversityWMSDialog(
    modifier: Modifier,
    text: String,
    confirmButtonText: String,
    dismissButtonText: String,
    onConfirmClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onCloseClick,
        text = {
            Text(text = text, style = TextStyle(color = IconTint, fontSize = 18.sp))
        },
        confirmButton = {
            Text(
                modifier = Modifier
                    .padding(4.dp)
                    .clickable {
                        onConfirmClick()
                    },
                text = confirmButtonText,
                style = TextStyle(color = IconTint, fontSize = 18.sp)
            )
        },
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 8.dp,
        dismissButton = {
            Text(
                modifier = Modifier
                    .padding(4.dp)
                    .clickable {
                        onCloseClick()
                    },
                text = dismissButtonText,
                style = TextStyle(color = SecondaryTextColor, fontSize = 18.sp)
            )
        }
    )
}

@Preview
@Composable
internal fun UniversityWMSDialogPreview() {
    val isVisible = remember { mutableStateOf(true) }
    if (isVisible.value) {
        UniversityWMSDialog(
            modifier = Modifier.fillMaxWidth(),
            text = "Текст",
            confirmButtonText = "Да",
            dismissButtonText = "Нет",
            onConfirmClick = { isVisible.value = false }
        ) { isVisible.value = false }
    }
}