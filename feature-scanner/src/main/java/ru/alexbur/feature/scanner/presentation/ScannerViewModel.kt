package ru.alexbur.feature.scanner.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexbur.core.domain.error_handler.ErrorHandler
import ru.alexbur.core.domain.providers.StringProvider
import ru.alexbur.core.presentation.BaseViewModel
import ru.alexbur.core.presentation.snackbar.SnackBarStatus
import ru.alexbur.feature.scanner.R
import ru.alexbur.feature.scanner.domain.interactor.ScannerInteractor
import javax.inject.Inject

class ScannerViewModel @Inject constructor(
    private val interactor: ScannerInteractor,
    private val errorHandler: ErrorHandler,
    private val stringProvider: StringProvider
) : BaseViewModel<ScannerViewState>(ScannerViewState.Initial) {

    fun scanBarcode(barcode: String) {
        viewModelScope.launch {
            if (barcode.isBlank()) {
                // showSnackBar()
                return@launch
            }
            interactor.inventory(barcode).onSuccess {
                showSnackBar(stringProvider.getString(R.string.inventory_success, barcode), SnackBarStatus.SUCCESS)
            }.onFailure {
                showSnackBar(errorHandler.handleError(it), SnackBarStatus.ERROR)
            }
        }
    }
}