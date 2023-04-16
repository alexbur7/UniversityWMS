package ru.alexbur.uikit.text_field

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import ru.alexbur.uikit.theme.IconTint
import ru.alexbur.uikit.theme.PlaceHolderColor

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
    onValueChanged: (String) -> Unit = { },
) {
    val text = remember { mutableStateOf(initialField) }
    val visibility = remember { mutableStateOf(false) }
    val showPassword = remember { { visibility.value = !visibility.value } }

    TextField(
        modifier = modifier.background(Color.White),
        value = text.value,
        placeholder = {
            Text(text = textLabel, style = TextStyle(color = PlaceHolderColor, fontSize = 16.sp))
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = startIconId),
                contentDescription = "Icon",
                tint = iconTint
            )
        },
        shape = RectangleShape,
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
            containerColor = Color.Transparent
        ),
        trailingIcon = {
            if (keyboardType == KeyboardType.Password) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { showPassword() },
                    painter = painterResource(
                        id = if (visibility.value) {
                            R.drawable.ic_eye_password_hide
                        } else {
                            R.drawable.ic_eye
                        }
                    ),
                    contentDescription = "Icon Eye",
                    tint = iconTint
                )
            }
        },
        visualTransformation = if (keyboardType == KeyboardType.Password && !visibility.value) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
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