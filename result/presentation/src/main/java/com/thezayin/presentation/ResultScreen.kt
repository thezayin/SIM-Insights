package com.thezayin.presentation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import com.thezayin.analytics.events.AnalyticsEvent
import com.thezayin.common.dailogs.ErrorDialog
import com.thezayin.common.dailogs.LoadingDialog
import com.thezayin.framework.ads.interstitialAd
import com.thezayin.framework.lifecycles.ComposableLifecycle
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import com.thezayin.presentation.components.ScreenContent
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun ResultScreen(phoneNumber: String, onBackPress: () -> Unit, onPremiumClick: () -> Unit) {
    val viewModel: ResultViewModel = koinInject()
    val activity = LocalContext.current as Activity
    val state by viewModel.resultUiState.collectAsState()
    val scope = rememberCoroutineScope()
    val nativeAd = remember { viewModel.nativeAd }
    val showBottomAd =
        remember { mutableStateOf(viewModel.remoteConfig.adConfigs.nativeAdOnResultScreen) }
    val showLoadingAd =
        remember { mutableStateOf(viewModel.remoteConfig.adConfigs.nativeAdOnResultLoadingDialog) }

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
        viewModel.searchNumber(phoneNumber)
    }

    viewModel.analytics.logEvent(AnalyticsEvent.ScreenViewEvent("ResultScreen"))

    if (state.loading) {
        LoadingDialog(
            showAd = showLoadingAd.value,
            nativeAd = nativeAd.value,
            ad = {
                GoogleNativeAd(
                    modifier = Modifier,
                    style = GoogleNativeAdStyle.Small,
                    nativeAd = nativeAd.value
                )
            }
        )
    }

    if (state.errorDialog) {
        ErrorDialog(
            error = state.error,
            showDialog = { viewModel.hideErrorDialog() },
            callback = { onBackPress() })
    }

    state.result?.let { result ->
        ScreenContent(
            modifier = Modifier,
            showBottomAd = showBottomAd.value,
            nativeAd = nativeAd.value,
            onBackClick = {
                activity.interstitialAd(
                    scope = scope,
                    googleManager = viewModel.googleManager,
                    analytics = viewModel.analytics,
                    showAd = viewModel.remoteConfig.adConfigs.adOnBackPress
                ) {
                    onBackPress()
                }
            },
            onPremiumClick = {
                activity.interstitialAd(
                    scope = scope,
                    googleManager = viewModel.googleManager,
                    analytics = viewModel.analytics,
                    showAd = viewModel.remoteConfig.adConfigs.adOnPremiumClick
                ) {
                    onPremiumClick()
                }
            },
            result = result
        )
    }
}