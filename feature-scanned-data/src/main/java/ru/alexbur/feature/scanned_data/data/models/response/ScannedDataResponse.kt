package ru.alexbur.feature.scanned_data.data.models.response

import android.annotation.SuppressLint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.alexbur.feature.scanned_data.domain.models.ScannedData
import java.text.SimpleDateFormat
import java.util.*

@Serializable
class ScannedDataResponse(
    @SerialName("barcode")
    val barcode: String,
    @SerialName("name")
    val name: String,
    @SerialName("date")
    val date: Long,
    @SerialName("type")
    val type: String,
    @SerialName("quantity")
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
            val calendar = GregorianCalendar().apply {
                time.time = scannedDate
                timeZone = TimeZone.getDefault()
            }
            val formatter = SimpleDateFormat("dd.MM.yyyy")
            return formatter.format(calendar.time)
        }
    }
}