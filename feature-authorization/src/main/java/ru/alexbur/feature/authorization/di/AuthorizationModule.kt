package ru.alexbur.feature.authorization.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.alexbur.core.di.named.FeatureScope
import ru.alexbur.feature.authorization.data.api.AuthorizationApi

@Module
class AuthorizationModule {

    @Provides
    @FeatureScope
    fun provideApi(retrofit: Retrofit): AuthorizationApi {
        return retrofit.create(AuthorizationApi::class.java)
    }
}