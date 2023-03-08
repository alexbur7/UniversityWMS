package ru.alexbur.core.di.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface NavigationFactory {

    val factoryType: List<NavigationFactoryType>

    interface NavigationFactoryCompanion {
        val route: String
            get() = with(javaClass) {
                `package`?.name.orEmpty() + canonicalName
            }
    }

    fun create(
        builder: NavGraphBuilder,
        navGraph: NavHostController
    )

    enum class NavigationFactoryType {
        Main,
        Nested
    }
}

interface NavigationScreenFactory : NavigationFactory

interface NavigationHostFactory : NavigationFactory