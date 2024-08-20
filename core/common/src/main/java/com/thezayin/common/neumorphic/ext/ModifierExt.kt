@file:Suppress("LocalVariableName")

package com.thezayin.common.neumorphic.ext

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.NativePaint
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.common.neumorphic.LightSource
import com.thezayin.common.neumorphic.Shape

/**
 * backgroundShadowExtensionFunction
 */
fun Modifier.backgroundShadow(
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 10f,
    cornerRadius: Dp = 0.dp,
    shape: Int = Shape.Rectangle,
    borderWidth: Dp = 20.dp,//Shape.Circle.mediumAsRingWidth
) = then(object : DrawModifier {
    override fun ContentDrawScope.draw() {
        //lightShadowBrush
        val paintShadowLight = Paint().also { paint: Paint ->
            paint.asFrameworkPaint() //Convert custom drawing operations into rendering description objects that the underlying rendering engine can understand, thereby achieving more efficient and flexible drawing operations.
                .also { nativePaint: NativePaint ->
                    nativePaint.isAntiAlias = true //Set anti-aliasing
                    nativePaint.isDither = true //Turn on anti-shake
                    nativePaint.color = shadowColorLight.toArgb() //Set brush color
                    if (offset > 0) nativePaint.maskFilter = BlurMaskFilter(
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
        //DARK SHADOW BRUSH
        val paintShadowDark = Paint().also { paint: Paint ->
            paint.asFrameworkPaint() //Convert custom drawing operations into rendering description objects that the underlying rendering engine can understand, thereby achieving more efficient and flexible drawing operations.
                .also { nativePaint: NativePaint ->
                    nativePaint.isAntiAlias = true //Set anti-aliasing
                    nativePaint.isDither = true //Turn on anti-shake
                    nativePaint.color = shadowColorDark.toArgb() //Set brush color
                    if (offset > 0) nativePaint.maskFilter = BlurMaskFilter(
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
            when (shape) {
                Shape.Circle -> {
                    paintShadowLight.style = PaintingStyle.Stroke
                    paintShadowLight.strokeWidth = borderWidth.toPx()
                    it.drawCircle(
                        Offset(this.size.width / 2, this.size.height / 2),
                        (this.size.width - borderWidth.toPx()) / 2,
                        paintShadowLight
                    )
                }

                Shape.Rectangle -> {
                    it.drawRoundRect(
                        0f,
                        0f,
                        this.size.width,
                        this.size.height,
                        cornerRadius.toPx(),
                        cornerRadius.toPx(),
                        paintShadowLight
                    )
                }
            }
            it.restore()
            //Canvas pan draws dark shadows
            it.save()
            it.translate(backgroundShadowDarkOffset.x, backgroundShadowDarkOffset.y)
            when (shape) {
                Shape.Circle -> {
                    paintShadowDark.style = PaintingStyle.Stroke
                    paintShadowDark.strokeWidth = borderWidth.toPx()
                    it.drawCircle(
                        Offset(this.size.width / 2, this.size.height / 2),
                        (this.size.width - borderWidth.toPx()) / 2,
                        paintShadowDark
                    )
                }

                Shape.Rectangle -> {
                    it.drawRoundRect(
                        0f,
                        0f,
                        this.size.width,
                        this.size.height,
                        cornerRadius.toPx(),
                        cornerRadius.toPx(),
                        paintShadowDark
                    )
                }
            }
            it.restore()
        }
        drawContent()
    }
})

/**
 * Foreground shadow extension function
 */
fun Modifier.foregroundShadow(
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 22f,
    cornerRadius: Dp = 0.dp,
) = then(object : DrawModifier {
    override fun ContentDrawScope.draw() {
        drawContent()
        //light shadow brush
        val paintShadowLight = Paint().also { paint: Paint ->
            paint.asFrameworkPaint() //Convert custom drawing operations into rendering description objects that the underlying rendering engine can understand, thereby achieving more efficient and flexible drawing operations.
                .also { nativePaint: NativePaint ->
                    nativePaint.isAntiAlias = true //Set anti-aliasing
                    nativePaint.isDither = true //Turn on anti-shake
                    nativePaint.color = shadowColorLight.toArgb() //Set brush color
                    nativePaint.style = android.graphics.Paint.Style.STROKE
                    nativePaint.strokeWidth = offset
                    if (offset > 0) nativePaint.maskFilter = BlurMaskFilter(
                        blurRadius,
                        BlurMaskFilter.Blur.NORMAL
                    ) //Set blur filter effect
                }
        }

        //The offset of light shadows in the direction of the light source
        val backgroundShadowLightOffset: Offset = when (LightSource.opposite(lightSource)) {
            LightSource.LEFT_TOP -> Offset(offset, offset)
            LightSource.LEFT_BOTTOM -> Offset(offset, -offset)
            LightSource.RIGHT_TOP -> Offset(-offset, offset)
            LightSource.RIGHT_BOTTOM -> Offset(-offset, -offset)
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
                    nativePaint.style = android.graphics.Paint.Style.STROKE
                    nativePaint.strokeWidth = offset //Set stroke width
                    if (offset > 0) nativePaint.maskFilter = BlurMaskFilter(
                        blurRadius,
                        BlurMaskFilter.Blur.NORMAL
                    ) //Set blur filter effect
                }
        }
        //The offset of dark shadows in the direction of the light source
        val backgroundShadowDarkOffset: Offset = when (lightSource) {
            LightSource.LEFT_TOP -> Offset(offset, offset)
            LightSource.LEFT_BOTTOM -> Offset(offset, -offset)
            LightSource.RIGHT_TOP -> Offset(-offset, offset)
            LightSource.RIGHT_BOTTOM -> Offset(-offset, -offset)
            else -> {
                Offset(0f, 0f)
            }
        }

        drawIntoCanvas {
            //Draw dark shadows
            it.save()
            val pathShadowDark = Path().also { path ->
                path.moveTo(0f, 0f)
                path.addRoundRect(
                    RoundRect(
                        0f,
                        0f,
                        this.size.width,
                        this.size.height,
                        cornerRadius.toPx(),
                        cornerRadius.toPx()
                    )
                )
            }
            it.clipPath(pathShadowDark)
            it.translate(backgroundShadowDarkOffset.x, backgroundShadowDarkOffset.y)
            it.drawRoundRect(
                -offset,
                -offset,
                this.size.width + offset,
                this.size.height + offset,
                cornerRadius.toPx(),
                cornerRadius.toPx(),
                paintShadowDark
            )
            it.restore()
            //Draw light shadows
            it.save()
            val pathShadowLight = Path().also { path ->
                path.moveTo(0f, 0f)
                path.addRoundRect(
                    RoundRect(
                        0f,
                        0f,
                        this.size.width,
                        this.size.height,
                        cornerRadius.toPx(),
                        cornerRadius.toPx()
                    )
                )
            }
            it.clipPath(pathShadowLight)
            it.translate(backgroundShadowLightOffset.x, backgroundShadowLightOffset.y)
            it.drawRoundRect(
                -offset,
                -offset,
                this.size.width + offset,
                this.size.height + offset,
                cornerRadius.toPx(),
                cornerRadius.toPx(),
                paintShadowLight
            )
            it.restore()
        }
    }
})

