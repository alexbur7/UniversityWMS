package ru.alexbur.feature.authorization.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.alexbur.core.domain.error_handler.ErrorHandler
import ru.alexbur.core.presentation.ViewEvent
import ru.alexbur.feature.authorization.domain.interactor.AuthorizationInteractor
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val interactor: AuthorizationInteractor,
    private val errorHandler: ErrorHandler
) : ViewModel() {

    private val _viewEvent = Channel<ViewEvent>(BUFFERED)
    val viewEvent: Flow<ViewEvent> = _viewEvent.receiveAsFlow()

    fun auth(login: String, password: String) {
        viewModelScope.launch {
            interactor.auth(login, password).onSuccess {

            }.onFailure {
                val text = errorHandler.handleError(it)
            }
        }
    }
}