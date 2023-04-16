package ru.alexbur.feature.scanned_data.domain.repository

import ru.alexbur.feature.scanned_data.domain.models.ScannedData

interface ScannedDataRepository {

    suspend fun getScannedData(): List<ScannedData>
}