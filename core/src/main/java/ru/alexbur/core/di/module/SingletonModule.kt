package ru.alexbur.core.di.module

import dagger.Binds
import dagger.Module
import ru.alexbur.core.data.datastore.AccountDataStoreImpl
import ru.alexbur.core.data.error_handler.ErrorHandlerImpl
import ru.alexbur.core.data.providers.DispatcherProviderImpl
import ru.alexbur.core.data.providers.StringProvidersImpl
import ru.alexbur.core.domain.datastore.AccountDataStore
import ru.alexbur.core.domain.error_handler.ErrorHandler
import ru.alexbur.core.domain.providers.DispatcherProvider
import ru.alexbur.core.domain.providers.StringProvider
import javax.inject.Singleton

@Module
interface SingletonModule {

    @Binds
    @Singleton
    fun bindErrorHandler(errorHandlerImpl: ErrorHandlerImpl): ErrorHandler

    @Binds
    @Singleton
    fun bindStringProvider(stringProvider: StringProvidersImpl): StringProvider

    @Binds
    @Singleton
    fun bindDispatcherProvider(dispatcherProviderImpl: DispatcherProviderImpl): DispatcherProvider

    @Binds
    @Singleton
    fun bindAccountDataStore(accountDataStoreImpl: AccountDataStoreImpl): AccountDataStore
}