package ru.alexbur.feature.scanned_data.data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.alexbur.feature.scanned_data.domain.models.ScannedData

@Serializable
class ScannedDataListResponse(
    @SerialName("data")
    val data: List<ScannedDataResponse>
) {
    companion object {
        fun toModel(response: ScannedDataListResponse): List<ScannedData> {
            return response.data.map(ScannedDataResponse.Companion::toModel)
        }
    }
}