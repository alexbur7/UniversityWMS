package ru.alexbur.core.presentation.snackbar

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.alexbur.uikit.snackbar.UniversityWmsSnackBar

@Composable
fun UniversityWmsSnackBarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    SnackbarHost(hostState = hostState, modifier) {
        val customVisuals = it.visuals as UniversityWmsSnackBarVisuals
        UniversityWmsSnackBar(customVisuals.message, customVisuals.status.color, customVisuals.status.iconRes)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
suspend fun SnackbarHostState.showSnackBar(
    message: String,
    status: SnackBarStatus,
    duration: SnackbarDuration = SnackbarDuration.Short
): SnackbarResult {
    return showSnackbar(UniversityWmsSnackBarVisuals(message = message, duration = duration, status = status))
}