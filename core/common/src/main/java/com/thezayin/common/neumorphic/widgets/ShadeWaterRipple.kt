package com.thezayin.common.neumorphic.widgets

import android.graphics.BlurMaskFilter
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.NativePaint
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.common.neumorphic.LightSource
import kotlin.math.sqrt

@Composable
fun ShadeWaterRipple(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    backgroundColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    blurRadius: Float = 29f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 30f,
    borderWidth: Dp = 10.dp,
    size: Dp,
) {
    var click by remember { mutableIntStateOf(0) }
    Box(modifier = Modifier
        .size(size)
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) {
            click += 1
        }) {
        if (click > 0) {
            ShadeWater(
                modifier = modifier,
                size = size,
                initialValue = 0.dp
            )
        }
    }
}

@Composable
fun ShadeWater(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    backgroundColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    blurRadius: Float = 29f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 30f,
    borderWidth: Dp = 10.dp,
    size: Dp,
    initialValue: Dp
) {
    val sizeCurrent by rememberInfiniteTransition(label = "").animateValue(
        initialValue = initialValue,
        targetValue = size,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(2 * 1000, easing = LinearEasing)
        ), label = ""
    )

    var rippleAlpha by remember { mutableFloatStateOf(1f) }
    LaunchedEffect(sizeCurrent) {
        rippleAlpha = 1f - sizeCurrent.value / size.value
    }

    Canvas(modifier = modifier.size(sizeCurrent)) {
        val startX = this.size.width / 2 - this.size.width / 2 / sqrt(2f)
        val endX = this.size.width - startX
        //light shadow brush
        val paintShadowLight = Paint().also { paint: Paint ->
            paint.asFrameworkPaint() //Convert custom drawing operations into rendering description objects that the underlying rendering engine can understand, thereby achieving more efficient and flexible drawing operations.
                .also { nativePaint: NativePaint ->
                    nativePaint.isAntiAlias = true //Set anti-aliasing
                    nativePaint.isDither = true //Turn on anti-shake
                    nativePaint.color = shadowColorLight.toArgb() //Set brush color
                    nativePaint.shader = LinearGradientShader(
                        Offset(startX, startX),
                        Offset(endX, endX),
                        mutableListOf(shadowColorLight, shadowColorLight.copy(alpha = 0f)),
                    )
                    if (offset > 0) nativePaint.maskFilter =
                        BlurMaskFilter(
                            blurRadius,
                            BlurMaskFilter.Blur.NORMAL
                        ) //Set blur filter effect
                }
        }
        //The offset of light shadows in the direction of the light source
        val backgroundShadowLightOffset: Offset = when (lightSource) {
            LightSource.LEFT_TOP -> Offset(-offset, -offset)
            LightSource.LEFT_BOTTOM -> Offset(-offset, offset)
            LightSource.RIGHT_TOP -> Offset(offset, -offset)
            LightSource.RIGHT_BOTTOM -> Offset(offset, offset)
            else -> {
                Offset(0f, 0f)
            }
        }
        //dark shadow brush
        val paintShadowDark = Paint().also { paint: Paint ->
            paint.asFrameworkPaint() //Convert custom drawing operations into rendering description objects that the underlying rendering engine can understand, thereby achieving more efficient and flexible drawing operations.
                .also { nativePaint: NativePaint ->
                    nativePaint.isAntiAlias = true //Set anti-aliasing
                    nativePaint.isDither = true //Turn on anti-shake
                    nativePaint.color = shadowColorDark.toArgb() //Set brush color
                    nativePaint.shader = LinearGradientShader(
                        Offset(startX, startX),
                        Offset(endX, endX),
                        mutableListOf(shadowColorDark.copy(alpha = 0f), shadowColorDark),
                    )
                    if (offset > 0) nativePaint.maskFilter =
                        BlurMaskFilter(
                            blurRadius,
                            BlurMaskFilter.Blur.NORMAL
                        ) //Set blur filter effect
                }
        }

        val paintShadow = Paint().also { paint: Paint ->
            paint.asFrameworkPaint() //Convert custom drawing operations into rendering description objects that the underlying rendering engine can understand, thereby achieving more efficient and flexible drawing operations.
                .also { nativePaint: NativePaint ->
                    nativePaint.isAntiAlias = true //Set anti-aliasing
                    nativePaint.isDither = true //Turn on anti-shake
                    nativePaint.color = shadowColorDark.toArgb() //Set brush color
                    nativePaint.shader = LinearGradientShader(
                        Offset(startX, startX),
                        Offset(endX, endX),
                        mutableListOf(shadowColorDark, backgroundColor),
                    )
                    if (offset > 0) nativePaint.maskFilter =
                        BlurMaskFilter(
                            blurRadius,
                            BlurMaskFilter.Blur.NORMAL
                        ) //Set blur filter effect
                }
        }
        //The offset of dark shadows in the direction of the light source
        val backgroundShadowDarkOffset: Offset = when (LightSource.opposite(lightSource)) {
            LightSource.LEFT_TOP -> Offset(-offset, -offset)
            LightSource.LEFT_BOTTOM -> Offset(-offset, offset)
            LightSource.RIGHT_TOP -> Offset(offset, -offset)
            LightSource.RIGHT_BOTTOM -> Offset(offset, offset)
            else -> {
                Offset(0f, 0f)
            }
        }
        drawIntoCanvas {

            //Canvas pan draws light shadows
            it.save()
            it.translate(backgroundShadowLightOffset.x, backgroundShadowLightOffset.y)
            paintShadowLight.style = PaintingStyle.Stroke
            paintShadowLight.strokeWidth = borderWidth.toPx()
            it.drawCircle(
                Offset(this.size.width / 2, this.size.height / 2),
                (this.size.width - borderWidth.toPx()) / 2,
                paintShadowLight.also { paint: Paint -> paint.alpha = rippleAlpha }
            )

            it.restore()
            //Canvas pan draws dark shadows
            it.save()
            it.translate(backgroundShadowDarkOffset.x, backgroundShadowDarkOffset.y)
            paintShadowDark.style = PaintingStyle.Stroke
            paintShadowDark.strokeWidth = borderWidth.toPx()
            it.drawCircle(
                Offset(this.size.width / 2, this.size.height / 2),
                (this.size.width - borderWidth.toPx()) / 2,
                paintShadowDark.also { paint: Paint -> paint.alpha = rippleAlpha }
            )
            it.restore()
            //Draw dark shadows without displacement
            it.save()
            paintShadow.style = PaintingStyle.Stroke
            paintShadow.strokeWidth = borderWidth.toPx()
            it.drawCircle(
                Offset(this.size.width / 2, this.size.height / 2),
                (this.size.width - borderWidth.toPx()) / 2,
                paintShadow.also { paint: Paint -> paint.alpha = rippleAlpha }
            )
            it.restore()
        }
    }
}