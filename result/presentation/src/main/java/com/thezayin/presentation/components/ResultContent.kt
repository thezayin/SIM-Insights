package com.thezayin.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.common.neumorphic.widgets.ShadeCard
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun ResultContent(
    modifier: Modifier,
    name: String = "",
    cnic: String = "",
    address: String = "",
    number: String = ""
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
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Name",
                fontSize = 10.ssp,
                fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_regular)),
                color = colorResource(id = com.thezayin.values.R.color.text_color_light),
            )
            Text(
                text = if (name.length < 2) "Data not found in server" else name,
                modifier = Modifier.padding(top = 10.sdp),
                fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
                color = colorResource(id = com.thezayin.values.R.color.black),
                fontSize = 16.ssp,
            )
            Text(
                text = "CNIC:",
                fontSize = 10.ssp,
                modifier = Modifier.padding(top = 15.sdp),
                fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_regular)),
                color = colorResource(id = com.thezayin.values.R.color.text_color_light),
            )
            Text(
                text = if (cnic.length < 2) "Data not found in server" else cnic,
                modifier = Modifier.padding(top = 10.sdp),
                fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
                color = colorResource(id = com.thezayin.values.R.color.black),
                fontSize = 16.ssp,
            )
            Text(
                text = "Address:",
                modifier = Modifier.padding(top = 15.sdp),
                fontSize = 10.ssp,
                fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_regular)),
                color = colorResource(id = com.thezayin.values.R.color.text_color_light),
            )
            Text(
                text = if (address.length < 2) "Data not found in server" else address,
                modifier = Modifier.padding(top = 10.sdp),
                lineHeight = 20.ssp,
                fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
                color = colorResource(id = com.thezayin.values.R.color.black),
                fontSize = 16.ssp,
            )
            Text(
                text = "Number:",
                modifier = Modifier.padding(top = 15.sdp),
                fontSize = 10.ssp,
                fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_regular)),
                color = colorResource(id = com.thezayin.values.R.color.text_color_light),
            )
            Text(
                text = if (number.length < 2) "Data not found in server" else number,
                modifier = Modifier.padding(top = 15.sdp),
                fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
                color = colorResource(id = com.thezayin.values.R.color.black),
                fontSize = 16.ssp,
            )

        }
    }
}