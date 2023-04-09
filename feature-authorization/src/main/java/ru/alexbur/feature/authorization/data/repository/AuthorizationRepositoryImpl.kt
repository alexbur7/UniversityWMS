package ru.alexbur.feature.authorization.data.repository

import kotlinx.coroutines.withContext
import ru.alexbur.core.domain.datastore.AccountDataStore
import ru.alexbur.core.domain.providers.DispatcherProvider
import ru.alexbur.feature.authorization.data.api.AuthorizationApi
import ru.alexbur.feature.authorization.data.models.request.AuthorizationRequest
import ru.alexbur.feature.authorization.data.models.response.AuthorizationResponse
import ru.alexbur.feature.authorization.domain.models.AuthorizationToken
import ru.alexbur.feature.authorization.domain.repository.AuthorizationRepository
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(
    private val api: AuthorizationApi,
    private val dispatcherProvider: DispatcherProvider,
    private val accountDataStore: AccountDataStore
) : AuthorizationRepository {

    override suspend fun auth(login: String, password: String): AuthorizationToken =
        withContext(dispatcherProvider.io) {
            AuthorizationResponse.toModel(api.auth(AuthorizationRequest(login, password)))
        }
}