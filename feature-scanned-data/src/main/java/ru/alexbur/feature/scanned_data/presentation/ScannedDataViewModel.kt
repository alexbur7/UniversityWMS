package ru.alexbur.feature.scanned_data.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexbur.core.domain.error_handler.ErrorHandler
import ru.alexbur.core.domain.providers.StringProvider
import ru.alexbur.core.presentation.BaseViewModel
import ru.alexbur.core.presentation.snackbar.SnackBarStatus
import ru.alexbur.feature.scanned_data.domain.interactor.ScannedDataInteractor
import ru.alexbur.feature.scanned_data.domain.models.ScannedData
import ru.alexbur.feature.scanned_data.presentation.utils.ScannedDataListItem
import javax.inject.Inject

class ScannedDataViewModel @Inject constructor(
    private val interactor: ScannedDataInteractor,
    private val errorHandler: ErrorHandler,
    private val stringProvider: StringProvider
) : BaseViewModel<ScannedDataViewState>(ScannedDataViewState.Initial) {

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            interactor.getData().onSuccess {
                viewStateMutable.value = ScannedDataViewState.ShowScannedData(convertToUiData(it))
            }.onFailure {
                showSnackBar(errorHandler.handleError(it), SnackBarStatus.ERROR)
            }
        }
    }

    private fun convertToUiData(data: List<ScannedData>): List<ScannedDataListItem> {
        return buildList {
            data.groupBy { it.date }.map { (scannedDate, list) ->
                add(ScannedDataListItem.DayItem(scannedDate))

                list.forEach { scannedData ->
                    add(
                        ScannedDataListItem.Product(
                            name = scannedData.name,
                            barcode = scannedData.barcode,
                            quantity = scannedData.quantity.toString(),
                            icon = scannedData.type.iconRes
                        )
                    )
                }
            }
        }
    }
}