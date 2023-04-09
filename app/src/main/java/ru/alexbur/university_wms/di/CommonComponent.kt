package ru.alexbur.university_wms.di

import android.content.Context
import dagger.Component
import retrofit2.Retrofit
import ru.alexbur.core.di.module.BindsModule
import ru.alexbur.core.di.module.ContextModule
import ru.alexbur.core.di.module.NetworkModule
import ru.alexbur.core.domain.datastore.AccountDataStore
import ru.alexbur.core.domain.error_handler.ErrorHandler
import ru.alexbur.core.domain.mediators.CommonComponentMediator
import ru.alexbur.core.domain.providers.DispatcherProvider
import ru.alexbur.core.domain.providers.StringProvider
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        NetworkModule::class,
        BindsModule::class
    ]
)
interface CommonComponent : CommonComponentMediator {
    override fun context(): Context
    override fun stringProvider(): StringProvider
    override fun dispatcherProvider(): DispatcherProvider
    override fun errorHandler(): ErrorHandler
    override fun retrofit(): Retrofit
    override fun accountDataStore(): AccountDataStore
}