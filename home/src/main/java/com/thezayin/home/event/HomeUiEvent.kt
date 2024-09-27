package com.thezayin.home.event

import com.thezayin.domain.model.HistoryModel

sealed interface HomeUiEvents {
    data class GetHistory(val historyList: List<HistoryModel>?) : HomeUiEvents
}