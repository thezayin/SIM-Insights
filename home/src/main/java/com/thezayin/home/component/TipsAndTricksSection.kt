package com.thezayin.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun TipsAndTricksSection() {
    Column(
        modifier = Modifier.padding(horizontal = 20.sdp)
    ) {
        ShadeCard(
            modifier = Modifier.fillMaxWidth(),
            cornerRadius = 10.sdp,
            backgroundColor = ConstantColor.NeumorphismLightBackgroundColor
        ) {
            Column(
                modifier = Modifier.padding(16.sdp),
                verticalArrangement = Arrangement.spacedBy(8.sdp)
            ) {
                Text(
                    text = "Tips & Tricks",
                    modifier = Modifier.padding(top = 15.sdp),
                    fontSize = 14.ssp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_regular)),
                    color = colorResource(id = com.thezayin.values.R.color.text_color),
                )
                Text(
                    text = "• Monitor your data usage to avoid extra charges.\n• Enable data saving mode for efficient usage.",
                    modifier = Modifier.padding(top = 10.sdp),
                    fontSize = 11.ssp,
                    textAlign = TextAlign.Start,
                    fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_regular)),
                    color = colorResource(id = com.thezayin.values.R.color.text_color),
                )
            }
        }
    }
}