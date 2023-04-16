package ru.alexbur.feature.profile.domain.interactor

import ru.alexbur.feature.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileInteractor @Inject constructor(
    private val repository: ProfileRepository
) {

    suspend fun getProfile() = runCatching { repository.getProfile() }
}