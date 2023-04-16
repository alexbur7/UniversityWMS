package ru.alexbur.feature.authorization.presentation.shape

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection


class CustomShape(private val radius: Float) : Shape {

    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        return Outline.Generic(
            path = createPath(size)
        )
    }

    private fun createPath(size: Size): Path {
        return Path().apply {
            reset()
            arcTo(
                rect = Rect(
                    left = 0f,
                    top =  -radius,
                    right = size.width,
                    bottom = radius * 3
                ),
                startAngleDegrees = -180f,
                sweepAngleDegrees = 180.0f,
                forceMoveTo = false
            )
            lineTo(x = size.width, y = size.height)
            lineTo(x = 0f, y = size.height)
            lineTo(x = 0f, y = radius)
            close()
        }
    }
}