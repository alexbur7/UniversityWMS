package ru.alexbur.feature.scanner.domain.interactor

import ru.alexbur.feature.scanner.domain.repository.ScannerRepository
import javax.inject.Inject

class ScannerInteractor @Inject constructor(
    private val repository: ScannerRepository
) {

    suspend fun inventory(barcode: String) = runCatching {
        repository.inventory(barcode)
    }
}