package com.thezayin.neumorphic.widgets

import android.content.Context
import android.view.MotionEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.thezayin.neumorphic.BlurProvider
import com.thezayin.neumorphic.ConstantColor.NeumorphismLightBackgroundColor
import com.thezayin.neumorphic.ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK
import com.thezayin.neumorphic.ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT
import com.thezayin.neumorphic.LightSource
import com.thezayin.neumorphic.Shape
import com.thezayin.neumorphic.ext.backgroundShadow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShadeIconButton(
    context: Context,
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = BlurProvider.getDefaultBlurRadius(context),
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 20f,
    cornerRadius: Dp = 0.dp,
    onClick: () -> Unit,
    backgroundColor: Color = NeumorphismLightBackgroundColor,
    size: Dp,
    painter: Painter,
    shape: Int = Shape.Circle,
    iconPadding: Dp = 0.dp

) {
    var currentOffset by remember { mutableFloatStateOf(offset) }
    LaunchedEffect(offset) { // Use LaunchedEffect to monitor offset changes
        currentOffset = offset
    }
    var currentCornerRadius by remember { mutableStateOf(cornerRadius) }
    if (shape == Shape.Circle) {
        currentCornerRadius = (size + size / 5 * 2) / 2
    }

    Card(
        modifier = modifier
            .backgroundShadow(
                shadowColorLight,
                shadowColorDark,
                blurRadius,
                lightSource,
                currentOffset,
                currentCornerRadius,
            )
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        currentOffset = 0f

                    }

                    MotionEvent.ACTION_UP -> {
                        currentOffset = offset
                        onClick()
                    }
                }
                true
            },
        shape = RoundedCornerShape(currentCornerRadius),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Box(
            Modifier
                .background(backgroundColor)
                .padding(size / 5)
        ) {
            Image(
                painter = painter,
                contentDescription = "ShadeImageButton",
                modifier = Modifier
                    .size(size)
                    .padding(iconPadding),
            )
        }
    }
}
