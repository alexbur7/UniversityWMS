package ru.alexbur.feature.scanner.presentation

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
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
import ru.alexbur.core.domain.navigation.Router
import ru.alexbur.core.presentation.ViewEvent
import ru.alexbur.core.presentation.snackbar.UniversityWmsSnackBarHost
import ru.alexbur.core.presentation.snackbar.showSnackBar
import ru.alexbur.feature.scanner.R
import ru.alexbur.feature.scanner.di.ScannerComponent
import ru.alexbur.feature.scanner.presentation.scanner_view.ScannerViewScreen
import ru.alexbur.uikit.theme.BackgroundColor
import ru.alexbur.uikit.theme.BottomNavigationHeight
import ru.alexbur.uikit.theme.IconTint
import javax.inject.Inject

@Composable
fun ScannerScreen(
    navController: NavController,
    viewModel: ScannerViewModel = viewModel(modelClass = ScannerViewModel::class.java,
        key = null,
        factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ScannerComponent.getComponent().getViewModel() as T
            }
        })
) {
    val context = LocalContext.current
    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted -> hasCameraPermission = granted }
    )
    val lifecycleOwner = LocalLifecycleOwner.current
    val viewEventLifecycleAware = remember(viewModel.viewEvent, lifecycleOwner) {
        viewModel.viewEvent.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val viewEvent = viewEventLifecycleAware.collectAsState(initial = null)
    val snackBarHostState = SnackbarHostState()

    LaunchedEffect(viewEvent.value) {
        when (val event = viewEvent.value) {
            is ViewEvent.Navigation -> Unit
            is ViewEvent.ShowSnackBar -> {
                snackBarHostState.showSnackBar(event.text, event.status)
            }
            else -> Unit
        }
    }

    LaunchedEffect(key1 = true) {
        launcher.launch(Manifest.permission.CAMERA)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
        ) {
            if (hasCameraPermission) {
                ScannerViewScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    onScanBarcode = viewModel::scanBarcode
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, bottom = BottomNavigationHeight + 24.dp),
                text = stringResource(id = R.string.scanner_instruction),
                color = IconTint,
                style = TextStyle(textAlign = TextAlign.Center, fontWeight = FontWeight.Medium, fontSize = 18.sp),
            )
        }
    }

    UniversityWmsSnackBarHost(hostState = snackBarHostState)
}

class ScannerScreenFactory @Inject constructor() : NavigationScreenFactory {

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Nested)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = Router.ScannerRouter.route
        ) {
            ScannerScreen(navGraph)
        }
    }
}