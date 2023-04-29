package ru.alexbur.core.domain.mediators

import android.content.Context
import retrofit2.Retrofit
import ru.alexbur.core.domain.datastore.AccountDataStore
import ru.alexbur.core.domain.error_handler.ErrorHandler
import ru.alexbur.core.domain.manager.CommunicateManager
import ru.alexbur.core.domain.providers.DispatcherProvider
import ru.alexbur.core.domain.providers.StringProvider

interface CommonComponentMediator {
    fun stringProvider(): StringProvider
    fun dispatcherProvider(): DispatcherProvider
    fun context(): Context
    fun retrofit(): Retrofit
    fun errorHandler(): ErrorHandler
    fun communicateManager(): CommunicateManager

    fun accountDataStore(): AccountDataStore
}