package ru.alexbur.core.domain.mediators

import android.content.Context
import retrofit2.Retrofit
import ru.alexbur.core.domain.providers.DispatcherProvider
import ru.alexbur.core.domain.providers.StringProvider

interface CommonComponentMediator {
    fun stringProvider(): StringProvider
    fun dispatcherProvider(): DispatcherProvider
    fun context(): Context
    fun retrofit(): Retrofit
}