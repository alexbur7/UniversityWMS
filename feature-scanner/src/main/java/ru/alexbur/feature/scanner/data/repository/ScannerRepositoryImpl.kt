package ru.alexbur.feature.scanner.data.repository

import kotlinx.coroutines.withContext
import ru.alexbur.core.domain.providers.DispatcherProvider
import ru.alexbur.feature.scanner.data.api.ScannerApi
import ru.alexbur.feature.scanner.data.models.request.InventoryProductRequest
import ru.alexbur.feature.scanner.domain.repository.ScannerRepository
import javax.inject.Inject

class ScannerRepositoryImpl @Inject constructor(
    private val api: ScannerApi,
    private val dispatcherProvider: DispatcherProvider
) : ScannerRepository {
    override suspend fun inventory(barcode: String) = withContext(dispatcherProvider.io) {
        api.inventory(InventoryProductRequest(barcode))
    }
}