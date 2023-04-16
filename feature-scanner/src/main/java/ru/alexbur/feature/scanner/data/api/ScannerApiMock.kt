package ru.alexbur.feature.scanner.data.api

import kotlinx.coroutines.delay
import ru.alexbur.feature.scanner.data.models.request.InventoryProductRequest

class ScannerApiMock : ScannerApi {

    override suspend fun inventory(request: InventoryProductRequest) {
        delay(1500L)
    }
}