package ru.alexbur.core.presentation

import ru.alexbur.core.di.navigation.NavigationFactory
import ru.alexbur.core.presentation.snackbar.SnackBarStatus

sealed class ViewEvent {
    class Navigation(val route: NavigationFactory) : ViewEvent()
    class ShowSnackBar(val text: String, val status: SnackBarStatus) : ViewEvent()
    class PopBackStack(val data: Any? = null) : ViewEvent()
}