package com.thezayin.setting.component

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.common.neumorphic.widgets.ShadeCard
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Preview
@Composable
fun SettingHeader() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ConstantColor.NeumorphismLightBackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShadeCard(
            modifier = Modifier.size(80.sdp),
            cornerRadius = 80.sdp,
            onClick = {
                Toast.makeText(context, "Coming soon", Toast.LENGTH_SHORT).show()
            }
        ) {
            Image(
                contentScale = ContentScale.FillBounds,
                painter = painterResource(id = com.thezayin.drawable.R.drawable.ic_main),
                contentDescription = "Logo"
            )
        }
        Text(
            text = "SIM Insights",
            fontSize = 18.ssp,
            fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_regular)),
            color = colorResource(id = com.thezayin.values.R.color.black),
            modifier = Modifier.padding(top = 20.sdp)
        )
    }
}