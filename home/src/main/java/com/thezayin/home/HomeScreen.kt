package com.thezayin.home

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import com.thezayin.analytics.events.AnalyticsEvent
import com.thezayin.common.dailogs.LoadingAdDialog
import com.thezayin.framework.extension.ads.showInterstitialAd
import com.thezayin.framework.extension.ads.showRewardedAd
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
    val homeBottomNativeAd = remember { viewModel.homeBottomNativeAd }
    val homeBottomNativeAdMid = remember { viewModel.homeBottomNativeAdMid }

    val showServerList = remember { viewModel.remoteConfig.adConfigs.showServerList }
    val showAdLoading = remember { mutableStateOf(false) }

    if (showAdLoading.value) {
        LoadingAdDialog()
    }

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
        homeBottomNativeAd = homeBottomNativeAd.value,
        homeBottomNativeAdMid = homeBottomNativeAdMid.value,
        showPremium = viewModel.remoteConfig.adConfigs.showPremium,
        showHistory = viewModel.remoteConfig.adConfigs.showHistory,
        showServerList = showServerList,
        historyList = uiState.getHistory,
        onHistoryClick = {
            activity.showRewardedAd(
                showLoading = { showAdLoading.value = true },
                hideLoading = { showAdLoading.value = false },
                googleManager = viewModel.googleManager,
                showAd = viewModel.remoteConfig.adConfigs.adOnPremiumClick,
                callback = onHistoryClick
            )
        },
        onMenuClick = {
            activity.showInterstitialAd(
                showLoading = { showAdLoading.value = true },
                hideLoading = { showAdLoading.value = false },
                googleManager = viewModel.googleManager,
                showAd = viewModel.remoteConfig.adConfigs.adOnSettingClick,
                callback = onMenuClick
            )
        },
        onServerClick = {
            activity.showRewardedAd(
                showLoading = { showAdLoading.value = true },
                hideLoading = { showAdLoading.value = false },
                googleManager = viewModel.googleManager,
                showAd = viewModel.remoteConfig.adConfigs.adOnServerClick,
                callback = onServerClick
            )
        },
        onSearchClick = { number ->
            activity.showRewardedAd(
                showLoading = { showAdLoading.value = true },
                hideLoading = { showAdLoading.value = false },
                googleManager = viewModel.googleManager,
                showAd = viewModel.remoteConfig.adConfigs.adOnSearchClick,
                callback = {
                    onSearchClick(number)
                    viewModel.analytics.logEvent(
                        AnalyticsEvent.SearchNumberClick(
                            status = number
                        )
                    )
                }
            )
        }
    )
}