package ru.alexbur.core.domain.navigation

import ru.alexbur.core.di.navigation.NavigationFactory

sealed class Router : NavigationFactory.NavigationFactoryCompanion {
    object AuthRouter : Router() {
        override val route: String = "auth_route"
    }

    object MainRouter : Router() {
        override val route: String = "main_route"
    }

    object ProfileRouter : Router() {
        override val route: String = "profile_route"
    }

    object ScannerRouter : Router() {
        override val route: String = "scanner_route"

    }

    object ScannerDataRouter : Router() {
        override val route: String = "scanned_data_route"
    }
}