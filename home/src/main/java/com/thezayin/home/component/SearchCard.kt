package com.thezayin.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.common.neumorphic.Convexity
import com.thezayin.common.neumorphic.widgets.ShadeCard
import com.thezayin.common.neumorphic.widgets.ShadeSearchBar
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun SearchCard(
    modifier: Modifier,
    number: MutableState<TextFieldValue>,
    showWarning: MutableState<Boolean>,
    onSearchClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = ConstantColor.NeumorphismLightBackgroundColor)
            .padding(horizontal = 15.sdp)
            .padding(top = 30.sdp, bottom = 10.sdp),
    ) {
        Text(
            text = "Enter Number",
            textAlign = TextAlign.Center,
            color = colorResource(id = com.thezayin.values.R.color.text_color),
            fontSize = 14.ssp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
            modifier = Modifier.padding(bottom = 12.sdp)
        )
        Column(verticalArrangement = Arrangement.spacedBy(40.sdp)) {
            ShadeSearchBar(
                value = number.value,
                onValueChange = {
                    number.value = it
                },
                cornerRadius = 20.sdp,
                convexity = Convexity.CONCAVE,
                placeholderText = "03211234567",
                fontSize = 12.ssp,
            )
            if (showWarning.value) {
                Text(
                    text = "Please enter a valid number",
                    color = colorResource(id = com.thezayin.values.R.color.red),
                    fontSize = 10.ssp,
                    fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
                )
            }
            ShadeCard(
                onClick = {
                    if (number.value.text.length != 11) {
                        showWarning.value = true
                        return@ShadeCard
                    } else {
                        showWarning.value = false
                        onSearchClick(number.value.text)
                    }
                }, cornerRadius = 40.sdp, modifier = Modifier
                    .fillMaxWidth()
                    .height(40.sdp)
            ) {
                Text(
                    text = "Search",
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = com.thezayin.values.R.color.black),
                    fontSize = 16.ssp,
                    fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
                )
            }
        }
    }
}