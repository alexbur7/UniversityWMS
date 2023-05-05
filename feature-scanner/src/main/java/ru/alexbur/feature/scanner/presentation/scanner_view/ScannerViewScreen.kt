package ru.alexbur.feature.scanner.presentation.scanner_view

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import ru.alexbur.feature.scanner.presentation.analyzer.QRCodeAnalyzer
import ru.alexbur.uikit.buttons.UniversityWmsButton

@Composable
fun ScannerViewScreen(modifier: Modifier, onScanBarcode: (String) -> Unit) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val scanFlag = remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        AndroidView(
            modifier = modifier
                .height(350.dp)
                .clip(RoundedCornerShape(24.dp)),
            factory = { context ->
                val previewView = PreviewView(context)
                val preview = Preview.Builder().build()
                val selector = CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                    .build()
                preview.setSurfaceProvider(previewView.surfaceProvider)
                val imageAnalysis = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                imageAnalysis.setAnalyzer(
                    ContextCompat.getMainExecutor(context),
                    QRCodeAnalyzer { result ->
                        if (scanFlag.value) return@QRCodeAnalyzer
                        result?.let {
                            scanFlag.value = true
                            onScanBarcode(it)
                        }
                    }
                )

                runCatching {
                    cameraProviderFuture.get().bindToLifecycle(
                        lifecycleOwner,
                        selector,
                        preview,
                        imageAnalysis
                    )
                }

                return@AndroidView previewView
            },
        )
        if (scanFlag.value) {
            UniversityWmsButton(modifier = Modifier
                .wrapContentWidth()
                .padding(top = 16.dp), text = "Продолжить") {
                scanFlag.value = false
            }
        }
    }
}