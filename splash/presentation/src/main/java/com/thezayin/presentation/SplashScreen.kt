package com.thezayin.presentation

import android.app.Activity
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.thezayin.analytics.events.AnalyticsEvent
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.framework.ads.interstitialAd
import com.thezayin.presentation.component.BottomText
import com.thezayin.presentation.component.ImageHeader
import kotlinx.coroutines.delay
import org.koin.compose.koinInject

@Composable
fun SplashScreen(
    onNavigate: () -> Unit
) {
    val viewModel: SplashViewModel = koinInject()
    val activity = LocalContext.current as Activity
    val scope = rememberCoroutineScope()

    viewModel.analytics.logEvent(AnalyticsEvent.ScreenViewEvent("SplashScreen"))

    LaunchedEffect(Unit) {
        delay(4000)
        activity.interstitialAd(
            scope = scope,
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
            BottomText(modifier = Modifier)
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