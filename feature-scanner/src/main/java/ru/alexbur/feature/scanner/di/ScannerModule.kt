package ru.alexbur.feature.scanner.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.alexbur.core.di.named.FeatureScope
import ru.alexbur.core.domain.mock.MockEnable
import ru.alexbur.feature.scanner.data.api.ScannerApi
import ru.alexbur.feature.scanner.data.api.ScannerApiMock
import ru.alexbur.feature.scanner.data.repository.ScannerRepositoryImpl
import ru.alexbur.feature.scanner.domain.repository.ScannerRepository

@Module
object ScannerModule {

    @Provides
    @FeatureScope
    fun provideApi(retrofit: Retrofit): ScannerApi {
        return if (MockEnable.mockState) {
            ScannerApiMock()
        } else {
            retrofit.create(ScannerApi::class.java)
        }
    }

    @Provides
    @FeatureScope
    fun provideRepository(repositoryImpl: ScannerRepositoryImpl): ScannerRepository {
        return repositoryImpl
    }
}