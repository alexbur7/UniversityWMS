package ru.alexbur.uikit.text_field

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexbur.uikit.R
import ru.alexbur.uikit.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniversityWmsTextField(
    modifier: Modifier,
    textLabel: String,
    @DrawableRes
    startIconId: Int,
    iconTint: Color,
    initialField: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation  = VisualTransformation.None,
    onValueChanged: (String) -> Unit = { },
) {
    val text = remember { mutableStateOf(initialField) }

    TextField(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .border(
                width = 0.5.dp,
                brush = Brush.horizontalGradient(listOf(BorderColor, Color.Transparent)),
                shape = RoundedCornerShape(24.dp)
            )
            .background(ListColor),
        value = text.value,
        placeholder = {
            Text(text = textLabel, style = TextStyle(color = PlaceHolderColor, fontSize = 16.sp))
        },
        leadingIcon = {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(IconBackColor)
                    .size(40.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = startIconId),
                    contentDescription = "Icon",
                    tint = iconTint
                )
            }
        },
        onValueChange = {
            text.value = it
            onValueChanged(it)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Send
        ),
        textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            textColor = iconTint,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation
    )
}


@Preview
@Composable
internal fun UniversityWmsTextFieldPreviewWithoutText() {
    UniversityWmsTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        textLabel = "InitialText",
        startIconId = R.drawable.ic_lock,
        iconTint = IconTint,
        keyboardType = KeyboardType.Password
    )
}

@Preview
@Composable
internal fun UniversityWmsTextFieldPreviewWithText() {
    UniversityWmsTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        textLabel = "InitialText",
        initialField = "Initial",
        startIconId = R.drawable.ic_user,
        iconTint = IconTint
    )
}