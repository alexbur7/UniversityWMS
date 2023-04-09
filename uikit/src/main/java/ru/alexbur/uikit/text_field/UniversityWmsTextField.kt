package ru.alexbur.uikit.text_field

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexbur.uikit.theme.PlaceHolderColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniversityWmsTextField(
    modifier: Modifier,
    textLabel: String,
    @DrawableRes
    startIconId: Int,
    initialField: String = "",
    onValueChanged: (String) -> Unit = { },
) {
    var text by remember {
        mutableStateOf(initialField)
    }

    TextField(
        modifier = modifier.background(Color.White),
        value = text,
        placeholder = {
            Text(text = textLabel, style = TextStyle(color = PlaceHolderColor, fontSize = 16.sp))
        },
        leadingIcon = {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = startIconId),
                contentDescription = "Icon"
            )
        },
        shape = RectangleShape,
        onValueChange = {
            text = it
            onValueChanged(it)
        },
        textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent
        )
    )
}


@Preview
@Composable
fun UniversityWmsTextFieldPreviewWithoutText() {
    UniversityWmsTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        textLabel = "InitialText",
        startIconId = ru.alexbur.uikit.R.drawable.ic_lock
    )
}

@Preview
@Composable
fun UniversityWmsTextFieldPreviewWithText() {
    UniversityWmsTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        textLabel = "InitialText",
        initialField = "Initial",
        startIconId = ru.alexbur.uikit.R.drawable.ic_user
    )
}