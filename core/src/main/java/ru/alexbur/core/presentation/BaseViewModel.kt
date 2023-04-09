package ru.alexbur.core.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<VS : ViewState>(defaultState: VS) : ViewModel() {

    protected val viewStateMutable = MutableStateFlow(defaultState)
    val viewState: StateFlow<VS> = viewStateMutable.asStateFlow()

    protected val viewEventMutable = Channel<ViewEvent>(Channel.BUFFERED)
    val viewEvent: Flow<ViewEvent> = viewEventMutable.receiveAsFlow()

    protected suspend fun showSnackBar(text: String, isSuccess: Boolean) {
        viewEventMutable.send(ViewEvent.ShowSnackBar(text, isSuccess))
    }
}