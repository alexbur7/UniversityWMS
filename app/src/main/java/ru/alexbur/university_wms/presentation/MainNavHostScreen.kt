package ru.alexbur.university_wms.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.alexbur.core.data.extentions.filter
import ru.alexbur.core.di.navigation.NavigationFactory
import ru.alexbur.core.di.navigation.NavigationHostFactory
import ru.alexbur.core.di.navigation.NavigationScreenFactory
import ru.alexbur.core.domain.navigation.Router
import ru.alexbur.core.presentation.ViewEvent
import ru.alexbur.uikit.theme.FirstBackColorColor
import ru.alexbur.university_wms.di.MainComponent
import ru.alexbur.university_wms.presentation.navbar.BottomNavBar
import ru.alexbur.university_wms.presentation.navbar.BottomNavBarViewModel
import javax.inject.Inject

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavHostScreen(
    navController: NavController,
    navigationFactoryList: List<NavigationFactory>,
    viewModel: BottomNavBarViewModel = viewModel(modelClass = BottomNavBarViewModel::class.java,
        key = null,
        factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainComponent.getComponent().getViewModel() as T
            }
        })
) {
    val controller = rememberNavController()
    val lifecycleOwner = LocalLifecycleOwner.current
    val viewEventLifecycleAware = remember(viewModel.event, lifecycleOwner) {
        viewModel.event.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val viewEvent = viewEventLifecycleAware.collectAsState(initial = null)
    LaunchedEffect(key1 = viewEvent.value) {
        when (viewEvent.value) {
            is ViewEvent.PopBackStack -> {
                navController.popBackStack()
            }
            else -> Unit
        }
    }

    Scaffold(
        modifier = Modifier.background(FirstBackColorColor),
        bottomBar = {
            BottomNavBar(
                navController = controller
            )
        }
    ) {
        NavHost(
            navController = controller,
            startDestination = Router.ScannerRouter.route
        ) {
            navigationFactoryList.forEach { it.create(this, controller) }
        }
    }
}

class MainNavHostScreenFactory @Inject constructor(
    private val navigationFactorySet: @JvmSuppressWildcards Set<NavigationScreenFactory>
) : NavigationHostFactory {

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Main)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(route = Router.MainRouter.route) {
            MainNavHostScreen(
                navController = navGraph,
                navigationFactoryList = navigationFactorySet.filter(NavigationFactory.NavigationFactoryType.Nested)
            )
        }
    }
}