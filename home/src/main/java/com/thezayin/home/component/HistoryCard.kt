package com.thezayin.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.common.neumorphic.widgets.ShadeCard
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Preview
@Composable
fun HistoryCard(
    number: String = "0312-1234567",
    resultSuccess: Boolean = true
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.sdp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShadeCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.sdp),
            cornerRadius = 10.sdp,
            backgroundColor = ConstantColor.NeumorphismLightBackgroundColor
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.sdp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = number,
                    fontSize = 12.ssp,
                    color = colorResource(id = com.thezayin.values.R.color.text_color),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
                )
                ShadeCard(
                    cornerRadius = 40.sdp,
                    modifier = Modifier
                        .width(70.sdp)
                        .height(20.sdp)
                )
                {
                    Text(
                        text = if (resultSuccess) "Success" else "Failed",
                        fontSize = 12.ssp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = colorResource(id = com.thezayin.values.R.color.text_color),
                        fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
                    )
                }
            }
        }
    }
}