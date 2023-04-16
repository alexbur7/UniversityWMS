package ru.alexbur.university_wms.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import ru.alexbur.core.di.named.MainScope
import ru.alexbur.core.di.navigation.NavigationScreenFactory
import ru.alexbur.feature.authorization.presentation.AuthorizationScreenFactory
import ru.alexbur.feature.scanned_data.presentation.ScannedDataScreenFactory

@Module
interface NavigationFactoriesModule {

    @IntoSet
    @Binds
    @MainScope
    fun bindAuthorizationFactory(authNavigationScreenFactory: AuthorizationScreenFactory): NavigationScreenFactory

    @IntoSet
    @Binds
    @MainScope
    fun bindScannedDataFactory(factory: ScannedDataScreenFactory): NavigationScreenFactory
}