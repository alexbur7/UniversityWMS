package ru.alexbur.feature.scanned_data.data.api

import retrofit2.http.GET
import ru.alexbur.feature.scanned_data.data.models.response.ScannedDataListResponse

interface ScannedDataApi {

    @GET("get-scanned-data")
    suspend fun getScannedData(): ScannedDataListResponse
}