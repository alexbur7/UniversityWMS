package ru.alexbur.core.presentation

import ru.alexbur.core.di.navigation.NavigationFactory

sealed class ViewEvent {
    class Navigation(val route: NavigationFactory) : ViewEvent()
    class ShowSnackBar(val text: String, val isSuccess: Boolean) : ViewEvent()
    class PopBackStack(val data: Any? = null) : ViewEvent()
}