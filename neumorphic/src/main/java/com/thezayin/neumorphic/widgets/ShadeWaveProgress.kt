package com.thezayin.neumorphic.widgets

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.thezayin.neumorphic.ConstantColor
import com.thezayin.neumorphic.LightSource
import com.thezayin.neumorphic.ext.backgroundShadow
import com.thezayin.neumorphic.ext.foregroundShadow


@Composable
fun ShadeWaveProgress(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    backgroundColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    blurRadius: Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 10f,
    cornerRadius: Dp = 0.dp,
    width: Dp,
    height: Dp,
    colors: List<Color>,
    progress: Float = 0f,
    maxProgress: Float = height.value * LocalDensity.current.density,
    onProgress: (progress: Float) -> Unit
) {
    val density = LocalDensity.current.density
    val widthPx = width.value * density
    val heightPx = height.value * density
    val levelLine =
        remember { mutableFloatStateOf((maxProgress - progress) / maxProgress * heightPx) }
    Card(
        modifier = modifier
            .foregroundShadow(
                shadowColorLight,
                shadowColorDark,
                blurRadius,
                lightSource,
                offset,
                cornerRadius,
            )
            .backgroundShadow(
                shadowColorLight,
                shadowColorDark,
                blurRadius,
                lightSource,
                offset,
                cornerRadius,
            ),
        shape = RoundedCornerShape(cornerRadius),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Box(
            modifier = Modifier
                .background(backgroundColor)
                .width(width)
                .height(height)
                .pointerInput(Unit) {
                    detectVerticalDragGestures { change, _ ->
                        levelLine.floatValue = change.position.y
                        var progressCallback =
                            (heightPx - levelLine.floatValue) / heightPx * maxProgress
                        if (progressCallback < 0f) {
                            progressCallback = 0f
                        } else if (progressCallback > maxProgress) {
                            progressCallback = maxProgress
                        }
                        onProgress.invoke(progressCallback)
                    }
                }
        ) {
            val waveHeight = 30f //Wave amplitude
            val waveWidth = widthPx / 1.2f //wave length
            val waveWidthBelow = widthPx  //wave length
            val abovePath by remember { mutableStateOf(Path()) }
            val belowPath by remember { mutableStateOf(Path()) }
            val firstColor = colors[0]

            val animateValue by rememberInfiniteTransition(label = "").animateFloat(  //Create an infinite loop of floating point animations
                initialValue = 0f,  //animation initial value
                targetValue = waveWidth,  //animation target value
                animationSpec = infiniteRepeatable(  //Animation configuration parameters, the default is an infinite loop animation effect
                    animation = tween(
                        durationMillis = 1000,  //The duration of the animation in milliseconds
                        easing = CubicBezierEasing(
                            0.2f,
                            0.2f,
                            0.7f,
                            0.9f
                        )  //Animation easing effect
                    ),
                    repeatMode = RepeatMode.Restart  // Animated repeating pattern
                ), label = ""
            )
            val animateValueBelow by rememberInfiniteTransition(label = "").animateFloat(  //Create an infinite loop of floating point animations
                initialValue = waveWidthBelow,  //animation initial value
                targetValue = 0f,  //animation target value
                animationSpec = infiniteRepeatable(  //Animation configuration parameters, the default is an infinite loop animation effect
                    animation = tween(
                        durationMillis = 1000,  //The duration of the animation in milliseconds
                        easing = CubicBezierEasing(
                            0.2f,
                            0.2f,
                            0.7f,
                            0.9f
                        )  //Animation easing effect
                    ),
                    repeatMode = RepeatMode.Restart  //Animated repeating pattern
                ), label = ""
            )

            Canvas(modifier = Modifier.fillMaxSize()) {
                if (levelLine.floatValue == heightPx) return@Canvas
                belowPath.reset()
                belowPath.moveTo(
                    -waveWidthBelow + animateValueBelow,
                    levelLine.floatValue
                )  //Move to the initial point
                var i = -waveWidthBelow
                while (i < widthPx + waveWidthBelow) { //Draw the path of a wave in a loop
                    belowPath.relativeQuadraticBezierTo(
                        waveWidthBelow / 4f,
                        -waveHeight,
                        waveWidthBelow / 2f,
                        0f
                    )
                    belowPath.relativeQuadraticBezierTo(
                        waveWidthBelow / 4f,
                        waveHeight,
                        waveWidthBelow / 2f,
                        0f
                    )
                    i += waveWidthBelow
                }
                belowPath.lineTo(this.size.width, this.size.height)
                belowPath.lineTo(0f, this.size.height)
                belowPath.close()
                drawPath(
                    path = belowPath,
                    color = firstColor.copy(alpha = 0.5f)
                )

                //waves above
                abovePath.reset()
                abovePath.moveTo(
                    -waveWidth + animateValue,
                    levelLine.floatValue
                )  //Move to the initial point
                var j = -waveWidth
                while (j < widthPx + waveWidth) {  //Draw the path of a wave in a loop
                    abovePath.relativeQuadraticBezierTo(
                        waveWidth / 4f,
                        -waveHeight,
                        waveWidth / 2f,
                        0f
                    )
                    abovePath.relativeQuadraticBezierTo(
                        waveWidth / 4f,
                        waveHeight,
                        waveWidth / 2f,
                        0f
                    )
                    j += waveWidth
                }
                abovePath.lineTo(this.size.width, this.size.height)
                abovePath.lineTo(0f, this.size.height)
                abovePath.close()
                drawPath(
                    path = abovePath,
                    brush = Brush.linearGradient(
                        colors = colors,
                        start = Offset(0f, levelLine.floatValue), //Starting point coordinates
                        end = Offset(0f, size.height), //End point coordinates
                    )
                )
            }
        }
    }
}

