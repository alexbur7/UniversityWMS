package ru.alexbur.core.presentation.snackbar

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import ru.alexbur.uikit.R
import ru.alexbur.uikit.theme.ErrorColor
import ru.alexbur.uikit.theme.SuccessColor

enum class SnackBarStatus(val color: Color, @DrawableRes val iconRes: Int) {
    SUCCESS(SuccessColor, R.drawable.ic_ok),
    ERROR(ErrorColor, R.drawable.ic_alert);
}