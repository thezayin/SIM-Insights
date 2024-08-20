package com.thezayin.common.neumorphic.widgets

import android.view.MotionEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.thezayin.common.neumorphic.ConstantColor.NeumorphismLightBackgroundColor
import com.thezayin.common.neumorphic.ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK
import com.thezayin.common.neumorphic.ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT
import com.thezayin.common.neumorphic.LightSource
import com.thezayin.common.neumorphic.ext.backgroundShadow
import com.thezayin.common.neumorphic.ext.foregroundShadow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShadeRadioButton(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    cornerRadius: Dp = 0.dp,
    backgroundColor: Color = NeumorphismLightBackgroundColor,
    size: Dp,
    painter: Painter? = null,
    iconColor: Color = Color.Black,
    iconPadding: Dp = 0.dp,
    onSelectedChanged: (selected: Boolean) -> Unit,
    selected: Boolean = false,
    selectedEnable: Boolean = true
) {
    val selectedPrivate = remember { mutableStateOf(selected) }
    selectedPrivate.value = selected

    Card(
        modifier = modifier
            .foregroundShadow(
                shadowColorLight,
                shadowColorDark,
                blurRadius,
                lightSource,
                if (selectedPrivate.value) 22f else 0f,
                cornerRadius,
            )
            .backgroundShadow(
                shadowColorLight,
                shadowColorDark,
                blurRadius,
                lightSource,
                if (selectedPrivate.value) 0f else 11f,
                cornerRadius,
            )
            .pointerInteropFilter {
                if (selectedEnable) {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            selectedPrivate.value = !selectedPrivate.value
                            onSelectedChanged.invoke(selectedPrivate.value)

                        }

                        MotionEvent.ACTION_UP -> {

                        }
                    }
                }
                true
            },
        shape = RoundedCornerShape(cornerRadius),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Box(
            Modifier
                .background(backgroundColor)
                .padding(size / 5)
        ) {
            Image(
                painter = painter!!,
                contentDescription = null,
                modifier = Modifier
                    .size(size)
                    .align(Alignment.Center)
                    .padding(iconPadding),
                colorFilter = ColorFilter.tint(iconColor)
            )
        }
    }
}


