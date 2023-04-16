package ru.alexbur.university_wms.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import ru.alexbur.core.di.named.MainScope
import ru.alexbur.core.di.navigation.NavigationHostFactory
import ru.alexbur.core.di.navigation.NavigationScreenFactory
import ru.alexbur.feature.authorization.presentation.AuthorizationScreenFactory
import ru.alexbur.feature.profile.presentation.ProfileScreenFactory
import ru.alexbur.feature.scanned_data.presentation.ScannedDataScreenFactory
import ru.alexbur.feature.scanner.presentation.ScannerScreenFactory
import ru.alexbur.university_wms.presentation.MainNavHostScreenFactory

@Module
interface NavigationFactoriesModule {

    @IntoSet
    @Binds
    @MainScope
    fun bindAuthorizationFactory(authNavigationScreenFactory: AuthorizationScreenFactory): NavigationScreenFactory

    @IntoSet
    @Binds
    @MainScope
    fun bindMainNavHostScreenFactory(mainNavHostScreenFactory: MainNavHostScreenFactory): NavigationHostFactory

    @IntoSet
    @Binds
    @MainScope
    fun bindScannedDataFactory(factory: ScannedDataScreenFactory): NavigationScreenFactory

    @IntoSet
    @Binds
    @MainScope
    fun bindScannerScreenFactory(factory: ScannerScreenFactory): NavigationScreenFactory

    @IntoSet
    @Binds
    @MainScope
    fun bindProfileScreenFactory(factory: ProfileScreenFactory): NavigationScreenFactory
}