package ru.alexbur.feature.profile.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.alexbur.core.domain.error_handler.ErrorHandler
import ru.alexbur.core.domain.manager.CommunicateManager
import ru.alexbur.core.presentation.BaseViewModel
import ru.alexbur.core.presentation.ViewEvent
import ru.alexbur.core.presentation.snackbar.SnackBarStatus
import ru.alexbur.feature.profile.domain.interactor.ProfileInteractor
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val interactor: ProfileInteractor,
    private val errorHandler: ErrorHandler,
    private val communicateManager: CommunicateManager
) : BaseViewModel<ProfileViewState>(ProfileViewState.Initial) {

    val profileFlow = viewState.filterIsInstance<ProfileViewState.ShowProfile>().map { it.data }

    init {
        getProfile()
    }

    fun exit() {
        viewModelScope.launch {
            communicateManager.communicateEvent.emit(ViewEvent.PopBackStack())
        }
    }

    private fun getProfile() {
        viewModelScope.launch {
            interactor.getProfile().onSuccess {
                viewStateMutable.value = ProfileViewState.ShowProfile(it)
            }.onFailure {
                showSnackBar(errorHandler.handleError(it), SnackBarStatus.ERROR)
            }
        }
    }
}