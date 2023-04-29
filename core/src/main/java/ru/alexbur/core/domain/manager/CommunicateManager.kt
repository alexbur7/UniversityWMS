package ru.alexbur.core.domain.manager

import kotlinx.coroutines.flow.MutableSharedFlow
import ru.alexbur.core.presentation.ViewEvent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommunicateManager @Inject constructor() {
    val communicateEvent = MutableSharedFlow<ViewEvent>()
}