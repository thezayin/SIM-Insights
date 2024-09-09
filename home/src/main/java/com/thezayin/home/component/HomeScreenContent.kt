package com.thezayin.home.component

import androidx.compose.foundation.layout.Box
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
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle

@Composable
fun HomeScreenContent(
    modifier: Modifier,
    showPremium: Boolean,
    onPremiumClick: () -> Unit,
    onMenuClick: () -> Unit,
    onServerClick: () -> Unit,
    onSearchClick: (String) -> Unit,
    nativeAd: NativeAd?,
    showNativeAd: Boolean?,
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
                onPremiumClick = onPremiumClick
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
                    if (showNativeAd == true) {
                        GoogleNativeAd(
                            modifier = Modifier,
                            style = GoogleNativeAdStyle.Small,
                            nativeAd = nativeAd
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(
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
        }
    }
}