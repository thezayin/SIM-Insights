package com.thezayin.presentation

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import com.thezayin.analytics.events.AnalyticsEvent
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.framework.extension.ads.showAppOpenAd
import com.thezayin.framework.lifecycles.ComposableLifecycle
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import com.thezayin.presentation.component.BottomText
import com.thezayin.presentation.component.ImageHeader
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun SplashScreen(
    onNavigate: () -> Unit
) {
    val viewModel: SplashViewModel = koinInject()
    val activity = LocalContext.current as Activity
    val scope = rememberCoroutineScope()

    viewModel.analytics.logEvent(AnalyticsEvent.ScreenViewEvent("SplashScreen"))
    val nativeAd = viewModel.nativeAd.value

    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                scope.launch {
                    while (this.isActive) {
                        viewModel.getNativeAd()
                        delay(20000L)
                    }
                }
            }

            else -> {
            }
        }
    }

    LaunchedEffect(Unit) {
        delay(10000)
        activity.showAppOpenAd(
            analytics = viewModel.analytics,
            googleManager = viewModel.googleManager,
            showAd = viewModel.remoteConfig.adConfigs.adOnSplashScreen,
        ) { onNavigate() }
    }

    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .statusBarsPadding(),
        containerColor = ConstantColor.NeumorphismLightBackgroundColor,
        bottomBar = {
            Column {
                BottomText(modifier = Modifier)
                GoogleNativeAd(
                    modifier = Modifier,
                    style = GoogleNativeAdStyle.Small,
                    nativeAd = nativeAd
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ImageHeader(modifier = Modifier.align(Alignment.Center))
        }
    }
}