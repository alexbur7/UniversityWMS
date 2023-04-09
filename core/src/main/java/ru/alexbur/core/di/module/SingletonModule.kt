package ru.alexbur.core.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.alexbur.core.data.error_handler.ErrorHandlerImpl
import ru.alexbur.core.data.providers.StringProvidersImpl
import ru.alexbur.core.domain.error_handler.ErrorHandler
import ru.alexbur.core.domain.providers.StringProvider

@Module
@InstallIn(SingletonComponent::class)
interface SingletonModule {

    @Binds
    fun bindErrorHandler(errorHandlerImpl: ErrorHandlerImpl): ErrorHandler

    @Binds
    fun bindStringProvider(stringProvider: StringProvidersImpl): StringProvider
}