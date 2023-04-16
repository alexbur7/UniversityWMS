package ru.alexbur.feature.profile.presentation

import ru.alexbur.core.presentation.ViewState
import ru.alexbur.feature.profile.domain.models.Profile

sealed class ProfileViewState : ViewState() {
    object Initial : ProfileViewState()
    data class ShowProfile(val data: Profile) : ProfileViewState()
}