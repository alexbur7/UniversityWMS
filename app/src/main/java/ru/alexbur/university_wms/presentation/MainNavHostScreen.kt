package ru.alexbur.university_wms.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.alexbur.core.data.extentions.filter
import ru.alexbur.core.di.navigation.NavigationFactory
import ru.alexbur.core.di.navigation.NavigationHostFactory
import ru.alexbur.core.di.navigation.NavigationScreenFactory
import ru.alexbur.uikit.theme.PrimaryFirst
import ru.alexbur.university_wms.presentation.navbar.BottomNavBar
import ru.alexbur.university_wms.presentation.navbar.NavItem
import javax.inject.Inject

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavHostScreen(
    modifier: Modifier,
    navigationFactoryList: List<NavigationFactory>,
) {
    val controller = rememberNavController()

    Scaffold(
        modifier = modifier
            .background(PrimaryFirst),
        bottomBar = {
            BottomNavBar(
                navController = controller
            )
        }
    ) {
        NavHost(
            navController = controller,
            startDestination = NavItem.NavBarItems.SCANNER.route
        ) {
            navigationFactoryList.forEach { it.create(this, controller) }
        }
    }
}

class MainNavHostScreenFactory @Inject constructor(
    private val navigationFactorySet: @JvmSuppressWildcards Set<NavigationScreenFactory>
) : NavigationHostFactory {

    companion object Companion : NavigationFactory.NavigationFactoryCompanion

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Main)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(route = route) {
            MainNavHostScreen(
                modifier = Modifier,
                navigationFactoryList = navigationFactorySet.filter(NavigationFactory.NavigationFactoryType.Nested)
            )
        }
    }
}