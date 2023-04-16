package ru.alexbur.university_wms

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.alexbur.core.data.extentions.filter
import ru.alexbur.core.di.navigation.NavigationFactory
import ru.alexbur.core.di.navigation.NavigationHostFactory
import ru.alexbur.core.di.navigation.NavigationScreenFactory
import ru.alexbur.uikit.theme.PrimaryFirst
import ru.alexbur.uikit.theme.UniversityWMSTheme
import ru.alexbur.university_wms.di.MainComponent
import ru.alexbur.university_wms.presentation.navbar.NavItem
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationScreenFactorySet: @JvmSuppressWildcards Set<NavigationScreenFactory>

    @Inject
    lateinit var navigationHostFactorySet: @JvmSuppressWildcards Set<NavigationHostFactory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainComponent.getComponent().inject(this)
        setTheme(ru.alexbur.uikit.R.style.Theme_UniversityWMS)
        setContent {
            UniversityWMSTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = PrimaryFirst
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = NavItem.MainScreen.route//if (account == null) NavItem.Authorization.route else NavItem.MainScreen.route
                    ) {
                        mutableSetOf<NavigationFactory>().apply {
                            addAll(
                                navigationScreenFactorySet.filter(NavigationFactory.NavigationFactoryType.Main)
                            )
                            addAll(
                                navigationHostFactorySet.filter(NavigationFactory.NavigationFactoryType.Main)
                            )
                        }.forEach {
                            it.create(this, navController)
                        }
                    }
                }
            }
        }
    }
}