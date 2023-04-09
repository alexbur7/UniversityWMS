package ru.alexbur.feature.authorization.presentation

import ru.alexbur.core.presentation.ViewState

sealed class AuthorizationViewState : ViewState() {
    object EnterData : AuthorizationViewState()
}