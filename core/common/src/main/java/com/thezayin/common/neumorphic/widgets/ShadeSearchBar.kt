package com.thezayin.common.neumorphic.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.common.neumorphic.Convexity
import com.thezayin.common.neumorphic.LightSource
import com.thezayin.common.neumorphic.ext.backgroundShadow
import com.thezayin.common.neumorphic.ext.foregroundShadow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShadeSearchBar(
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 10f,
    cornerRadius: Dp = 0.dp,
    backgroundColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    fontSize: TextUnit = 16.sp,
    height: Dp = 55.dp,
    convexity: Convexity = Convexity.CONVEX,
    placeholderText: String? = null,
) {
    var text by remember { mutableStateOf(value) }
    val convexityType by remember { mutableStateOf(convexity) }
    var currentOffsetBack by remember { mutableFloatStateOf(offset) }
    var currentOffsetFore by remember { mutableFloatStateOf(0f) }
    LaunchedEffect(offset) { // Use LaunchedEffect to monitor offset changes
        if (convexityType == Convexity.CONVEX) {
            currentOffsetBack = offset
            currentOffsetFore = 0f
        } else {
            currentOffsetBack = 0f
            currentOffsetFore = offset
        }
    }
    Card(
        modifier = Modifier
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
            ),
        shape = RoundedCornerShape(cornerRadius),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Box(
            Modifier
                .background(backgroundColor)
        ) {
            Row(
                Modifier
                    .background(backgroundColor)
                    .wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    modifier = Modifier
                        .background(backgroundColor)
                        .weight(1f)
                        .height(height)
                        .padding(horizontal = 10.dp),
                    value = text,
                    onValueChange = { newValue ->
                        if (newValue.text.length <= 11) {
                            text = newValue
                            onValueChange.invoke(newValue)
                        }
                    },
                    placeholder = {
                        Text(
                            text = placeholderText.toString(),
                            fontSize = fontSize,
                            fontStyle = FontStyle.Italic,
                            fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
                            color = colorResource(id = com.thezayin.values.R.color.text_color),
                            textAlign = TextAlign.Start,
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        cursorColor = colorResource(id = com.thezayin.values.R.color.text_color),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                    ),
                    textStyle = TextStyle(
                        color = colorResource(id = com.thezayin.values.R.color.text_color),
                        fontSize = fontSize,
                        fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
                        fontStyle = FontStyle.Italic,
                    ),
                    singleLine = true,
                )
            }
        }
    }
}