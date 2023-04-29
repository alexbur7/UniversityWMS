package ru.alexbur.feature.authorization.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexbur.core.domain.error_handler.ErrorHandler
import ru.alexbur.core.domain.navigation.Router
import ru.alexbur.core.domain.providers.StringProvider
import ru.alexbur.core.presentation.BaseViewModel
import ru.alexbur.core.presentation.ViewEvent
import ru.alexbur.core.presentation.snackbar.SnackBarStatus
import ru.alexbur.feature.authorization.R
import ru.alexbur.feature.authorization.domain.interactor.AuthorizationInteractor
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val interactor: AuthorizationInteractor,
    private val errorHandler: ErrorHandler,
    private val stringProvider: StringProvider
) : BaseViewModel<AuthorizationViewState>(AuthorizationViewState.EnterData) {

    var login = ""
    var password = ""

    fun auth() {
        viewModelScope.launch {
            if (login.isBlank() || password.isBlank()) {
                showSnackBar(stringProvider.getString(R.string.auth_empty_data_error), SnackBarStatus.ERROR)
                return@launch
            }
            interactor.auth(login, password).onSuccess {
                viewEventMutable.send(ViewEvent.Navigation(Router.MainRouter))
            }.onFailure {
                showSnackBar(errorHandler.handleError(it), SnackBarStatus.ERROR)
            }
        }
    }
}