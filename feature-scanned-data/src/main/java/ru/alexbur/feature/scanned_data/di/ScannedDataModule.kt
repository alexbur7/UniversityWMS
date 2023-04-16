package ru.alexbur.feature.scanned_data.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.alexbur.core.di.named.FeatureScope
import ru.alexbur.core.domain.mock.MockEnable
import ru.alexbur.feature.scanned_data.data.api.ScannedDataApi
import ru.alexbur.feature.scanned_data.data.api.ScannedDataApiMock
import ru.alexbur.feature.scanned_data.data.repository.ScannedDataRepositoryImpl
import ru.alexbur.feature.scanned_data.domain.repository.ScannedDataRepository

@Module
object ScannedDataModule {

    @Provides
    @FeatureScope
    fun provideApi(retrofit: Retrofit): ScannedDataApi {
        return if (MockEnable.mockState) {
            ScannedDataApiMock()
        } else {
            retrofit.create(ScannedDataApi::class.java)
        }
    }

    @Provides
    @FeatureScope
    fun provideRepository(repositoryImpl: ScannedDataRepositoryImpl): ScannedDataRepository {
        return repositoryImpl
    }
}