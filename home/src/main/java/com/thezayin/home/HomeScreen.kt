package com.thezayin.home

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import com.thezayin.analytics.events.AnalyticsEvent
import com.thezayin.framework.ads.interstitialAd
import com.thezayin.framework.extension.ads.showRewardedInterstitialAd
import com.thezayin.framework.lifecycles.ComposableLifecycle
import com.thezayin.home.component.HomeScreenContent
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    onHistoryClick: () -> Unit,
    onMenuClick: () -> Unit,
    onServerClick: () -> Unit,
    onSearchClick: (String) -> Unit
) {
    val viewModel: HomeViewModel = koinInject()
    val uiState = viewModel.homeUiState.collectAsState().value

    val activity = LocalContext.current as Activity
    val scope = rememberCoroutineScope()
    val nativeAd = remember { viewModel.nativeAd }

    val showServerList = remember { viewModel.remoteConfig.adConfigs.showServerList }

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

    viewModel.analytics.logEvent(AnalyticsEvent.ScreenViewEvent("HomeScreen"))

    HomeScreenContent(
        modifier = Modifier,
        nativeAd = nativeAd.value,
        showPremium = viewModel.remoteConfig.adConfigs.showPremium,
        showServerList = showServerList,
        historyList = uiState.getHistory,
        onHistoryClick = {
            activity.interstitialAd(
                scope = scope,
                analytics = viewModel.analytics,
                googleManager = viewModel.googleManager,
                showAd = viewModel.remoteConfig.adConfigs.adOnPremiumClick
            ) {
                onHistoryClick()
            }
        },
        onMenuClick = {
            activity.interstitialAd(
                scope = scope,
                analytics = viewModel.analytics,
                googleManager = viewModel.googleManager,
                showAd = viewModel.remoteConfig.adConfigs.adOnSettingClick
            ) {
                onMenuClick()
            }
        },
        onServerClick = {
            activity.showRewardedInterstitialAd(
                analytics = viewModel.analytics,
                googleManager = viewModel.googleManager,
                boolean = viewModel.remoteConfig.adConfigs.adOnServerClick
            ) {
                onServerClick()
            }
        },
        onSearchClick = { number ->
            activity.showRewardedInterstitialAd(
                analytics = viewModel.analytics,
                googleManager = viewModel.googleManager,
                boolean = viewModel.remoteConfig.adConfigs.adOnSearchClick
            ) {
                onSearchClick(number)
                viewModel.analytics.logEvent(
                    AnalyticsEvent.SearchNumberClick(
                        status = number
                    )
                )
            }
        }
    )
}