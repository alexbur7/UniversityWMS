package ru.alexbur.feature.scanned_data.data.repository

import kotlinx.coroutines.withContext
import ru.alexbur.core.domain.providers.DispatcherProvider
import ru.alexbur.feature.scanned_data.data.api.ScannedDataApi
import ru.alexbur.feature.scanned_data.data.models.response.ScannedDataListResponse
import ru.alexbur.feature.scanned_data.domain.models.ScannedData
import ru.alexbur.feature.scanned_data.domain.repository.ScannedDataRepository
import javax.inject.Inject

class ScannedDataRepositoryImpl @Inject constructor(
    private val api: ScannedDataApi,
    private val dispatcherProvider: DispatcherProvider
) : ScannedDataRepository {

    override suspend fun getScannedData(): List<ScannedData> = withContext(dispatcherProvider.io) {
        ScannedDataListResponse.toModel(api.getScannedData())
    }
}