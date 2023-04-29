package ru.alexbur.core.presentation

import ru.alexbur.core.domain.navigation.Router
import ru.alexbur.core.presentation.snackbar.SnackBarStatus

sealed class ViewEvent {
    class Navigation(val router: Router) : ViewEvent()
    class ShowSnackBar(val text: String, val status: SnackBarStatus) : ViewEvent()
    class PopBackStack(val data: Any? = null) : ViewEvent()
}