package com.thezayin.neumorphic.widgets

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.thezayin.neumorphic.ConstantColor
import com.thezayin.neumorphic.LightSource
import com.thezayin.neumorphic.Toggle
import com.thezayin.neumorphic.ext.backgroundShadow
import com.thezayin.neumorphic.ext.foregroundShadow

@Composable
fun ShadeToggle(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 10f,
    backgroundColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    width: Dp = 60.dp,
    height: Dp = 30.dp,
    toggle: Toggle = Toggle.OFF,
    onToggleChange: (open: Boolean) -> Unit,
    colorOn: Color = Color(0xff1aef0b)
) {
    val TAG = "ShadeToggle"
    val borderWidth = height / 21 //stroke width
    val innerWidth = width - borderWidth * 2 //internal layout width dp
    val innerWidthPx = with(LocalDensity.current) { innerWidth.toPx() }//internal layout width px
    val innerHeight = height - borderWidth * 2 ////Internal layout height
    val innerPadding = height / 7 + borderWidth //internal layout padding dp
    val innerPaddingPx =
        with(LocalDensity.current) { innerPadding.toPx() }//internal layout padding px
    val circleButtonSize = height / 210 * 145 //round button size dp
    val circleButtonSizePx =
        with(LocalDensity.current) { circleButtonSize.toPx() } //round button size px
    var currentToggle by remember { mutableStateOf(toggle) } //Current switch status

    var currentTargetValue by remember { mutableStateOf(innerPaddingPx) }
    val offsetCircleButton by animateFloatAsState(
        targetValue = currentTargetValue,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    val innerBackgroundColor = remember { Animatable(backgroundColor) }

    LaunchedEffect(currentToggle) { // Use LaunchedEffect to monitor offset changes
        currentTargetValue =
            if (currentToggle == Toggle.OFF) innerPaddingPx else innerWidthPx - innerPaddingPx - circleButtonSizePx
        innerBackgroundColor.animateTo(if (currentToggle == Toggle.OFF) backgroundColor else colorOn)
    }

    Card(
        modifier = modifier
            .backgroundShadow(
                shadowColorLight,
                shadowColorDark,
                blurRadius * 2,
                lightSource,
                offset,
                height / 2,
            )
            .clickable(//Remove the default water ripple effect
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                currentToggle = if (currentToggle == Toggle.OFF) Toggle.ON else Toggle.OFF
                onToggleChange.invoke(currentToggle == Toggle.ON)
            },

        shape = RoundedCornerShape(height / 2),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Box(
            Modifier
                .background(backgroundColor)
                .size(width, height)
        ) {

            Card(
                modifier = Modifier
                    .foregroundShadow(
                        shadowColorLight,
                        shadowColorDark,
                        blurRadius,
                        lightSource,
                        offset,
                        innerHeight / 2,
                    )
                    .align(Alignment.Center),
                shape = RoundedCornerShape(innerHeight / 2),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Box(
                    Modifier
                        .background(innerBackgroundColor.value)
                        .size(innerWidth, innerHeight)
                ) {
                    Card(
                        modifier = Modifier
                            .offset { IntOffset(offsetCircleButton.toInt(), 0) }
                            .backgroundShadow(
                                shadowColorLight,
                                shadowColorDark,
                                blurRadius,
                                lightSource,
                                offset / 3,
                                circleButtonSize / 2,
                            )
                            .align(Alignment.CenterStart),
                        shape = RoundedCornerShape(circleButtonSize / 2),
                        elevation = CardDefaults.cardElevation(0.dp)
                    ) {
                        Box(
                            Modifier
                                .background(backgroundColor)
                                .size(circleButtonSize, circleButtonSize)
                        ) {

                        }
                    }
                }
            }
        }
    }
}