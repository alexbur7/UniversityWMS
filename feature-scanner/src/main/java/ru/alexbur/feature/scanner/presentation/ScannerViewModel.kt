package ru.alexbur.feature.scanner.presentation

import ru.alexbur.core.presentation.BaseViewModel
import javax.inject.Inject

class ScannerViewModel @Inject constructor() : BaseViewModel<ScannerViewState>(ScannerViewState.Initial)