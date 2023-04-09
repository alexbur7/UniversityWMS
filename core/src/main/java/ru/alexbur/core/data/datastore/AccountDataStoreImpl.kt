package ru.alexbur.core.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import ru.alexbur.core.domain.datastore.AccountDataStore
import java.io.IOException
import javax.inject.Inject

class AccountDataStoreImpl @Inject constructor(
    private val context: Context
) : AccountDataStore {

    private val Context.dataStore by preferencesDataStore(AUTHORIZED_DATA_STORE_NAME)
    private val preferences: Flow<Preferences> = context.dataStore.data.catch {
        if (it is IOException) {
            it.printStackTrace()
            emit(emptyPreferences())
        } else {
            throw it
        }
    }


    override val login: Flow<String> = preferences.map {
        it[LOGIN_KEY]
    }.filterNotNull()
    override val password: Flow<String> = preferences.map {
        it[PASSWORD_KEY]
    }.filterNotNull()
    override val token: Flow<String> = preferences.map {
        it[USER_TOKEN_KEY]
    }.filterNotNull()

    override suspend fun updateLogin(login: String) {
        context.dataStore.edit { pref ->
            pref[LOGIN_KEY] = login
        }
    }

    override suspend fun updatePassword(password: String) {
        context.dataStore.edit { pref ->
            pref[PASSWORD_KEY] = password
        }
    }

    override suspend fun updateToken(token: String) {
        context.dataStore.edit { pref ->
            pref[USER_TOKEN_KEY] = token
        }
    }

    private companion object {
        const val AUTHORIZED_DATA_STORE_NAME = "authorized_data_store_name"
        val LOGIN_KEY = stringPreferencesKey("login_key")
        val PASSWORD_KEY = stringPreferencesKey("password_key")
        val USER_TOKEN_KEY = stringPreferencesKey("user_token_key")
    }
}