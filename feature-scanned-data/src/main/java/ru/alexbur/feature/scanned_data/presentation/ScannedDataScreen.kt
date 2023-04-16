package ru.alexbur.feature.scanned_data.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
import ru.alexbur.feature.scanned_data.R
import ru.alexbur.feature.scanned_data.di.ScannedDataComponent
import ru.alexbur.feature.scanned_data.presentation.list.ScannedDataList
import ru.alexbur.feature.scanned_data.presentation.utils.ScannedDataListItem
import ru.alexbur.uikit.theme.Secondary
import javax.inject.Inject

@Composable
fun ScannedDataScreen(
    navController: NavController?,
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
    val data = remember { mutableStateOf(emptyList<ScannedDataListItem>()) }
    LaunchedEffect(key1 = state.value) {
        when (val newState = state.value) {
            ScannedDataViewState.Initial -> Unit
            is ScannedDataViewState.ShowScannedData -> {
                data.value = newState.data
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 60.dp, end = 60.dp),
            text = stringResource(id = R.string.scanned_data_title),
            textAlign = TextAlign.Center,
            style = TextStyle(color = Secondary, fontSize = 20.sp, fontWeight = FontWeight(600))
        )

        ScannedDataList(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            data = data.value
        )
    }
}

class ScannedDataScreenFactory @Inject constructor() : NavigationScreenFactory {

    companion object Companion : NavigationFactory.NavigationFactoryCompanion

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Main)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = route
        ) {
            ScannedDataScreen(navGraph)
        }
    }
}

@Preview
@Composable
internal fun ScannedDataScreenPreview() {
    ScannedDataScreen(navController = null)
}