package ru.alexbur.university_wms.presentation.navbar

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.asSharedFlow
import ru.alexbur.core.domain.manager.CommunicateManager
import javax.inject.Inject

class BottomNavBarViewModel @Inject constructor(
    communicateManager: CommunicateManager
) : ViewModel() {

    val event = communicateManager.communicateEvent.asSharedFlow()
}