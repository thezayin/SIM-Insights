package com.thezayin.common.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.common.neumorphic.widgets.ShadeCard
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun TopBar(
    screenName: String,
    modifier: Modifier = Modifier,
    showPremium: Boolean,
    onBackClick: () -> Unit = {},
    onPremiumClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.sdp, vertical = 20.sdp)
            .background(ConstantColor.NeumorphismLightBackgroundColor),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ShadeCard(
            onClick = {
                onBackClick()
            },
            cornerRadius = 40.sdp,
            modifier = Modifier.size(25.sdp)
        ) {
            Image(
                painter = painterResource(id = com.thezayin.drawable.R.drawable.ic_back),
                contentDescription = null,
                modifier = Modifier
                    .size(25.sdp)
                    .padding(6.sdp),
            )
        }
        Text(
            text = screenName,
            color = colorResource(id = com.thezayin.values.R.color.black),
            fontSize = 17.ssp,
            fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
        )

        Spacer(modifier)
        if (showPremium) {
            ShadeCard(
                onClick = {
                    onPremiumClick()
                },
                cornerRadius = 40.sdp,
                modifier = Modifier.size(25.sdp)
            ) {
                Image(
                    painter = painterResource(id = com.thezayin.drawable.R.drawable.ic_crown),
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.sdp)
                        .padding(6.sdp),
                )
            }
        }
    }
}