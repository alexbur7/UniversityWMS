package ru.alexbur.feature.scanned_data.domain.models

import androidx.annotation.DrawableRes

data class ScannedData(
    val barcode: String,
    val name: String,
    val date: String,
    val type: Type,
    val quantity: Int
) {
    enum class Type(@DrawableRes val iconRes: Int) {
        PRODUCT(ru.alexbur.uikit.R.drawable.ic_user),
        TECH(ru.alexbur.uikit.R.drawable.ic_user),
        UNKNOWN(ru.alexbur.uikit.R.drawable.ic_user);

        companion object {
            fun create(value: String?): Type {
                return values().find { it.name.equals(value, ignoreCase = true) } ?: UNKNOWN
            }
        }
    }
}