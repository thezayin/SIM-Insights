package com.thezayin.paksimdata.presentation.settings.component

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.neumorphic.ConstantColor
import com.thezayin.neumorphic.widgets.ShadeCard
import com.thezayin.paksimdata.R

@Preview
@Composable
fun SettingHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ConstantColor.NeumorphismLightBackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShadeCard(
            modifier = Modifier.size(100.dp),
            cornerRadius = 100.dp,
            onClick = {}
        ) {
            Image(painter = painterResource(id = R.drawable.ic_main), contentDescription = "Logo")
        }
        Text(
            text = "Pak Sim Data",
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.abeezee_regular)),
            color = colorResource(id = com.thezayin.core.R.color.black),
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}