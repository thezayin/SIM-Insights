@file:Suppress("NAME_SHADOWING")

package com.thezayin.common.neumorphic.widgets

import android.view.MotionEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.common.neumorphic.LightSource
import com.thezayin.common.neumorphic.ext.backgroundShadow
import com.thezayin.common.neumorphic.ext.foregroundShadow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShadeCard(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 10f,
    cornerRadius: Dp = 0.dp,
    onClick: () -> Unit = {},
    backgroundColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    selectedEnable: Boolean = false,
    selected: Boolean = false,
    content: @Composable () -> Unit,
) {
    var selected by remember { mutableStateOf(selected) }
    var currentOffsetBack by remember { mutableFloatStateOf(offset) }
    var currentOffsetFore by remember { mutableFloatStateOf(0f) }
    LaunchedEffect(offset) {
        if (selected) {
            currentOffsetBack = 0f
            currentOffsetFore = offset
        } else {
            currentOffsetBack = offset
            currentOffsetFore = 0f
        }
    }

    Card(
        modifier = modifier
            .foregroundShadow(
                shadowColorLight,
                shadowColorDark,
                blurRadius,
                lightSource,
                currentOffsetFore,
                cornerRadius,
            )
            .backgroundShadow(
                shadowColorLight,
                shadowColorDark,
                blurRadius,
                lightSource,
                currentOffsetBack,
                cornerRadius,
            )
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        if (selectedEnable) {
                            selected = !selected
                            if (selected) {
                                currentOffsetBack = 0f
                                currentOffsetFore = 22f
                            } else {
                                currentOffsetBack = 10f
                                currentOffsetFore = 0f
                            }
                        }
                    }

                    MotionEvent.ACTION_UP -> {
                        onClick()
                    }
                }
                true
            },
        shape = RoundedCornerShape(cornerRadius),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Box(
            modifier
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}
