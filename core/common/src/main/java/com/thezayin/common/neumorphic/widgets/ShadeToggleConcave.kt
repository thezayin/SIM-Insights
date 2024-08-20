package com.thezayin.common.neumorphic.widgets

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.common.neumorphic.LightSource
import com.thezayin.common.neumorphic.Toggle
import com.thezayin.common.neumorphic.ext.backgroundShadow
import com.thezayin.common.neumorphic.ext.foregroundShadow

@Composable
fun ShadeToggleConcave(
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
    fontSize: TextUnit = TextUnit.Unspecified,

    ) {
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

    var currentTargetValue by remember { mutableFloatStateOf(innerPaddingPx) }
    val offsetCircleButton by animateFloatAsState(
        targetValue = currentTargetValue,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ), label = ""
    )


    LaunchedEffect(currentToggle) { // Use LaunchedEffect to monitor offset changes
        currentTargetValue =
            if (currentToggle == Toggle.OFF) innerPaddingPx else innerWidthPx - innerPaddingPx - circleButtonSizePx
    }

    Card(
        modifier = modifier
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
                        .background(backgroundColor)
                        .size(innerWidth, innerHeight)
                ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .width(innerWidth)
                            .wrapContentSize()
                    ) {
                        Text(
                            text = "On",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center,
                            color = Color.Gray,
                            fontSize = fontSize
                        )
                        Text(
                            text = "Off",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center,
                            color = Color.Gray,
                            fontSize = fontSize
                        )
                    }
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