package ru.alexbur.feature.authorization.domain.repository

import ru.alexbur.feature.authorization.domain.models.AuthorizationToken

interface AuthorizationRepository {

    suspend fun auth(login: String, password: String): AuthorizationToken
}