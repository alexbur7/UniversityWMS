package ru.alexbur.feature.scanner.presentation

import ru.alexbur.core.presentation.BaseViewModel
import ru.alexbur.feature.scanner.domain.interactor.ScannerInteractor
import javax.inject.Inject

class ScannerViewModel @Inject constructor(
    private val interactor: ScannerInteractor
) : BaseViewModel<ScannerViewState>(ScannerViewState.Initial)