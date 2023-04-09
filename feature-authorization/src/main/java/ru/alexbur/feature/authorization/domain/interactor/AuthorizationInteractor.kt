package ru.alexbur.feature.authorization.domain.interactor

import ru.alexbur.feature.authorization.domain.repository.AuthorizationRepository
import javax.inject.Inject

class AuthorizationInteractor @Inject constructor(
    private val authorizationRepository: AuthorizationRepository
) {

    suspend fun auth(login: String, password: String) = runCatching {
        authorizationRepository.auth(login, password)
    }
}