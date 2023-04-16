package ru.alexbur.feature.scanned_data.data.models.response

import android.annotation.SuppressLint
import ru.alexbur.feature.scanned_data.domain.models.ScannedData
import java.text.SimpleDateFormat
import java.util.*

class ScannedDataResponse(
    val barcode: String,
    val name: String,
    val date: Long,
    val type: String,
    val quantity: Int
) {
    companion object {
        fun toModel(response: ScannedDataResponse): ScannedData {
            return ScannedData(
                barcode = response.barcode,
                name = response.name,
                date = getFormattedDate(response.date),
                type = ScannedData.Type.create(response.type),
                quantity = response.quantity
            )
        }

        @SuppressLint("SimpleDateFormat")
        private fun getFormattedDate(scannedDate: Long): String {
            val date = Date(scannedDate)
            val calendar = GregorianCalendar().apply {
                time = date
                timeZone = TimeZone.getDefault()
            }
            val formatter = SimpleDateFormat("dd.MM.yyyy")
            return formatter.format(calendar.time)
        }
    }
}