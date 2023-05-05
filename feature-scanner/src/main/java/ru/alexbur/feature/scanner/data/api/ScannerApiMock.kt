package ru.alexbur.feature.scanner.data.api

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import ru.alexbur.core.data.mock_db.MockDB
import ru.alexbur.feature.scanner.data.models.request.InventoryProductRequest
import java.util.*

class ScannerApiMock : ScannerApi {

    override suspend fun inventory(request: InventoryProductRequest) {
        delay(300L)
        MockDB.scannerData.update {
            it + (Calendar.getInstance().time.time to request.barcode)
        }
    }
}