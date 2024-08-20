package com.thezayin.setting.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.analytics.analytics.Analytics
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import ir.kaaveh.sdpcompose.sdp

@Composable
fun SettingScreenContent(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onPremiumClick: () -> Unit,
    showAd: Boolean?,
    nativeAd: NativeAd?,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = ConstantColor.NeumorphismLightBackgroundColor,
        topBar = {
            SettingTopBar(
                modifier = Modifier,
                onBackPress = onBackClick,
                onPremiumPress = onPremiumClick
            )
        },
        bottomBar = {
            if (showAd==true) {
                GoogleNativeAd(
                    modifier = Modifier,
                    style = GoogleNativeAdStyle.Small,
                    nativeAd = nativeAd
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(top = 60.sdp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            SettingOptionsList()
        }
    }
}