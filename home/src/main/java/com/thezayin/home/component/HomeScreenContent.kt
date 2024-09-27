package com.thezayin.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.domain.model.HistoryModel
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import ir.kaaveh.sdpcompose.sdp

@Composable
fun HomeScreenContent(
    modifier: Modifier,
    showPremium: Boolean,
    historyList: List<HistoryModel>?,
    onHistoryClick: () -> Unit,
    onMenuClick: () -> Unit,
    onServerClick: () -> Unit,
    onSearchClick: (String) -> Unit,
    nativeAd: NativeAd?,
    showServerList: Boolean,
) {
    val number = remember { mutableStateOf(TextFieldValue()) }
    val showWarning = remember { mutableStateOf(false) }
    Scaffold(
        modifier = modifier
            .navigationBarsPadding()
            .statusBarsPadding(),
        containerColor = ConstantColor.NeumorphismLightBackgroundColor,
        topBar = {
            TopBar(
                modifier = Modifier,
                showPremium = showPremium,
                onMenuClick = onMenuClick,
                onHistoryClick = onHistoryClick
            )
        },
        bottomBar = {
            if (showServerList) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    BottomBar(
                        modifier = Modifier,
                        onServerClick = onServerClick
                    )
                    GoogleNativeAd(
                        modifier = Modifier,
                        style = GoogleNativeAdStyle.Small,
                        nativeAd = nativeAd
                    )
                }
            } else {
                TipsAndTricksSection()
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchCard(
                modifier = Modifier,
                number = number,
                showWarning = showWarning,
                onSearchClick = onSearchClick
            )
            RecentPeak(
                modifier = Modifier.padding(10.sdp),
                list = historyList
            )
        }
    }
}