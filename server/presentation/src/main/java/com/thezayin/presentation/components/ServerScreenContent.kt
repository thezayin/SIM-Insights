package com.thezayin.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.common.topbar.TopBar
import com.thezayin.domain.model.ServerModel
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import ir.kaaveh.sdpcompose.sdp

@Composable
fun ServerScreenContent(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onPremiumClick: () -> Unit,
    onServerClick: (String) -> Unit,
    list: List<ServerModel>,
    showBottomAd: MutableState<Boolean>,
    nativeAd: NativeAd?
) {
    Scaffold(modifier = modifier
        .navigationBarsPadding()
        .statusBarsPadding()
        .fillMaxSize(),
        containerColor = ConstantColor.NeumorphismLightBackgroundColor,
        topBar = {
            TopBar(
                modifier = Modifier, onBackClick = onBackClick, onPremiumClick = onPremiumClick
            )
        },
        bottomBar = {
            if (showBottomAd.value) {
                GoogleNativeAd(
                    modifier = Modifier,
                    style = GoogleNativeAdStyle.Small,
                    nativeAd = nativeAd
                )
            }
        }) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(vertical = 10.sdp, horizontal = 10.sdp)
        ) {
            ServerMenu(
                modifier = modifier,
                list = list,
                onServerClick = onServerClick
            )
        }
    }
}