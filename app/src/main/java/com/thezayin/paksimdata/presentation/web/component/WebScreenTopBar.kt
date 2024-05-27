package com.thezayin.paksimdata.presentation.web.component

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.core.R
import com.thezayin.neumorphic.ConstantColor
import com.thezayin.neumorphic.widgets.ShadeCard
import com.thezayin.paksimdata.presentation.activities.MainViewModel
import com.thezayin.paksimdata.presentation.activities.dialogs.interstitialAd

@Composable
fun WebScreenTopBar(
    navigator: DestinationsNavigator,
    modifier: Modifier,
    mainViewModel: MainViewModel
) {
    val activity = LocalContext.current as Activity
    val scope = rememberCoroutineScope()
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
            onClick = {
                activity.interstitialAd(
                    scope = scope,
                    viewModel = mainViewModel,
                    showAd = mainViewModel.remoteConfig.showAdOnWebScreenBackSelection,
                ) {
                    navigator.navigateUp()
                }
            },
            cornerRadius = 40.dp,
            modifier = Modifier.size(40.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(10.dp),
            )
        }
        Text(
            text = "Servers",
            color = colorResource(id = R.color.black),
            fontSize = 17.sp,
            fontFamily = FontFamily(Font(R.font.abeezee_italic)),
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ShadeCard(
                onClick = {
                    activity.interstitialAd(
                        scope = scope,
                        viewModel = mainViewModel,
                        showAd = mainViewModel.remoteConfig.showAdOnWebScreenVPNSelection,
                        {},
                    )
                },
                cornerRadius = 40.dp,
                modifier = Modifier.size(40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_vpn),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(10.dp),
                )
            }
            ShadeCard(
                onClick = {
                    activity.interstitialAd(
                        scope = scope,
                        viewModel = mainViewModel,
                        showAd = mainViewModel.remoteConfig.showAdOnWebScreenIAPSelection,
                        {},
                    )
                },
                cornerRadius = 40.dp,
                modifier = Modifier.size(40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_crown),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(10.dp),
                )
            }
        }
    }
}