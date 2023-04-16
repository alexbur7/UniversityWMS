package ru.alexbur.feature.authorization.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import ru.alexbur.feature.authorization.R
import ru.alexbur.feature.authorization.di.AuthorizationComponent
import ru.alexbur.feature.authorization.presentation.shape.CustomShape
import ru.alexbur.uikit.buttons.UniversityWmsButton
import ru.alexbur.uikit.text_field.UniversityWmsTextField
import javax.inject.Inject

@Composable
fun AuthorizationScreen(
    navController: NavController?,
    viewModel: AuthorizationViewModel = viewModel(modelClass = AuthorizationViewModel::class.java,
        key = null,
        factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AuthorizationComponent.getComponent().getViewModel() as T
            }
        })
) {
    val lifecycleOwner = LocalLifecycleOwner.current
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
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Icon(
            modifier = Modifier
                .padding(top = 48.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = ru.alexbur.uikit.R.drawable.ic_cube),
            contentDescription = "Cube",
            tint = Color.White
        )

        Column(
            modifier = Modifier
                .padding(top = 48.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.White, shape = CustomShape(radius = 56f))
        ) {
            UniversityWmsTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 44.dp, start = 24.dp, end = 24.dp),
                textLabel = stringResource(id = R.string.auth_login),
                startIconId = ru.alexbur.uikit.R.drawable.ic_user,
                onValueChanged = viewModel::login::set
            )

            UniversityWmsTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp),
                textLabel = stringResource(id = R.string.auth_password),
                startIconId = ru.alexbur.uikit.R.drawable.ic_lock,
                onValueChanged = viewModel::password::set
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                UniversityWmsButton(
                    modifier = Modifier
                        .padding(bottom = 24.dp, start = 34.dp, end = 34.dp)
                        .fillMaxWidth()
                        .align(Alignment.Bottom),
                    text = stringResource(id = R.string.auth_come_in),
                    onClick = viewModel::auth
                )
            }
        }
    }

    UniversityWmsSnackBarHost(hostState = snackBarHostState)
}

class AuthorizationScreenFactory @Inject constructor() : NavigationScreenFactory {

    companion object Companion : NavigationFactory.NavigationFactoryCompanion

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Main)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = route
        ) {
            AuthorizationScreen(navGraph)
        }
    }
}

@Preview
@Composable
fun AuthorizationScreenPreview() {
    AuthorizationScreen(null)
}