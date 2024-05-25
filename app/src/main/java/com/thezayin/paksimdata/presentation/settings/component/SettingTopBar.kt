package com.thezayin.paksimdata.presentation.settings.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.neumorphic.ConstantColor
import com.thezayin.neumorphic.widgets.ShadeCard


@Composable
fun SettingTopBar(
    modifier: Modifier,
    navigator: DestinationsNavigator
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 40.dp)
            .background(ConstantColor.NeumorphismLightBackgroundColor)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ShadeCard(
            onClick = { navigator.navigateUp() },
            cornerRadius = 40.dp,
            modifier = Modifier.size(40.dp)
        ) {
            Image(
                painter = painterResource(id = com.thezayin.core.R.drawable.ic_back),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(10.dp),
            )
        }
        Text(
            text = "Setting",
            color = colorResource(id = com.thezayin.core.R.color.black),
            fontSize = 17.sp,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.abeezee_italic)),
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ShadeCard(
                onClick = {},
                cornerRadius = 40.dp,
                modifier = Modifier.size(40.dp)
            ) {
                Image(
                    painter = painterResource(id = com.thezayin.core.R.drawable.ic_vpn),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(10.dp),
                )
            }
            ShadeCard(
                onClick = { },
                cornerRadius = 40.dp,
                modifier = Modifier.size(40.dp)
            ) {
                Image(
                    painter = painterResource(id = com.thezayin.core.R.drawable.ic_crown),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(10.dp),
                )
            }
        }
    }
}