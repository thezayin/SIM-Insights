package com.thezayin.premium.component

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
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.common.neumorphic.widgets.ShadeCard
import ir.kaaveh.sdpcompose.sdp

@Composable
fun PremiumTopBar(
    modifier: Modifier,
    onBackClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.sdp, vertical = 20.sdp)
            .background(ConstantColor.NeumorphismLightBackgroundColor),
        horizontalArrangement = Arrangement.Start,
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
    }
}