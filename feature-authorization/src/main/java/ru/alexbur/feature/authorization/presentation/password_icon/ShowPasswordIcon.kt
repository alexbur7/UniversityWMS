package ru.alexbur.feature.authorization.presentation.password_icon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.alexbur.uikit.R
import ru.alexbur.uikit.theme.IconTint

@Composable
internal fun ShowPasswordIcon(visibility: Boolean, showPassword: () -> Unit) {
    Icon(
        modifier = Modifier
            .size(24.dp)
            .clickable { showPassword() },
        painter = painterResource(
            id = if (visibility) {
                R.drawable.ic_eye_password_hide
            } else {
                R.drawable.ic_eye
            }
        ),
        contentDescription = "Icon Eye",
        tint = IconTint
    )
}