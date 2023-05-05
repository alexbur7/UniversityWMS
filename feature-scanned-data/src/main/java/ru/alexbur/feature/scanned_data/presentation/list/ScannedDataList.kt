package ru.alexbur.feature.scanned_data.presentation.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.alexbur.feature.scanned_data.presentation.utils.ScannedDataListItem
import ru.alexbur.uikit.items.InventoryItem
import ru.alexbur.uikit.items.SingleLineItem
import ru.alexbur.uikit.theme.BottomNavigationHeight

@Composable
fun ScannedDataList(modifier: Modifier, data: List<ScannedDataListItem>) {
    LazyColumn(
        modifier = modifier.padding(vertical = 8.dp),
        contentPadding = PaddingValues(bottom = BottomNavigationHeight + 16.dp)
    ) {
        items(data.size) {
            when (val item = data[it]) {
                is ScannedDataListItem.DayItem -> SingleLineItem(
                    modifier = Modifier.fillMaxWidth(),
                    text = item.date
                )
                is ScannedDataListItem.Product -> InventoryItem(
                    modifier = Modifier.fillMaxWidth(),
                    title = item.name,
                    count = item.quantity,
                    startIcon = item.icon,
                    barcode = item.barcode
                )
            }
        }
    }
}