package com.thezayin.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.thezayin.analytics.events.AnalyticsEvent
import com.thezayin.common.dailogs.DeleteSuccessDialog
import com.thezayin.common.dailogs.ErrorDialog
import com.thezayin.common.dailogs.LoadingDialog
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import com.thezayin.presentation.component.RecentScreenContent
import org.koin.compose.koinInject

@Composable
fun HistoryScreen(
    onBackPress: () -> Unit
) {
    val viewModel: HistoryViewModel = koinInject()
    val state = viewModel.historyUiState.collectAsState().value
    val scope = rememberCoroutineScope()

    val historyNotFound = state.resultNotFound
    val list = state.list
    val error = state.error
    val loading = state.loading
    val errorDialog = state.errorDialog
    val deleteDialogShow = state.deleteDialogShow
    val nativeAd = viewModel.nativeAd.value

    Log.d("jejeHistoryScreen", "HistoryScreen")
    Log.d("jejeHistoryScreen", "historyNotFound: $historyNotFound")
    Log.d("jejeHistoryScreen", "list: $list")
    Log.d("jejeHistoryScreen", "error: $error")
    Log.d("jejeHistoryScreen", "loading: $loading")
    Log.d("jejeHistoryScreen", "errorDialog: $errorDialog")
    Log.d("jejeHistoryScreen", "deleteDialogShow: $deleteDialogShow")
    Log.d("jejeHistoryScreen", "nativeAd: $nativeAd")


    val showBottomAd =
        remember { mutableStateOf(viewModel.remoteConfig.adConfigs.nativeAdOnResultScreen) }
    val showLoadingAd =
        remember { mutableStateOf(viewModel.remoteConfig.adConfigs.nativeAdOnResultLoadingDialog) }

    viewModel.analytics.logEvent(AnalyticsEvent.ScreenViewEvent("HistoryScreen"))
    if (loading) {
        LoadingDialog(
            showAd = showLoadingAd.value,
            nativeAd = nativeAd,
            ad = {
                GoogleNativeAd(
                    modifier = Modifier,
                    style = GoogleNativeAdStyle.Small,
                    nativeAd = nativeAd
                )
            }
        )
    }

    if (errorDialog) {
        ErrorDialog(
            error = error,
            showDialog = { viewModel.hideErrorDialog() },
            callback = { onBackPress() })
    }

    if (deleteDialogShow) {
        DeleteSuccessDialog(
            showDialog = { viewModel.showDeleteDialog(false) },
            callback = { viewModel.getSearchHistory() }
        )
    }

    RecentScreenContent(
        showNativeAd = showBottomAd.value,
        scope = scope,
        nativeAd = nativeAd,
        historyNotFound = historyNotFound,
        list = list,
        onBackClick = {
            onBackPress()
        },
        onDeleteClick = {
            Log.d("jejeHistoryScreen", "onDeleteClick")
            viewModel.deleteSearchHistory()
        },
        getNativeAd = { viewModel.getNativeAd() },
    )
}