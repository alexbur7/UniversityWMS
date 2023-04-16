package ru.alexbur.feature.scanner.data.models.request

import kotlinx.serialization.Serializable

@Serializable
class InventoryProductRequest(
    val barcode: String
)