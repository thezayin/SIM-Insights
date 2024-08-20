package com.thezayin.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.thezayin.common.neumorphic.widgets.ShadeCard
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun BottomBar(modifier: Modifier, onServerClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.sdp, vertical = 10.sdp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = "If you can’t find data select servers",
            fontSize = 12.ssp,
            fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
            color = colorResource(id = com.thezayin.values.R.color.text_color),
            modifier = Modifier.padding(bottom = 10.sdp)
        )
        ShadeCard(
            onClick = {
                onServerClick()
            },
            cornerRadius = 10.sdp,
            modifier = Modifier.height(60.sdp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.sdp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "More Server",
                    fontSize = 14.ssp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
                    color = colorResource(id = com.thezayin.values.R.color.text_color)
                )
                Image(
                    painter = painterResource(id = com.thezayin.drawable.R.drawable.ic_next),
                    contentDescription = null,
                    modifier = Modifier
                        .size(15.sdp),
                )
            }
        }
    }
}