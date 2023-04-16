package ru.alexbur.feature.profile.data.repository

import kotlinx.coroutines.withContext
import ru.alexbur.core.domain.providers.DispatcherProvider
import ru.alexbur.feature.profile.data.api.ProfileApi
import ru.alexbur.feature.profile.data.models.response.ProfileResponse
import ru.alexbur.feature.profile.domain.models.Profile
import ru.alexbur.feature.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val api: ProfileApi,
    private val dispatcherProvider: DispatcherProvider
) : ProfileRepository {

    override suspend fun getProfile(): Profile = withContext(dispatcherProvider.io) {
        ProfileResponse.toModel(api.getProfile())
    }
}