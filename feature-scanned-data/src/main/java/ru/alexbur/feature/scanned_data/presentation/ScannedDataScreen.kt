package ru.alexbur.feature.scanned_data.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
import ru.alexbur.feature.scanned_data.R
import ru.alexbur.feature.scanned_data.di.ScannedDataComponent
import ru.alexbur.feature.scanned_data.presentation.list.ScannedDataList
import ru.alexbur.feature.scanned_data.presentation.utils.ScannedDataListItem
import ru.alexbur.uikit.theme.BackgroundColor
import ru.alexbur.uikit.theme.BottomNavigationHeight
import ru.alexbur.uikit.theme.Typography
import javax.inject.Inject

@Composable
fun ScannedDataScreen(
    navController: NavController,
    viewModel: ScannedDataViewModel = viewModel(modelClass = ScannedDataViewModel::class.java,
        key = null,
        factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ScannedDataComponent.getComponent().getViewModel() as T
            }
        })
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val viewStateLifecycleAware = remember(viewModel.viewState, lifecycleOwner) {
        viewModel.viewState.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state = viewStateLifecycleAware.collectAsState(initial = ScannedDataViewState.Initial)
    val viewEventLifecycleAware = remember(viewModel.viewEvent, lifecycleOwner) {
        viewModel.viewEvent.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val viewEvent = viewEventLifecycleAware.collectAsState(initial = null)
    val data = remember { mutableStateOf(emptyList<ScannedDataListItem>()) }
    val snackBarHostState = SnackbarHostState()
    LaunchedEffect(key1 = state.value, key2 = viewEvent.value) {
        when (val newState = state.value) {
            ScannedDataViewState.Initial -> Unit
            is ScannedDataViewState.ShowScannedData -> {
                data.value = newState.data
            }
        }
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
            text = stringResource(id = R.string.scanned_data_title),
            textAlign = TextAlign.Center,
            style = Typography.titleMedium
        )

        ScannedDataList(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            data = data.value
        )
    }

    UniversityWmsSnackBarHost(hostState = snackBarHostState)
}

class ScannedDataScreenFactory @Inject constructor() : NavigationScreenFactory {

    companion object Companion : NavigationFactory.NavigationFactoryCompanion

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Nested)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = route
        ) {
            ScannedDataScreen(navGraph)
        }
    }
}