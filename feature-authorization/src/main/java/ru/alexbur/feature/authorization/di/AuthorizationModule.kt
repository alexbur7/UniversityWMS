package ru.alexbur.feature.authorization.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.alexbur.core.di.named.FeatureScope
import ru.alexbur.core.domain.mock.MockEnable
import ru.alexbur.feature.authorization.data.api.AuthorizationApi
import ru.alexbur.feature.authorization.data.api.AuthorizationApiMock
import ru.alexbur.feature.authorization.data.repository.AuthorizationRepositoryImpl
import ru.alexbur.feature.authorization.domain.repository.AuthorizationRepository

@Module
object AuthorizationModule {

    @Provides
    @FeatureScope
    fun provideApi(retrofit: Retrofit): AuthorizationApi {
        return if (MockEnable.mockState) {
            AuthorizationApiMock()
        } else {
            retrofit.create(AuthorizationApi::class.java)
        }
    }

    @Provides
    @FeatureScope
    fun provideRepository(repositoryImpl: AuthorizationRepositoryImpl): AuthorizationRepository {
        return repositoryImpl
    }
}