package ru.alexbur.feature.scanner.presentation

import ru.alexbur.core.presentation.ViewState

sealed class ScannerViewState : ViewState() {
    object Initial : ScannerViewState()
}