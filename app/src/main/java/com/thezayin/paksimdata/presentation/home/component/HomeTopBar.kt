package com.thezayin.paksimdata.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.neumorphic.ConstantColor
import com.thezayin.neumorphic.widgets.ShadeCard
import com.thezayin.paksimdata.presentation.destinations.SettingScreenDestination

@Composable
fun TopBarComponent(
    modifier: Modifier, navigator: DestinationsNavigator
) {
    Row(
        modifier = modifier
            .fillMaxWidth().padding(top = 40.dp)
            .background(ConstantColor.NeumorphismLightBackgroundColor)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ShadeCard(
            onClick = { navigator.navigate(SettingScreenDestination) },
            cornerRadius = 40.dp,
            modifier = Modifier.size(40.dp)
        ) {
            Image(
                painter = painterResource(id = com.thezayin.core.R.drawable.ic_menue),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(10.dp),
            )
        }
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