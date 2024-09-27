package com.thezayin.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.common.topbar.TopBar
import com.thezayin.domain.model.ResultModel
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import ir.kaaveh.sdpcompose.sdp

@Composable
fun ResultScreenContent(
    modifier: Modifier,
    nativeAd: NativeAd?,
    result: ResultModel?,
    showPremium: Boolean,
    showBottomAd: Boolean?,
    resultNotFound: Boolean?,
    onBackClick: () -> Unit,
    onPremiumClick: () -> Unit,
) {
    Scaffold(modifier = modifier
        .navigationBarsPadding()
        .statusBarsPadding()
        .fillMaxSize(),
        containerColor = ConstantColor.NeumorphismLightBackgroundColor,
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                TopBar(
                    modifier = Modifier,
                    onBackClick = onBackClick,
                    onPremiumClick = onPremiumClick,
                    showPremium = showPremium,
                    screenName = "Result"
                )
                if (showBottomAd == true) {
                    GoogleNativeAd(
                        modifier = Modifier, style = GoogleNativeAdStyle.Small, nativeAd = nativeAd
                    )
                }
            }
        },
        bottomBar = {
            if (showBottomAd == true) {
                GoogleNativeAd(
                    modifier = Modifier, style = GoogleNativeAdStyle.Small, nativeAd = nativeAd
                )
            }
        }) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(vertical = 35.sdp, horizontal = 10.sdp)
        ) {
            result?.let { result ->
                ResultContent(
                    modifier = Modifier,
                    name = result.name,
                    cnic = result.cnic,
                    address = result.address,
                    number = result.number
                )
            }
            resultNotFound?.let {
                ResultNotFound(modifier = Modifier)
            }
        }
    }
}