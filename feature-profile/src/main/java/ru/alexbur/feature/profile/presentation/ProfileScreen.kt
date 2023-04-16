package ru.alexbur.feature.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.alexbur.core.di.navigation.NavigationFactory
import ru.alexbur.core.di.navigation.NavigationScreenFactory
import ru.alexbur.core.presentation.ViewEvent
import ru.alexbur.core.presentation.snackbar.UniversityWmsSnackBarHost
import ru.alexbur.core.presentation.snackbar.showSnackBar
import ru.alexbur.feature.profile.R
import ru.alexbur.feature.profile.di.ProfileComponent
import ru.alexbur.feature.profile.presentation.card.ProfileCardScreen
import ru.alexbur.uikit.theme.BackgroundColor
import ru.alexbur.uikit.theme.BottomNavigationHeight
import ru.alexbur.uikit.theme.Secondary
import javax.inject.Inject

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = viewModel(modelClass = ProfileViewModel::class.java,
        key = null,
        factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ProfileComponent.getComponent().getViewModel() as T
            }
        })
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val profile = viewModel.profileFlow.collectAsState(null)
    val viewEventLifecycleAware = remember(viewModel.viewEvent, lifecycleOwner) {
        viewModel.viewEvent.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val viewEvent = viewEventLifecycleAware.collectAsState(initial = null)
    val snackBarHostState = SnackbarHostState()
    LaunchedEffect(key1 = viewEvent.value) {
        when (val event = viewEvent.value) {
            is ViewEvent.Navigation -> TODO()
            is ViewEvent.ShowSnackBar -> {
                snackBarHostState.showSnackBar(event.text, event.status)
            }
            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(bottom = BottomNavigationHeight)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 60.dp, end = 60.dp),
            text = stringResource(id = R.string.profile_title),
            textAlign = TextAlign.Center,
            style = TextStyle(color = Secondary, fontSize = 20.sp, fontWeight = FontWeight(600))
        )

        ProfileCardScreen(modifier = Modifier, profile = profile.value)
    }

    UniversityWmsSnackBarHost(hostState = snackBarHostState)
}

class ProfileScreenFactory @Inject constructor() : NavigationScreenFactory {

    companion object Companion : NavigationFactory.NavigationFactoryCompanion

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Nested)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = route
        ) {
            ProfileScreen(navGraph)
        }
    }
}