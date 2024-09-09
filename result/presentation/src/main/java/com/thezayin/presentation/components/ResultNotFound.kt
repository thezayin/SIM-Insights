package com.thezayin.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.common.neumorphic.widgets.ShadeCard
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Preview
@Composable
fun ResultNotFound(
    modifier: Modifier = Modifier,
) {
    ShadeCard(
        modifier = modifier,
        cornerRadius = 10.sdp,
        backgroundColor = ConstantColor.NeumorphismLightBackgroundColor
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.sdp, vertical = 35.sdp)
                .background(ConstantColor.NeumorphismLightBackgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Data Not Found in our Database, Try other servers to get Result.",
                modifier = Modifier.padding(top = 15.sdp),
                fontSize = 14.ssp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_regular)),
                color = colorResource(id = com.thezayin.values.R.color.text_color),
            )
        }
    }
}