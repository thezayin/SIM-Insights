package com.thezayin.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.common.neumorphic.widgets.ShadeCard
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Preview
@Composable
fun ServerItem(
    modifier: Modifier = Modifier,
    name: String = "Server Name",
    url: String = "Server URL",
    onServerClick: (String) -> Unit = {},
) {
    ShadeCard(
        onClick = {
            onServerClick(url)
        },
        cornerRadius = 10.sdp,
        modifier = modifier.height(75.sdp)
    ) {
        Row(
            modifier = Modifier
                .padding(15.sdp)
                .background(color = ConstantColor.NeumorphismLightBackgroundColor)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = name,
                fontSize = 18.ssp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
                color = colorResource(id = com.thezayin.values.R.color.text_color)
            )
            Image(
                painter = painterResource(id = com.thezayin.drawable.R.drawable.ic_next),
                contentDescription = null,
                modifier = Modifier
                    .size(16.sdp),
            )
        }
    }
}