@file:Suppress("LocalVariableName")

package com.thezayin.common.neumorphic.widgets

import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.thezayin.common.neumorphic.ConstantColor.NeumorphismLightBackgroundColor
import com.thezayin.common.neumorphic.ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK
import com.thezayin.common.neumorphic.ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT
import com.thezayin.common.neumorphic.Convexity
import com.thezayin.common.neumorphic.LightSource
import com.thezayin.common.neumorphic.ext.backgroundShadow
import com.thezayin.common.neumorphic.ext.foregroundShadow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShadeButton(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 10f,
    cornerRadius: Dp = 0.dp,
    text: String,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    textColor: Color = Color.Black,
    onClick: () -> Unit,
    backgroundColor: Color = NeumorphismLightBackgroundColor,
    fontWeight: FontWeight? = null,
    width: Dp? = null,
    height: Dp? = null,
    convexity: Convexity = Convexity.CONVEX
) {
    var currentOffset by remember { mutableFloatStateOf(offset) }
    LaunchedEffect(offset) { // Use LaunchedEffect to monitor offset changes
        currentOffset = offset
    }
    val TAG = "ShadeButton"

    Card(
        modifier = if (convexity == Convexity.CONVEX) {
            modifier
                .backgroundShadow(
                    shadowColorLight,
                    shadowColorDark,
                    blurRadius,
                    lightSource,
                    currentOffset,
                    cornerRadius,
                )
                .pointerInteropFilter {
                    Log.e(TAG, "pointerInteropFilter motionEvent:${it.action}")
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            currentOffset = 0f

                        }

                        MotionEvent.ACTION_UP -> {
                            currentOffset = offset
                            onClick()
                        }

                        MotionEvent.ACTION_CANCEL -> {
                            currentOffset = offset
                        }
                    }
                    true
                }
        } else {
            modifier
                .foregroundShadow(
                    shadowColorLight,
                    shadowColorDark,
                    blurRadius,
                    lightSource,
                    currentOffset,
                    cornerRadius,
                )
                .backgroundShadow(
                    shadowColorLight,
                    shadowColorDark,
                    blurRadius,
                    lightSource,
                    currentOffset,
                    cornerRadius,
                )
        },
        shape = RoundedCornerShape(cornerRadius),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Box(
            modifier = if (width != null && height != null) Modifier
                .background(backgroundColor)
                .width(width)
                .height(height)
            else Modifier
                .background(backgroundColor)
                .padding(horizontal = 10.dp, vertical = 6.dp)

        ) {
            Text(
                text = text,
                modifier = Modifier
                    .align(Alignment.Center),
                fontSize = fontSize,
                fontStyle = fontStyle,
                color = textColor,
                fontWeight = fontWeight
            )
        }
    }
}
