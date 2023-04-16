package ru.alexbur.feature.scanner.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import ru.alexbur.feature.scanner.data.models.request.InventoryProductRequest

interface ScannerApi {

    @POST("inventory")
    suspend fun inventory(@Body request: InventoryProductRequest)
}