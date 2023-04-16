package ru.alexbur.feature.scanned_data.domain.interactor

import ru.alexbur.feature.scanned_data.domain.repository.ScannedDataRepository
import javax.inject.Inject

class ScannedDataInteractor @Inject constructor(
    private val repository: ScannedDataRepository
) {

    suspend fun getData() = runCatching { repository.getScannedData() }
}