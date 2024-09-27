package com.thezayin.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.ads.GoogleManager
import com.thezayin.analytics.analytics.Analytics
import com.thezayin.domain.model.HistoryModel
import com.thezayin.domain.usecase.GetHistory
import com.thezayin.framework.remote.RemoteConfig
import com.thezayin.framework.utils.Response
import com.thezayin.home.event.HomeUiEvents
import com.thezayin.home.state.HomeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    val googleManager: GoogleManager,
    val remoteConfig: RemoteConfig,
    val analytics: Analytics,
    val getHistory: GetHistory
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeState())
    val homeUiState = _homeUiState.asStateFlow()

    var nativeAd = mutableStateOf<NativeAd?>(null)
        private set

    private fun homeUiEvent(event: HomeUiEvents) {
        when (event) {
            is HomeUiEvents.GetHistory -> _homeUiState.update { it.copy(getHistory = event.historyList) }
        }
    }

    init {
        getSearchHistory()
    }

    private fun getSearchHistory() = viewModelScope.launch {
        getHistory().collect { response ->
            when (response) {
                is Response.Success -> {
                    getHistoryList(response.data)
                }

                else -> getHistoryList(null)
            }
        }
    }

    fun getNativeAd() = viewModelScope.launch {
        nativeAd.value = googleManager.createNativeAd().apply {} ?: run {
            delay(10000)
            googleManager.createNativeAd()
        }
    }

    private fun getHistoryList(historyList: List<HistoryModel>?) {
        homeUiEvent(HomeUiEvents.GetHistory(historyList))
    }
}