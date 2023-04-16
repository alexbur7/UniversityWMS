package ru.alexbur.feature.scanned_data.data.repository

import ru.alexbur.feature.scanned_data.data.api.ScannedDataApi
import ru.alexbur.feature.scanned_data.domain.models.ScannedData
import ru.alexbur.feature.scanned_data.domain.repository.ScannedDataRepository
import javax.inject.Inject

class ScannedDataRepositoryImpl @Inject constructor(
    private val api: ScannedDataApi
) : ScannedDataRepository {

    override suspend fun getScannedData(): List<ScannedData> {
        TODO("Not yet implemented")
    }
}