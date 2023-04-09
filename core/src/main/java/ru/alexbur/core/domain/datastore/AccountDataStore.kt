package ru.alexbur.core.domain.datastore

import kotlinx.coroutines.flow.Flow

interface AccountDataStore {
    val login: Flow<String>
    val password: Flow<String>
    val token: Flow<String>

    suspend fun updateLogin(login: String)
    suspend fun updatePassword(password: String)
    suspend fun updateToken(token: String)
}