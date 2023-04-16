package ru.alexbur.feature.scanned_data.presentation

import ru.alexbur.core.presentation.ViewState
import ru.alexbur.feature.scanned_data.presentation.utils.ScannedDataListItem

sealed class ScannedDataViewState : ViewState() {
    object Initial : ScannedDataViewState()
    class ShowScannedData(val data: List<ScannedDataListItem>) : ScannedDataViewState()
}