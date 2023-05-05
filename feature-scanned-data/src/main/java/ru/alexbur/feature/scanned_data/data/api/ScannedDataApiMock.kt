package ru.alexbur.feature.scanned_data.data.api

import ru.alexbur.core.data.mock_db.MockDB
import ru.alexbur.feature.scanned_data.data.models.response.ScannedDataListResponse
import ru.alexbur.feature.scanned_data.data.models.response.ScannedDataResponse

class ScannedDataApiMock : ScannedDataApi {

    override suspend fun getScannedData(): ScannedDataListResponse {
        return ScannedDataListResponse(
            data = MockDB.scannerData.value.mapIndexed { index, pair ->
                ScannedDataResponse(
                    barcode = pair.second,
                    name = "Name ${pair.second}",
                    date = pair.first,
                    type = if (index % 2 == 0 ) "Product" else "Tech",
                    quantity = index
                )
            })
        /*return ScannedDataListResponse(
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
                ScannedDataResponse(
                    barcode = "ab251521521",
                    name = "User 1245125121mgwfmwemf2mw",
                    date = 1681652614,
                    type = "Product",
                    quantity = 12
                ),
                ScannedDataResponse(
                    barcode = "ab20221521",
                    name = "User 1245125121mgwfmwemf2mw",
                    date = 1681652614,
                    type = "Product",
                    quantity = 7
                ),
                ScannedDataResponse(
                    barcode = "ab2509121521",
                    name = "User 21mgwfmwemf2mw",
                    date = 1681652614,
                    type = "Product",
                    quantity = 12
                ),
                ScannedDataResponse(
                    barcode = "ab21529121",
                    name = "User mgwfmwemf2mw",
                    date = 1681652614,
                    type = "Product",
                    quantity = 18
                ),
                ScannedDataResponse(
                    barcode = "ab521521",
                    name = "User 124512w",
                    date = 1681652614,
                    type = "Product",
                    quantity = 128
                ),
                ScannedDataResponse(
                    barcode = "ab251521521",
                    name = "User 124emf2mw",
                    date = 1681652614,
                    type = "Product",
                    quantity = 10
                ),
            )
        )*/
    }
}