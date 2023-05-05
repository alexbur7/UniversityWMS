package ru.alexbur.feature.scanned_data.presentation.utils

import androidx.annotation.DrawableRes

sealed class ScannedDataListItem {
    data class DayItem(val date: String) : ScannedDataListItem()
    data class Product(
        val name: String,
        val barcode: String,
        val quantity: String,
        @DrawableRes
        val icon: Int
    ) : ScannedDataListItem()
}