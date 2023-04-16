package ru.alexbur.feature.scanned_data.data.api

import ru.alexbur.feature.scanned_data.data.models.response.ScannedDataListResponse
import ru.alexbur.feature.scanned_data.data.models.response.ScannedDataResponse

class ScannedDataApiMock : ScannedDataApi {

    override suspend fun getScannedData(): ScannedDataListResponse {
        return ScannedDataListResponse(
            data = listOf(
                ScannedDataResponse(
                    barcode = "ab251521521",
                    name = "User 1245125121mgwfmwemf2mw",
                    date = 1681652614,
                    type = "Product",
                    quantity = 5
                ),
                ScannedDataResponse(
                    barcode = "51521521",
                    name = "User 1wfmwemf2mw",
                    date = 1681652614,
                    type = "Product",
                    quantity = 4
                ),
                ScannedDataResponse(
                    barcode = "ab21521",
                    name = "User 1245125121mg",
                    date = 1681652614,
                    type = "Tech",
                    quantity = 4
                ),
                ScannedDataResponse(
                    barcode = "ab25151521",
                    name = "User 1245125121",
                    date = 1681652614,
                    type = "Product",
                    quantity = 7
                ),
                ScannedDataResponse(
                    barcode = "ab251521521",
                    name = "User 1245125121mgwfmwemf2mw",
                    date = 1681652614,
                    type = "Tech",
                    quantity = 2
                ),
                ScannedDataResponse(
                    barcode = "ab251521521",
                    name = "User 1245125121mgwfmwemf2mw",
                    date = 1681652614,
                    type = "Product",
                    quantity = 12
                ),
            )
        )
    }
}