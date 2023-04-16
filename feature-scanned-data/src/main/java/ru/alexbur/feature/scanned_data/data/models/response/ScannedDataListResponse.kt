package ru.alexbur.feature.scanned_data.data.models.response

import ru.alexbur.feature.scanned_data.domain.models.ScannedData

class ScannedDataListResponse(
    val data: List<ScannedDataResponse>
) {
    companion object {
        fun toModel(response: ScannedDataListResponse): List<ScannedData> {
            return response.data.map(ScannedDataResponse.Companion::toModel)
        }
    }
}