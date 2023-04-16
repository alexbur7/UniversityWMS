package ru.alexbur.feature.profile.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.alexbur.core.di.named.FeatureScope
import ru.alexbur.core.domain.mock.MockEnable
import ru.alexbur.feature.profile.data.api.ProfileApi
import ru.alexbur.feature.profile.data.api.ProfileApiMock
import ru.alexbur.feature.profile.data.repository.ProfileRepositoryImpl
import ru.alexbur.feature.profile.domain.repository.ProfileRepository

@Module
object ProfileModule {

    @Provides
    @FeatureScope
    fun provideApi(retrofit: Retrofit): ProfileApi {
        return if (MockEnable.mockState) {
            ProfileApiMock()
        } else {
            retrofit.create(ProfileApi::class.java)
        }
    }

    @Provides
    @FeatureScope
    fun provideRepository(repositoryImpl: ProfileRepositoryImpl): ProfileRepository {
        return repositoryImpl
    }
}